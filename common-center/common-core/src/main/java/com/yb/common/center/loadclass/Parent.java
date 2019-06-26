package com.yb.common.center.loadclass;

/**
 * @author yebing
 */
public class Parent {
    protected String name;
    static{
        System.out.println("This is parent static block!");
    }

    public Parent(){
        System.out.println("This is parent construct!");
    }

    public static void method(){
        System.out.println("This is parent static method!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
