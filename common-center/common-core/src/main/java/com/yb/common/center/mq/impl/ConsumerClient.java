package com.yb.common.center.mq.impl;


import com.yb.common.center.mq.service.AbstractConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author yebing
 */
public class ConsumerClient extends AbstractConsumer  implements InitializingBean {

    public void createConsumer() throws MQClientException {
        if(this.messageModel != null){
            this.mqConsumer.setMessageModel(this.messageModel);
        }
        this.mqConsumer.setConsumerGroup(this.consumerGroup);
        this.mqConsumer.setNamesrvAddr(this.namesrvAddr);
        this.mqConsumer.setConsumeFromWhere(this.consumeFromWhere);
        this.mqConsumer.subscribe(this.topic, this.subExpression);
        if(listenerConcurrently != null){
            this.mqConsumer.registerMessageListener(this.listenerConcurrently);
        }else if(listenerOrderly != null){
            this.mqConsumer.registerMessageListener(this.listenerOrderly);
        }
        this.mqConsumer.start();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.createConsumer();
    }
}
