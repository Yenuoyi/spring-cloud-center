package com.yb.common.center.loadclass;

/**
 * @author yebing
 */
public class Parent {
    static{
        System.out.println("This is parent static block!");
    }

    public Parent(){
        System.out.println("This is parent construct!");
    }

    public static void method(){
        System.out.println("This is parent static method!");
    }
}
