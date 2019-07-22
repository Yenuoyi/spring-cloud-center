package com.yb.user.center.aop;

import java.util.Map;

/**
 * 数据传输服务
 */
public class DefaultTransmissionService implements TransmissionService{
    @Override
    public Map<String, Object> produceDataBeforeSendHttpReq() {
        return null;
    }

    @Override
    public void produceDataAfterSendHttpReq() {

    }

    @Override
    public void produceDataAfterAdvice() {

    }
}
