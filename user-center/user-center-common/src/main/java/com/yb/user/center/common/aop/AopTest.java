package com.yb.user.center.common.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yebing
 */
@Aspect
@Component
public class AopTest {

    @Pointcut(value = "execution (* com.yb.user.center.service.impl..*.*(..)) || execution (* com.yb.common.center.basicmethod.*ServiceImpl.*(..))")
    public void aopPointCut(){
        System.out.println("进入pointcut！");

    }

    @Around(value = "aopPointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        Map<String,Object> map = new HashMap<>(16);
        Object proceed = null;
        try {
            /* 方法名 */
            CodeSignature signature = (CodeSignature)joinPoint.getSignature();
            /*参数名*/
            String[] parameterNames = signature.getParameterNames();
            /* 参数值 */
            Object[] args = joinPoint.getArgs();
            String kind = joinPoint.getKind();
            System.out.println("kind:"+kind);
            /*注入map*/
            for(int i=0;i<args.length;i++){
                map.put(parameterNames[i],args[i]);
            }
            System.out.println("signature:"+ JSONObject.toJSONString(signature));
            System.out.println("map:"+ JSONObject.toJSONString(map));
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
