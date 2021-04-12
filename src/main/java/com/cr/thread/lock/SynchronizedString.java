package com.cr.thread.lock;

import java.util.concurrent.TimeUnit;

public class SynchronizedString {

    //s1和s2一把锁
    private String s1 = "cr";
    private String s2 = "cr";

    public void method1() {
        synchronized (s1) {
            System.out.println("s1 lock");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {
        synchronized (s2) {
            System.out.println("s2 lock");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedString ls = new SynchronizedString();
        new Thread(() -> ls.method1()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> ls.method2()).start();
    }
}
