package com.yb.common.center.mq.impl;

import com.yb.common.center.mq.service.AbstractProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author yebing
 */
public class ProducerClient extends AbstractProducer implements InitializingBean {
    public void createProducer() throws MQClientException {
        this.defaultMQProducer.setProducerGroup(this.producerGroup);
        this.defaultMQProducer.setNamesrvAddr(this.producerGroup);
        this.defaultMQProducer.start();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        createProducer();
    }

    @Override
    public SendResult sendMessage(Message msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = this.defaultMQProducer;
        SendResult send = producer.send(msg);
        return send;
    }
}
