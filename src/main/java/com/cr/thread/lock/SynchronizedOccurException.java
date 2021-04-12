package com.cr.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * 异常释放锁
 */
public class SynchronizedOccurException {

    private int count;

    public synchronized void lock() {
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " " + count);
            if (count == 100) {
                int i = 1 / 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedOccurException soe = new SynchronizedOccurException();
        new Thread(() -> soe.lock()).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> soe.lock()).start();
    }

}
