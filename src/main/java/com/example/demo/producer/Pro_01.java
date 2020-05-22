package com.example.demo.producer;

import com.example.demo.message.Message01;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者 发送模式
 * @author wll
 * @desc Pro_01
 * @link
 * @date 2020/5/21 8:52 下午
 */
@Component
public class Pro_01 {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //同步消息
    public SendResult syncSend(Integer id){

        //创建 msg01
        Message01 msg1=new Message01();
        msg1.setId(id);

       return rocketMQTemplate.syncSend(Message01.TOPIC,msg1);
    }

    //异步消息
    public void asyncSend(Integer id, SendCallback callback){

        //创建 msg01
        Message01 msg1=new Message01();
        msg1.setId(1);

        rocketMQTemplate.asyncSend(Message01.TOPIC,msg1,callback);
    }

    /// oneway 发送消息
    public void onewaySend(Integer id) {
        // 创建 Demo01Message 消息
        Message01 message = new Message01();
        message.setId(id);
        // oneway 发送消息
        rocketMQTemplate.sendOneWay(Message01.TOPIC, message);
    }
}
