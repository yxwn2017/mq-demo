package com.example.demo;

import com.example.demo.producer.Pro_04;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
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
public class Msg_04_Pro_Test {

    @Autowired
    private Pro_04 producer;

    @SneakyThrows
    @Test //同步消息-顺序
    public void testSyncSendOrderly() {

        // 发送多条消息
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            SendResult result = producer.syncSendOrderly(id);
            log.info("[同步消息-顺序][发送编号：[{}] 发送结果：[{}]]", id, result);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    // 异步消息
    @Test
    @SneakyThrows
    public void testASyncSendOrderly() {

        // 发送多条消息
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            producer.asyncSendOrderly(id, new SendCallback() {
                @Override
                public void onSuccess(SendResult result) {
                    log.info("[异步消息-顺序]-succ[发送编号：[{}] 发送结果：[{}]]", id, result);
                }

                @Override
                public void onException(Throwable e) {
                    log.error("[异步消息-顺序]-fail[发送编号：[{}] 发送结果：[{}]]", id, e);

                }
            });
        }

        //阻塞等待 ，保证消费
        new CountDownLatch(1).await();
    }

    // oneway 只管发送，不在意是否成功，日志处理一般这样
    @Test
    @SneakyThrows
    public void testOnewaySendOrderly() {

        // 发送多条消息
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            producer.onewaySendOrderly(id);
            log.info("[oneway-顺序][发送编号：[{}] 发送结果：[{}]]", id);
        }
        //阻塞等待 ，保证消费
        new CountDownLatch(1).await();
    }

}