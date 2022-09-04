package com.cr.thread.threadpool;

import com.cr.common.Facility;

import java.util.concurrent.*;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.sleep;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        demo0();
        //demo1();
        //demo2();
        //demo3();
    }

    /**
     * 测试线程池核心线程和最大线程什么时候创建
     * 1.创建核心线程
     * 2.任务放到队列中
     * 3.队列放不下,再创建线程执行任务
     */
    public static void demo0(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            threadPoolExecutor.submit(()-> {
                Facility.sleep(10);
                Facility.print(finalI);
            });
            print(threadPoolExecutor);
        }
    }


    public static void demo1() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        for (int i = 0; i < 2; ++i) {
            service.execute(() -> {
                sleep(100);
            });
            print(service);
        }

        service.shutdown();
        print(service);
        print("isTerminated = {}, isShutdown = {}", service.isTerminated(), service.isShutdown());

        //TimeUnit.SECONDS.sleep(10);
        print(service);
        print("isTerminated = {}, isShutdown = {}", service.isTerminated(), service.isShutdown());
    }

    public static void demo2() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

        service.submit(() ->
                new Thread(() -> {
                    sleep(2);
                    Facility.printThreadName();
                }).start()
        );
        print(service);
    }

    //阻塞队列使用SynchronousQueue
    public static void demo3() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i != 20; ++i) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                print(Thread.currentThread().getName());
            });
        }

        TimeUnit.SECONDS.sleep(30);
        print(executorService);
    }

}
