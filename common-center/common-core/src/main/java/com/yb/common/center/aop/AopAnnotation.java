package com.yb.common.center.aop;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface AopAnnotation {
    String name() default "hello";

    String strategy() default "com.yb.common.center.util.GeneratorId";
}
