package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureUsage {

    //使用静态方法创建任务
    public static void usage11() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 0);
        cf.join();
    }

    public static void usage12() {
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        //线程阻塞
        //cf.join();
        cf.complete(1);
        cf.join();
    }

    //null
    public static void usage13() {
        CompletableFuture<Integer> cf = CompletableFuture.completedFuture(null);
        Facility.print(cf.join());
    }

    //返回只会赋值一次,哪个先完成用哪个
    public static void usage14() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            Facility.printThread();
            Facility.sleep(2);
            return 2;
        });
        Facility.sleep(1);
        cf.complete(1);
        Facility.sleep(5);
        //返回值还是1
        Facility.print(cf.join());
    }

    //任务1>任务2>任务3
    public static void usage2() {
        CompletableFuture<Integer> cf1 = CompletableFuture.completedFuture(1);
        CompletableFuture<Integer> cf2 = cf1.thenApplyAsync(v -> 2);
        CompletableFuture<LocalDate> cf3 = cf2.thenApplyAsync(v -> LocalDate.now());
        Facility.print(cf1.join());
        Facility.print(cf2.join());
        Facility.print(cf3.join());
    }

    //任务1和任务2同时进行
    //获取返回结果再次执行任务
    public static void usage3() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "CR");
        CompletableFuture<LocalDate> cf3 = cf1.thenCombineAsync(cf2, (r1, r2) -> LocalDate.now());
        Facility.print(cf3.join());
    }

    //两个任务任一个完成,就执行一个新的任务
    public static void usage4() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> Facility.sleepRandom());
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> Facility.sleepRandom());
        CompletableFuture<String> cf = cf1.applyToEitherAsync(cf2, result -> String.valueOf(result * 10));
        Facility.print(cf.join());
    }

    //多个任务同时完成才能继续
    public static void usage5() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "CR");
        CompletableFuture<LocalDate> cf3 = CompletableFuture.supplyAsync(() -> LocalDate.now());
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cf1, cf2, cf3);
        allOf.join();
    }

    //多个任务有一个完成就能继续
    public static void usage6() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "CR");
        CompletableFuture<LocalDate> cf3 = CompletableFuture.supplyAsync(() -> LocalDate.now());
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(cf1, cf2, cf3);
        anyOf.join();
    }

    public static void main(String[] args) {
        usage2();
    }

}
