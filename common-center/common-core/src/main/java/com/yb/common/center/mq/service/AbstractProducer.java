package com.yb.common.center.mq.service;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author yebing
 */
public abstract class AbstractProducer {
    protected transient DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
    protected String producerGroup;
    protected String namesrvAddr;
    protected String createTopicKey;
    protected volatile int defaultTopicQueueNums;
    protected int sendMsgTimeout;
    protected int compressMsgBodyOverHowmuch;
    protected int retryTimesWhenSendFailed;
    protected int retryTimesWhenSendAsyncFailed;
    protected boolean retryAnotherBrokerWhenNotStoreOK;
    protected int maxMessageSize;

    public AbstractProducer() {
        this.createTopicKey = "TBW102";
        this.defaultTopicQueueNums = 4;
        this.sendMsgTimeout = 3000;
        this.compressMsgBodyOverHowmuch = 4096;
        this.retryTimesWhenSendFailed = 2;
        this.retryTimesWhenSendAsyncFailed = 2;
        this.retryAnotherBrokerWhenNotStoreOK = false;
        this.maxMessageSize = 4194304;
    }

    public void start() throws MQClientException {
        defaultMQProducer.start();
    }

    public void shutdown() {
        defaultMQProducer.shutdown();
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }

    public void setDefaultMQProducer(DefaultMQProducer defaultMQProducer) {
        this.defaultMQProducer = defaultMQProducer;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getCreateTopicKey() {
        return createTopicKey;
    }

    public void setCreateTopicKey(String createTopicKey) {
        this.createTopicKey = createTopicKey;
    }

    public int getDefaultTopicQueueNums() {
        return defaultTopicQueueNums;
    }

    public void setDefaultTopicQueueNums(int defaultTopicQueueNums) {
        this.defaultTopicQueueNums = defaultTopicQueueNums;
    }

    public int getSendMsgTimeout() {
        return sendMsgTimeout;
    }

    public void setSendMsgTimeout(int sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }

    public int getCompressMsgBodyOverHowmuch() {
        return compressMsgBodyOverHowmuch;
    }

    public void setCompressMsgBodyOverHowmuch(int compressMsgBodyOverHowmuch) {
        this.compressMsgBodyOverHowmuch = compressMsgBodyOverHowmuch;
    }

    public int getRetryTimesWhenSendFailed() {
        return retryTimesWhenSendFailed;
    }

    public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }

    public int getRetryTimesWhenSendAsyncFailed() {
        return retryTimesWhenSendAsyncFailed;
    }

    public void setRetryTimesWhenSendAsyncFailed(int retryTimesWhenSendAsyncFailed) {
        this.retryTimesWhenSendAsyncFailed = retryTimesWhenSendAsyncFailed;
    }

    public boolean isRetryAnotherBrokerWhenNotStoreOK() {
        return retryAnotherBrokerWhenNotStoreOK;
    }

    public void setRetryAnotherBrokerWhenNotStoreOK(boolean retryAnotherBrokerWhenNotStoreOK) {
        this.retryAnotherBrokerWhenNotStoreOK = retryAnotherBrokerWhenNotStoreOK;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    /**
     * 发送mq消息
     *
     * @param msg
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public abstract SendResult sendMessage(Message msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException;
}
