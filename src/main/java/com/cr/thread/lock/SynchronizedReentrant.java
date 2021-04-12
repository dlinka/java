package com.cr.thread.lock;

/**
 * 可重入锁
 */
public class SynchronizedReentrant {

    synchronized void method1(){
        method2();
    }

    synchronized void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        SynchronizedReentrant sr = new SynchronizedReentrant();
        sr.method1();
    }

}
