package com.yb.user.center.aop;

import java.util.Map;

public interface TransmissionService {
    /**
     * 没有找到相应的urlMapping
     */
    String NOT_URL_MAPPING_FIND = "not_url_mapping_find";

    /**
     * 在发送http请求之前执行方法,用于生成自定义的透传数据
     */
    default Map<String,Object> produceDataBeforeSendHttpReq() {
        return null;
    }

    /**
     * 在发送http请求之后执行方法,用于重新设置切入点的入参
     */
    void produceDataAfterSendHttpReq();

    /**
     * 在执行完切入点方法之后,还进行的后续操作(仅支持一个urlMapping,即一个http请求的时候才准确),isParseResponse,isExecBeforeProceed为同时true时生效
     */
    default void produceDataAfterAdvice() {

    }
}
