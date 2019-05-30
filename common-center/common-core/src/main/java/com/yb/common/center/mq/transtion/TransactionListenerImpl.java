package com.yb.common.center.mq.transtion;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo class
 *
 * @author yebing
 * @date 2019/04/10
 */
public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<String, Integer>();

    /**
     * 用于在发送半消息成功时执行本地事务，成功返回成功状态，失败回滚事务,默认状态UNKNOW
     *
     * @param msg
     * @param arg
     * @return
     */
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        LocalTransactionState localTransactionState = LocalTransactionState.UNKNOW;
        try {
            int value = transactionIndex.getAndIncrement();
            int status = value % 3;
            localTrans.put(msg.getTransactionId(), status);
            localTransactionState = LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            localTransactionState = LocalTransactionState.ROLLBACK_MESSAGE;
            e.printStackTrace();
        }
        return localTransactionState;
    }


    /**
     * 用于检查本地事务状态并响应MQ检查请求
     *
     * @param msg
     * @return
     */
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
