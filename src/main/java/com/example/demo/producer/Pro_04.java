package com.example.demo.producer;

import com.example.demo.message.Message01;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 顺序消息
 *
 * @author wll
 * @desc Pro_04
 * @link
 * @date 2020/5/21 8:52 下午
 *
 *
 *
 * 在 RocketMQ 中，Producer 可以根据定义 MessageQueueSelector 消息队列选择策略，选择 Topic 下的队列。目前提供三种策略：
 *
 * SelectMessageQueueByHash ，基于 hashKey 的哈希值取余，选择对应的队列。
 * SelectMessageQueueByRandom ，基于随机的策略，选择队列。
 * SelectMessageQueueByMachineRoom ，😈 有点看不懂，目前是空的实现，暂时无视吧。
 * 未使用 MessageQueueSelector 时，采用轮询的策略，选择队列。
 *
 * RocketMQTemplate 在发送顺序消息时，默认采用 SelectMessageQueueByHash 策略。如此，相同的 hashKey 的消息，就可以发送到相同的 Topic 的对应队列中。这种形式，就是我们上文提到的普通顺序消息的方式。
 */
@Component
public class Pro_04 {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 顺序消息 -- 同步
    public SendResult syncSendOrderly(Integer id) {

        Message01 msg = new Message01().setId(id);

        return rocketMQTemplate.syncSendOrderly(Message01.TOPIC, msg, String.valueOf(id));
    }

    // 顺序消息 -- 异步
    public void asyncSendOrderly(Integer id, SendCallback callback) {

        Message01 msg = new Message01().setId(id);

        rocketMQTemplate.asyncSendOrderly(Message01.TOPIC,msg,String.valueOf(id),callback);
    }

    // 顺序消息 -- oneway
    public void onewaySendOrderly(Integer id) {

        Message01 msg = new Message01().setId(id);
        rocketMQTemplate.sendOneWayOrderly(Message01.TOPIC,msg,String.valueOf(id));

    }

}
