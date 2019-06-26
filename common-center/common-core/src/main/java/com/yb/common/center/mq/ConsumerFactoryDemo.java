package com.yb.common.center.mq;

import com.yb.common.center.mq.impl.ConsumerClient;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yebing
 */
@Configuration
public class ConsumerFactoryDemo {
    @Bean
    public ConsumerClient createTest() {
        ConsumerClient consumerClient = new ConsumerClient();
        consumerClient.setConsumerGroup("consumer");
        consumerClient.setNamesrvAddr("127.0.0.1:9876");
        consumerClient.setTopic("TopicTest");
        consumerClient.setSubExpression("*");
        consumerClient.setListenerConcurrently(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + list);
                //返回消费状态
                //CONSUME_SUCCESS 消费成功
                //RECONSUME_LATER 消费失败，需要稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        return consumerClient;
    }
}
