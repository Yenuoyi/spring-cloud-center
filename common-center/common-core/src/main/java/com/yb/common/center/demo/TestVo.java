package com.yb.common.center.demo;

import com.yb.common.center.util.GeneratorId;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yebing
 */
@Vo(name = "class name")
public class TestVo {

    public TestVo(){
        try {
            Field name = this.getClass().getDeclaredField("name");
            Vo annotation = name.getAnnotation(Vo.class);
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
    @Vo(name = "hello")
    private String name;

    @Vo(name = "get hello")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[]  args){
        TestVo testVo = new TestVo();
        System.out.println("name:"+testVo.getName());
        Vo declaredAnnotation = null;
        Vo className = null;
        Vo method = null;
        try {
            boolean annotation = testVo.getClass().isAnnotationPresent(Vo.class);
            if(annotation == true){
                className = testVo.getClass().getAnnotation(Vo.class);
            }
            declaredAnnotation = testVo.getClass().getDeclaredField("name").getAnnotation(Vo.class);
            method = testVo.getClass().getDeclaredMethod("getName",null).getAnnotation(Vo.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("class:"+className.name());
        System.out.println("name:"+declaredAnnotation.name());
        System.out.println("method:"+method.name());

    }
}
