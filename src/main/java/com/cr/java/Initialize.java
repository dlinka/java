package com.cr.java;

/**
 * 普通初始化块
 * 构造方法
 * val1 = 0
 * val2 = 2
 * 静态初始化块
 * 静态方法
 */
public class Initialize {
    private static Initialize initialize = new Initialize();

    public Initialize() {
        System.out.println("构造方法");
        System.out.println("val1 = " + val1);
        System.out.println("val2 = " + val2);
    }

    public static void staticFunction() {
        System.out.println("静态方法");
    }

    public static void main(String[] args) {
        staticFunction();
    }

    int val2 = 2;

    {
        System.out.println("普通初始化块");
    }

    private static int val1 = 1;

    static {
        System.out.println("静态初始化块");
    }

}
