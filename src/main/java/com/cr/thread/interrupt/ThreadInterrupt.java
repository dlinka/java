package com.cr.thread.interrupt;

import java.util.concurrent.TimeUnit;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.sleep;

public class ThreadInterrupt {

    //interrupt只会改变线程的中断状态
    public static void demo1() {
        Thread thread = new Thread(() -> {
            while (true) {
                print("thread interrupt - {}", Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        sleep(1);
        //thread不会停止
        thread.interrupt();
    }

    //catch异常处理中断状态后会被恢复
    public static void demo2() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    print("thread interrupt - {}", Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    print("catch thread interrupt - {}", Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        sleep(5);
        //这里调用interrupt会导致上面sleep抛出异常
        thread.interrupt();
    }

    public static void demo3() {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                print("thread interrupt - {}", Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        sleep(1);
        thread.interrupt();
    }

    public static void main(String[] args) {
        //demo1();
        //demo2();
        demo3();
    }

}
