package com.example.demo.producer;

import com.example.demo.message.Message01;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 事务消息
 *
 * @author wll
 * @desc Pro_05
 * @link
 * @date 2020/5/21 8:52 下午
 */
@Component
public class Pro_05 {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //事务消息
    public TransactionSendResult sendMsgInTransaction(Integer id){

        // 创建 Message
        Message msg = MessageBuilder.withPayload(new Message01().setId(id)).build();

        //发送事务消息todo
        return rocketMQTemplate.sendMessageInTransaction(Message01.TOPIC,msg,id);

    }


}
