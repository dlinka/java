package com.cr.thread.threadpool;

import java.util.concurrent.*;

public class ThreadPoolUsage {

    public static void demo1() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        for (int i = 0; i < 2; ++i) {
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            });
            System.out.println(service);
        }
        service.shutdown();
        System.out.printf("isTerminated = %b, isShutdown = %b%n", service.isTerminated(), service.isShutdown());
        System.out.println(service);
        TimeUnit.SECONDS.sleep(10);
        System.out.println(service);
    }

    public static void demo2() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        service.submit(() -> {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            });
            thread.start();
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(service);
        TimeUnit.SECONDS.sleep(15);
        System.out.println(service);
    }

    public static void demo3() throws InterruptedException {
        //阻塞队列使用SynchronousQueue
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i != 20; ++i) {
            executorService.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        TimeUnit.SECONDS.sleep(60);
        System.out.println(executorService);
    }

    public static void main(String[] args) throws InterruptedException {
        //demo1();
        //demo2();
        demo3();
    }

}
