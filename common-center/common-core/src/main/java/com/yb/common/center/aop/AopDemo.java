package com.yb.common.center.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author by yebing
 * @Date 2019-06-26
 */
@AopAnnotation(name = "class name")
public class AopDemo {
    public AopDemo(){
        try {
            Field name = this.getClass().getDeclaredField("name");
            AopAnnotation annotation = name.getAnnotation(AopAnnotation.class);
            String strategy = annotation.strategy();
            Class aClass = Class.forName(strategy);
            Method declaredMethod = aClass.getMethod("getSequenceByPrefixAndLength",String.class,Integer.class);
            String vvv = (String)declaredMethod.invoke(null, "VVV", 16);
            this.setName(vvv);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @AopAnnotation(name = "hello")
    private String name;

    @AopAnnotation(name = "get hello")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[]  args){
        AopDemo aopDemo = new AopDemo();
        System.out.println("name:"+ aopDemo.getName());
        AopAnnotation declaredAnnotation = null;
        AopAnnotation className = null;
        AopAnnotation method = null;
        try {
            boolean annotation = aopDemo.getClass().isAnnotationPresent(AopAnnotation.class);
            if(annotation == true){
                className = aopDemo.getClass().getAnnotation(AopAnnotation.class);
            }
            declaredAnnotation = aopDemo.getClass().getDeclaredField("name").getAnnotation(AopAnnotation.class);
            method = aopDemo.getClass().getDeclaredMethod("getName",null).getAnnotation(AopAnnotation.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("class:"+className.name());
        System.out.println("name:"+declaredAnnotation.name());
        System.out.println("method.name:"+method.name());

    }
}
