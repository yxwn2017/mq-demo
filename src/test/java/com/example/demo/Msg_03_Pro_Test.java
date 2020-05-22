package com.example.demo;

import com.example.demo.producer.Pro_02;
import com.example.demo.producer.Pro_03;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author wll
 * @desc Msg_02_Producer_Test
 * @link
 * @date 2020/5/22 2:44 下午
 */
@SpringBootTest(classes = MQ_Demo_Application.class)
@Slf4j
public class Msg_03_Pro_Test {

    @Autowired
    private Pro_03 producer;

    @Test
    @SneakyThrows
    public  void testCommon( ) {
        testSyncSendDelay();
        testASyncSendDelay();
        new CountDownLatch(1).await();

    }

    //同步延时消息
    @SneakyThrows
    @Test
    public void testSyncSendDelay() {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSendDelay(id,3);
        log.info("[同步延时消息][发送编号：[{}] 发送结果：[{}]]", id, result);


        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
    }
    //异步延时消息
    @SneakyThrows
    @Test
    public void testASyncSendDelay() {
        int id = (int) (System.currentTimeMillis() / 1000);
         producer.asyncSendDelay(id, 2, new SendCallback() {
            @Override
            public void onSuccess(SendResult result) {
                log.info("[异步延时消息][发送编号：[{}] 发送结果：[{}]]", id, result);

            }

            @Override
            public void onException(Throwable e) {
                log.error("[异步延时消息] [发送编号：[{}] 发送结果：[{}]]", id, e);
            }
        });


        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
    }


}