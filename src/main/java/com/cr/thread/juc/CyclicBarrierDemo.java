package com.cr.thread.juc;

import com.cr.common.Facility;
import com.cr.common.JUCUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        demo4();
    }

    public static void demo1() {
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(() -> {
            Facility.print("线程1开始执行");
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程1执行完毕");
        }).start();

        new Thread(() -> {
            Facility.print("线程3开始执行");
            Facility.sleep(3);
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程3执行完毕");
        }).start();
    }

    public static void demo2() {
        /**
         * 最后一个进入屏障的线程执行Runnable
         * 所有线程都要等待Runnable执行完成才能继续
         */
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            Facility.printThreadName();
        });

        new Thread(() -> {
            Facility.print("线程1开始执行");
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程1结束执行");
        }, "线程1").start();

        new Thread(() -> {
            Facility.print("线程2开始执行");
            Facility.sleep(5);
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程2结束执行");
        }, "线程2").start();
    }

    public static void demo3() {
        /**
         * 线程等待超时报错TimeoutException
         * 再次调用await方法报BrokenBarrierException,栅栏用不了了
         */
        CyclicBarrier barrier = new CyclicBarrier(3);
        new Thread(() -> {
            Facility.print("线程11开始执行");
            JUCUtil.barrierAwait(barrier, 2L);
            Facility.print("线程11结束执行");
        }, "线程11").start();

        new Thread(() -> {
            Facility.print("线程12开始执行");
            JUCUtil.barrierAwait(barrier, 2L);
            Facility.print("线程12结束执行");
        }, "线程12").start();


        new Thread(() -> {
            Facility.print("线程13开始执行");
            Facility.sleep(3);
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程13结束执行");
        }, "线程13").start();

        Facility.sleep(5);
        new Thread(() -> {
            Facility.print("线程2开始执行");
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程2结束执行");
        }, "线程2").start();
    }

    public static void demo4() {
        CyclicBarrier barrier = new CyclicBarrier(2);

        new Thread(() -> {
            Facility.print("线程1开始执行");
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程1执行完毕");
        }).start();

        new Thread(() -> {
            Facility.print("线程2开始执行");
            Facility.sleep(1);
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程2执行完毕");
        }).start();

        Facility.sleep(2);

        new Thread(() -> {
            Facility.print("线程3开始执行");
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程3执行完毕");
        }).start();

        new Thread(() -> {
            Facility.print("线程4开始执行");
            Facility.sleep(1);
            JUCUtil.barrierAwait(barrier);
            Facility.print("线程4执行完毕");
        }).start();
    }

}
