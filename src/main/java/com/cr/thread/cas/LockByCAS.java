package com.cr.thread.cas;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CAS加锁
 * RocketMQ里面默认的情况就是使用CAS加锁实现的消息存储
 */
public class LockByCAS {

    //表示有多少个线程同时访问
    private int count = 10;
    private CountDownLatch latch = new CountDownLatch(count);
    private int num;

    //true表示无锁
    //false表示有锁
    private AtomicBoolean lock = new AtomicBoolean(true);

    public void lock() {
        boolean flag;
        do {
            flag = lock.compareAndSet(true, false);
        }
        while (!flag);
    }

    public void unLock() {
        lock.set(true);
    }

    public void demo() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            threads.add(new Thread(new Runnable() {

                @SneakyThrows
                public void run() {
                    lock();
                    for (int j = 0; j < 10000; j++) {
                        num++;
                    }
                    latch.countDown();
                    unLock();
                }

            }));
        }
        threads.forEach(thread -> thread.start());
        latch.await();
        System.out.println(num);
    }

    public static void main(String[] args) throws InterruptedException {
        LockByCAS cas = new LockByCAS();
        cas.demo();
    }


}
