package com.cr.thread.juc.lock;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUsage {

    public static void demo1() {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }).start();
    }

    //tryLock
    public static void demo2() {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            boolean result = lock.tryLock();
            try {
                if (result) {
                    System.out.println("lock");
                } else {
                    System.out.println("not lock");
                }
            } finally {
                if (result) lock.unlock();
            }
        }).start();
    }

    //公平锁和非公平锁
    public static void demo3() {
        //T1和T2交替打印
        //ReentrantLock lock = new ReentrantLock(true);

        //T1打印完后,T2再打印
        //可以参考下AQS源码
        ReentrantLock lock = new ReentrantLock(false);

        new Thread(() -> {
            for (int i = 0; i != 10; ++i) {
                lock.lock();
                try {
                    System.out.printf("%s %s %d%n", LocalDateTime.now(), Thread.currentThread().getName(), i);
                } finally {
                    lock.unlock();
                }
            }
        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i != 10; ++i) {
                lock.lock();
                try {
                    System.out.printf("%s %s %d%n", LocalDateTime.now(), Thread.currentThread().getName(), i);
                } finally {
                    lock.unlock();
                }
            }
        }, "T2").start();
    }

    @SneakyThrows
    //lockInterruptibly
    public static void demo4() {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            boolean flag = false;
            try {
                lock.lockInterruptibly();
                flag = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (flag) { lock.unlock(); }
            }
        });
        t2.start();

        TimeUnit.SECONDS.sleep(2);
        t2.interrupt();
    }

    public static void main(String[] args) {
        demo4();
    }

}
