package com.cr.thread.juc;

import com.cr.common.F;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierUsage {

    public static void main(String[] args) {
        demo4();
    }

    public static void demo1() {
        CyclicBarrier cb = new CyclicBarrier(2);
        new Thread(new Worker(cb), "t1").start();
        new Thread(new Worker(cb), "t2").start();
    }

    public static void demo2() {
        //由最后一个进入屏障的线程执行Runnable
        //所有线程都要等待Runnable执行完成才能继续
        CyclicBarrier cb = new CyclicBarrier(2, () -> {
            try {
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        new Thread(new Worker(cb), "t1").start();
        new Thread(new Worker(cb), "t2").start();
    }

    public static void demo3() {
        //线程等待超时报错TimeoutException
        //其他线程再次调用await方法会报BrokenBarrierException
        CyclicBarrier cb = new CyclicBarrier(2);
        new Thread(new Worker(cb, 1L), "t1").start();
        new Thread(new Worker(cb, 1L), "t2").start();
        new Thread(new Worker(cb, 1L), "t3").start();
    }

    @SneakyThrows
    //可重用
    public static void demo4(){
        CyclicBarrier cb = new CyclicBarrier(2);
        new Thread(new Worker(cb), "t1").start();
        new Thread(new Worker(cb), "t2").start();
        TimeUnit.SECONDS.sleep(20);
        new Thread(new Worker(cb), "t3").start();
        new Thread(new Worker(cb), "t4").start();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Worker implements Runnable {

        private CyclicBarrier cb;
        private Long timeout;

        public Worker(CyclicBarrier cb) {
            this.cb = cb;
        }

        @SneakyThrows
        @Override
        public void run() {
            int random = F.random(20);
            F.printThread("sleep " + random);
            TimeUnit.SECONDS.sleep(random);
            F.print("entering the barrier");
            if (Objects.isNull(timeout)) {
                cb.await();
            } else {
                try {
                    cb.await(timeout, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    F.print(e);
                } catch (BrokenBarrierException e) {
                    F.print(e);
                } catch (TimeoutException e) {
                    F.print(e);
                }
            }
            F.printThread("continue");
        }
    }

}
