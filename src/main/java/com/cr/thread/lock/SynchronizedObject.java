package com.cr.thread.lock;

import java.util.concurrent.TimeUnit;

public class SynchronizedObject {

    public synchronized void method1() throws InterruptedException {
        System.out.println("method1");
        TimeUnit.DAYS.sleep(1);
    }

    public synchronized void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedObject so = new SynchronizedObject();
        new Thread(() -> {
            try {
                so.method1();
            } catch (InterruptedException e) {
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> so.method2()).start();
    }

}
