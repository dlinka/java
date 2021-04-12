package com.cr.thread.lock;

import java.util.concurrent.TimeUnit;

public class SynchronizedObjectChange {

    private Object obj = new Object();

    public void method() {
        synchronized (obj) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedObjectChange soc = new SynchronizedObjectChange();

        new Thread(() -> soc.method()).start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(() -> soc.method()).start();

        //改变锁对象
        soc.setObj(new Object());
    }

}
