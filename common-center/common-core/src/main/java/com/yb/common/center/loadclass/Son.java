package com.yb.common.center.loadclass;

/**
 * @author yebing
 */
public class Son extends Parent {
    private String name;

    static {
        System.out.println("This is Son static block!");
    }

    public Son() {
        System.out.println("This is Son construct!");
    }

    public void setNameMethod() {
        super.name = "yebingParent";
        this.name = "yebing";
        System.out.println(this.getName());
        System.out.println(this.name);
    }

    public static void method() {
        System.out.println("This is Son static method!");
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.setNameMethod();
        String[] strings = {};
        strings[0] = "1";
        strings[1] = "2";
        System.out.println(strings);

    }
}
