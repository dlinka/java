package com.cr.thread.lock;

public class SynchronizedInherit {

    public static void main(String[] args) {
        Child child = new Child();
        child.method2();
    }

}

class Parent {
    public synchronized void method1() {
        System.out.println("method1");
    }
}

class Child extends Parent {
    public synchronized void method2() {
        System.out.println("method2");
        //synchronized可重入锁也适用调用父类方法
        method1();
    }
}

