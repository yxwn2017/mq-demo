package com.example.demo.producer;

import com.example.demo.message.Message01;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 延时消息
 *
 * @author wll
 * @desc Pro_03
 * @link
 * @date 2020/5/21 8:52 下午
 */
@Component
public class Pro_03 {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 延时消息 -- 同步
    public SendResult syncSendDelay(Integer id,int delayLevel){

        // 创建message
        Message  message = MessageBuilder.withPayload(new Message01().setId(id)).build();

        //同步发送消息
        return rocketMQTemplate.syncSend(Message01.TOPIC,message,30*1000,delayLevel);
    }

    // 延时消息 -- 异步
    public void asyncSendDelay(Integer id, int delayLevel, SendCallback callback){

        // 创建message
        Message  message = MessageBuilder.withPayload(new Message01().setId(id)).build();

        //异步
        rocketMQTemplate.asyncSend(Message01.TOPIC,message,callback,30*1000,delayLevel);

    }

}
