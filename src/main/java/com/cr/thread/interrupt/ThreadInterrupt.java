package com.cr.thread.interrupt;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        //demo1();
        //demo2();
        demo3();
    }

    public static void demo1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.printf("%s-%s%n", LocalDateTime.now(), Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        //t1不会停止
        //interrupt只会改变线程的中断状态
        t1.interrupt();
    }

    public static void demo2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.printf("%s-%s%n", LocalDateTime.now(), Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        //这里调用interrupt会导致上面sleep抛出异常
        //catch异常处理中断状态会被恢复
        t1.interrupt();
    }

    public static void demo3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.printf("%s%n", LocalDateTime.now());
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }

}
