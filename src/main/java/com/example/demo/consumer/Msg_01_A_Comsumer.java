package com.example.demo.consumer;

import com.example.demo.message.Message01;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author wll
 * @desc Msg_01_A_Comsumer
 * @link
 * @date 2020/5/22 2:24 下午
 */
@Component
@RocketMQMessageListener(
        topic = Message01.TOPIC,
        consumerGroup = "msg-01-A-consumer-group-"+Message01.TOPIC
)
@Slf4j
public class Msg_01_A_Comsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), messageExt);

        // sleep 2 秒，用于查看顺序消费的效果
        try {
            Thread.sleep(2 * 1000L);
        } catch (InterruptedException ignore) {
        }

    }
}
