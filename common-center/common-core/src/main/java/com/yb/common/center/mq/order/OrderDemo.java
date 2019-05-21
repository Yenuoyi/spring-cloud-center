package com.yb.common.center.mq.order;

/**
 * @author yebing
 */
public class OrderDemo {
    /** 订单id */
    private Long orderId;

    /** 订单操作 */
    private String desc;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
