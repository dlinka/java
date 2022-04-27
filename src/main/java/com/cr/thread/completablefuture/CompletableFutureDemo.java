package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    public static void main(String[] args) {

    }

    /**
     * 源码阅读
     */
    public static void demo() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            Facility.sleep(1);
            Facility.printThread("1");
            return 1;
        }, executorService);

        CompletableFuture<Integer> future2 = future1.thenApply(i -> {
            Facility.printThread("2");
            return i + 2;
        });
        CompletableFuture<Integer> future3 = future1.thenApply(i -> {
            Facility.printThread("3");
            return i + 3;
        });
        CompletableFuture<Integer> future4 = future1.thenApply(i -> {
            Facility.printThread("4");
            return i + 4;
        });

        CompletableFuture<Integer> future31 = future3.thenApply(i -> {
            Facility.printThread("31");
            return i + 31;
        });
        CompletableFuture<Integer> future32 = future3.thenApply(i -> {
            Facility.printThread("32");
            return i + 32;
        });
        CompletableFuture<Integer> future33 = future3.thenApply(i -> {
            Facility.printThread("33");
            return i + 33;
        });

        CompletableFuture<Integer> future41 = future4.thenApply(i -> {
            Facility.printThread("41");
            return i + 41;
        });
        CompletableFuture<Integer> future42 = future4.thenApply(i -> {
            Facility.printThread("42");
            return i + 42;
        });
        CompletableFuture<Integer> future43 = future4.thenApply(i -> {
            Facility.printThread("43");
            return i + 43;
        });

        Facility.print("future1 - {}", future1);
        Facility.print("future2 - {}", future2);
        Facility.print("future3 - {}", future3);
        Facility.print("future4 - {}", future4);
        Facility.print("future31 - {}", future31);
        Facility.print("future32 - {}", future32);
        Facility.print("future33 - {}", future33);
        Facility.print("future41 - {}", future41);
        Facility.print("future42 - {}", future42);
        Facility.print("future43 - {}", future43);

        Facility.print("主线程结束");
        Facility.sleep(6);

        Facility.print("执行结果" + future1.join());
        Facility.print("执行结果" + future2.join());
        Facility.print("执行结果" + future3.join());
        Facility.print("执行结果" + future4.join());
        Facility.print("执行结果" + future31.join());
        Facility.print("执行结果" + future32.join());
        Facility.print("执行结果" + future33.join());
        Facility.print("执行结果" + future41.join());
        Facility.print("执行结果" + future42.join());
        Facility.print("执行结果" + future43.join());
    }

    /**
     * 实例化
     * 1.使用new实例化
     * 2.使用静态方法实例化,默认使用ForkJoinPool线程池
     * 3.创建一个已经完成了的异步任务
     */
    public void initialize(){
        //1.
        CompletableFuture<Integer> cf1 = new CompletableFuture<>();
        //完成这个任务
        cf1.complete(1);
        Facility.print(cf1.join());

        //2.
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            Facility.printThreadName();
            Facility.print("没有返回值的异步任务");
        });
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            Facility.print("有返回值的异步任务");
            return 1;
        });

        //3.
        CompletableFuture<Integer> cf4 = CompletableFuture.completedFuture(null);
        Facility.print(cf4.join());
    }

    /**
     * 返回只会赋值一次,哪个先完成用哪个
     */
    public static void demo1() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            Facility.sleep(2);
            return 2;
        });
        Facility.sleep(1);
        cf.complete(1);
        Facility.sleep(3);
        Facility.print(cf.join());
    }

}
