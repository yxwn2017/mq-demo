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
 * 同步批量
 * @author wll
 * @desc Pro_02
 * @link
 * @date 2020/5/21 8:52 下午
 */
@Component
public class Pro_02 {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //同步消息--批量
    public SendResult sendBatch(Collection<Integer> ids){

        // 创建多条消息 Message
        ArrayList<Message> msgs = new ArrayList<>(ids.size());

        ids.forEach(id->{

            // 创建消息
            Message01 msg = new Message01();
            msg.setId(id);


            // 构件spring messaing 定义的 message 消息
            msgs.add(MessageBuilder.withPayload(msg).build());

        });

        //同步批量发送消息
       return rocketMQTemplate.syncSend(Message01.TOPIC,msgs,30 * 1000L);
    }


}
