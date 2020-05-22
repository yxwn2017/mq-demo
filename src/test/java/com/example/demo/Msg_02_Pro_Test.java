package com.example.demo;

import com.example.demo.producer.Pro_01;
import com.example.demo.producer.Pro_02;
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
public class Msg_02_Pro_Test {

    @Autowired
    private Pro_02 producer;

    //同步批量消息
    @SneakyThrows
    @Test
    public void testSyncSend() {
//        int id = (int) (System.currentTimeMillis() / 1000);
        List ids =Arrays.asList(1,2,3,4,5,6,7);
        SendResult result = producer.sendBatch(ids);
        log.error("[testSendBatch][发送编号：[{}] 发送结果：[{}]]", ids, result);


        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }



}