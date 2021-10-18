package com.cr.thread.juc;

import com.cr.common.Facility;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchUsage {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread0 = new Thread(() -> {
            Facility.printThread("start");
            Facility.sleep(2);
            Facility.printThread("end");
            latch.countDown();
        });
        thread0.start();

        Thread thread1 = new Thread(() -> {
            Facility.printThread("start");
            Facility.sleep(4);
            Facility.printThread("end");
            latch.countDown();
        });
        thread1.start();

        latch.await();
        Facility.printThread();
    }

}
