package com.example.demo;

import com.example.demo.producer.Pro_01;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author wll
 * @desc Msg_01_Producer_Test
 * @link
 * @date 2020/5/22 2:44 下午
 */
@SpringBootTest(classes = MQ_Demo_Application.class)
@Slf4j
public class Msg_01_Pro_Test {

    @Autowired
    private Pro_01 producer;

    @SneakyThrows
    @Test //同步消息
    public void testSyncSend() {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 异步消息
    @Test
    @SneakyThrows
    public void testASyncSend() {

        int id = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(id, new SendCallback() {

            @Override
            public void onSuccess(SendResult result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
            }

            @Override
            public void onException(Throwable e) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }
        });

        //阻塞等待 ，保证消费
        new CountDownLatch(1).await();
    }

    // oneway 只管发送，不在意是否成功，日志处理一般这样
    @Test
    @SneakyThrows
    public void testOnewaySend() {

        int id = (int) (System.currentTimeMillis() / 1000);
        producer.onewaySend(id);
        log.info("[testOnewaySend][发送编号：[{}] 发送完成]", id);
        //阻塞等待 ，保证消费
        new CountDownLatch(1).await();
    }

}