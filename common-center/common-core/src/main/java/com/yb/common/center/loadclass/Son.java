package com.yb.common.center.loadclass;

/**
 * @author yebing
 */
public class Son extends Parent {
    static{
        System.out.println("This is Son static block!");
    }

    public Son(){
        System.out.println("This is Son construct!");
    }

    public static void method(){
        System.out.println("This is Son static method!");
    }

    public static void main(String[] args){
        Son son = new Son();
    }
}
