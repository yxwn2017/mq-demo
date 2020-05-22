package com.example.demo.consumer;

import com.example.demo.message.Message01;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wll
 * @desc Msg_01_Consumer
 * @link
 * @date 2020/5/22 2:18 下午
 */
@Component
@RocketMQMessageListener(
        topic = Message01.TOPIC,
        consumerGroup = "msg-01-consumer-group-" + Message01.TOPIC
//       , messageModel = MessageModel.BROADCASTING // 广播模式 case1
//        , consumeMode = ConsumeMode.ORDERLY  // 顺序消费 case2
)
public class Msg_01_Consumer implements RocketMQListener<Message01> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message01 message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

        //case3 重试消息16次 == 延时消息等级有关
        //测试重试 不过要注意，只有集群消费模式下，才有消息重试。
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
//        throw new RuntimeException("我就是故意抛出一个异常");


        //case2  sleep 2 秒，用于查看顺序消费的效果
//        try {
//            Thread.sleep(2 * 1000L);
//        } catch (InterruptedException ignore) {
//        }
    }

}
