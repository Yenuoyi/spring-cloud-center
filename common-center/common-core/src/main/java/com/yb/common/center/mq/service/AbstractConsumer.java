package com.yb.common.center.mq.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author yebing
 */
public abstract class AbstractConsumer {
    protected String namesrvAddr;
    protected ConsumeFromWhere consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
    protected MessageModel messageModel;
    protected String consumerGroup;
    protected String topic;
    protected String subExpression;
    protected int consumeThreadMin = 20;
    protected int consumeThreadMax = 64;
    protected int pullBatchSize = 32;
    protected MessageListenerConcurrently listenerConcurrently;
    protected MessageListenerOrderly listenerOrderly;
    protected DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer();

    public void start() throws MQClientException {
        mqConsumer.start();
    }

    public void shutdown() {
        mqConsumer.shutdown();
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public ConsumeFromWhere getConsumeFromWhere() {
        return consumeFromWhere;
    }

    public void setConsumeFromWhere(ConsumeFromWhere consumeFromWhere) {
        this.consumeFromWhere = consumeFromWhere;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }

    public void setMessageModel(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(String subExpression) {
        this.subExpression = subExpression;
    }

    public int getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public int getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public int getPullBatchSize() {
        return pullBatchSize;
    }

    public void setPullBatchSize(int pullBatchSize) {
        this.pullBatchSize = pullBatchSize;
    }

    public MessageListenerConcurrently getListenerConcurrently() {
        return listenerConcurrently;
    }

    public void setListenerConcurrently(MessageListenerConcurrently listenerConcurrently) {
        this.listenerConcurrently = listenerConcurrently;
    }

    public MessageListenerOrderly getListenerOrderly() {
        return listenerOrderly;
    }

    public void setListenerOrderly(MessageListenerOrderly listenerOrderly) {
        this.listenerOrderly = listenerOrderly;
    }

    public DefaultMQPushConsumer getMqConsumer() {
        return mqConsumer;
    }

    public void setMqConsumer(DefaultMQPushConsumer mqConsumer) {
        this.mqConsumer = mqConsumer;
    }
}
