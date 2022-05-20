package com.cr.thread.threadpool;

import com.cr.common.Facility;

import java.util.concurrent.*;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.sleep;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        //demo1();
        //demo2();
        //demo3();
    }

    public static void demo1() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        for (int i = 0; i < 2; ++i) {
            service.execute(() -> {
                sleep(2);
            });
            print(service);
        }

        service.shutdown();
        print(service);
        print("isTerminated = {}, isShutdown = {}", service.isTerminated(), service.isShutdown());

        TimeUnit.SECONDS.sleep(5);
        print("isTerminated = {}, isShutdown = {}", service.isTerminated(), service.isShutdown());

        print(service);
    }

    public static void demo2() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        service.submit(() -> new Thread(() -> {
            sleep(2);
            Facility.printThreadName();
        }).start());
        print(service);
        print(service);
        print(service);

        sleep(4);
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
