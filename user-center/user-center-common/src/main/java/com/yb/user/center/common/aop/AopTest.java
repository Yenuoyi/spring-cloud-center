package com.yb.user.center.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
        Object proceed = null;
        /* 方法名 */
        Signature signature = joinPoint.getSignature();
        /* 参数 */
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();
        System.out.println("kind:"+kind);
        for(int i=0;i<args.length;i++){
            System.out.println("args name"+":"+args[i].toString()+"  args value:"+args[i]);
        }
        System.out.println("signature:"+signature);
        try {
            proceed = joinPoint.proceed();
            Object target = joinPoint.getTarget();
            Class<?> aClass = target.getClass();
            /*注入方法名*/
            String name = signature.getName();
            Method declaredMethod = aClass.getMethod(name,Long.class);
            Parameter[] parameters = declaredMethod.getParameters();
            for(int j=0;j<parameters.length;j++){
                System.out.println("param:"+parameters[j]);
            }
            System.out.println();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
