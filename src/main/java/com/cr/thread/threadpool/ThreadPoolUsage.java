package com.cr.thread.threadpool;

import com.cr.common.Facility;

import java.util.concurrent.*;

public class ThreadPoolUsage {

    public static void demo1() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        for (int i = 0; i < 2; ++i) {
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    Facility.print(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            });
            Facility.print(service);
        }
        service.shutdown();
        Facility.print(service);
        Facility.print("isTerminated = {}, isShutdown = {}", service.isTerminated(), service.isShutdown());
        TimeUnit.SECONDS.sleep(20);
        Facility.print(service);
    }

    public static void demo2() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        service.submit(() -> {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    Facility.print(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            });
            thread.start();
        });
        TimeUnit.SECONDS.sleep(1);
        Facility.print(service);
        TimeUnit.SECONDS.sleep(15);
        Facility.print(service);
    }

    //阻塞队列使用SynchronousQueue
    public static void demo3() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i != 20; ++i) {
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                Facility.print(Thread.currentThread().getName());
            });
        }

        TimeUnit.SECONDS.sleep(30);
        Facility.print(executorService);
    }

    public static void main(String[] args) throws InterruptedException {
        demo1();
        //demo2();
        //demo3();
    }

}
