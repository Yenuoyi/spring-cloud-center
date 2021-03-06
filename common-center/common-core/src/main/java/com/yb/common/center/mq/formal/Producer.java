package com.yb.common.center.mq.formal;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Demo class
 *
 * @author yebing
 * @date 2019/04/10
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {

        /* 声明并初始化一个producer ,需要一个producer group名字作为构造方法的参数，这里为producer1 */
        DefaultMQProducer producer = new DefaultMQProducer("producer1");

        /* 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔 */
        /* NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里 */
        producer.setNamesrvAddr("127.0.0.1:9876");
        /* 发送失败重试次数 */
        producer.setRetryTimesWhenSendFailed(3);
        /* 调用start()方法启动一个producer实例 */
        producer.start();

        /*  发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值 */
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest",// topic
                        "TagA",// tag
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)// body
                );

                /*延迟消息的使用方法是在 Message 时，调用 setDelayTimeLevel ( int
                level 方法设 延迟时间， 然后再把这个消息发送 目前延迟的时间不
                持任 设置，仅 持预 值的时间长度 1 s/5s/1 Os/30s/I m/2m/3m/4m/5m/6m/
                        7m/8m/9m/1 Om/20m/30m/1 2h 比如 setDelayTimeLevel(3 示延迟 10s*/
                msg.setDelayTimeLevel(3);

                /* 调用producer的send()方法发送消息,这里调用的是同步的方式，所以会有返回结果 */
                SendResult sendResult = producer.send(msg);
                /* 打印返回结果，可以看到消息发送的状态以及一些相关信息 */
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        //发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();
    }
}
