package com.yb.common.center.mq.order;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yebing
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setDefaultTopicQueueNums(8);
        //Launch the instance.
        producer.start();
        // 订单列表
        List<OrderDemo> orderList =  buildOrders();
        for (int i = 0; i < orderList.size(); i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("OrderTopicTest","TagA", "KEY" + i,
                    ("Body " + JSONObject.toJSONString(orderList.get(i))).getBytes(RemotingHelper.DEFAULT_CHARSET));

            /* 使用 MessageQueueSelector 类来控制 把消息发往哪个 MessageQueue */
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("queue selector mq nums :" + mqs.size());
                    System.out.println("msg nfo msg toString()");
                    for (MessageQueue mq : mqs) {
                        System.out.println(mq.toString());
                    }
                    long id = (Long) arg;
                    long indexLong = id % mqs.size();
                    int index = (int) indexLong;
                    return mqs.get(index);
                    }
                }
                , orderList.get(i).getOrderId());

            System.out.printf("%s%n", sendResult+"Body:"+orderList.get(i));
        }
        producer.shutdown();
    }

    /**
     * 生成模拟订单数据
     */
    private static List<OrderDemo> buildOrders() {
        List<OrderDemo> orderList = new ArrayList<OrderDemo>();

        OrderDemo orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        return orderList;
    }
}
