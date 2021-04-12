package com.cr.thread.lock;

import java.util.concurrent.TimeUnit;

public class NoSynchronizedMethod {

    public synchronized void method1() {
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2() {
        System.out.println("method2 is invoke");
    }

    public static void main(String[] args) throws InterruptedException {
        NoSynchronizedMethod nsm = new NoSynchronizedMethod();
        new Thread(() -> nsm.method1()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> nsm.method2()).start();
    }

}
