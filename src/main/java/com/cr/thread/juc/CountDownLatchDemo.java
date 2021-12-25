package com.cr.thread.juc;

import com.cr.common.Facility;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            Facility.sleep(2);
            latch.countDown();
        }).start();

        new Thread(() -> {
            Facility.sleep(4);
            latch.countDown();
        }).start();

        latch.await();
        Facility.printThreadName();
    }

}
