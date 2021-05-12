package com.cr.thread.future;

import com.cr.common.Facility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureUsage {

    public static void usage1() {
        CompletableFuture<Integer> cf1 = CompletableFuture.completedFuture(1);
        CompletableFuture<String> cf2 = cf1.thenApplyAsync(v -> "CompletableFuture");
        CompletableFuture<LocalDate> cf3 = cf2.thenApplyAsync(v -> LocalDate.now());
        Facility.print(cf1.join());
        Facility.print(cf2.join());
        Facility.print(cf3.join());
    }

    public static void usage2() {
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        //线程阻塞
        Facility.print(cf.join());
    }

    //null
    public static void usage3() {
        CompletableFuture<Integer> cf = CompletableFuture.completedFuture(null);
        Facility.print(cf.join());
    }

    //返回值变更
    public static void usage41() {
        CompletableFuture<LocalDateTime> cf = CompletableFuture
                .supplyAsync(() -> {
                    LocalDateTime now = LocalDateTime.now();
                    Facility.print(now);
                    return now;
                })
                .thenApplyAsync(t -> LocalDateTime.now());
        Facility.print(cf.join());
    }

    //返回值不变
    public static void usage42() {
        CompletableFuture<LocalDateTime> cf = CompletableFuture
                .supplyAsync(() -> LocalDateTime.now())
                .whenComplete((v, ex) -> v = null);
        Facility.print(cf.join());
    }

    public static void usage5() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 5; i++) {
                Facility.sleep(3);
                Facility.print("sleep");
            }
            return 10000;
        });
        Facility.sleep(1);
        cf.complete(1);
        Facility.sleep(10);
        //返回值还是1
        Facility.print(cf.join());
    }

    //线程执行不确定性
    public static void usage6() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                //TimeUnit.SECONDS.sleep(0);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            return 0;
        }).thenApply((i) -> {
            System.out.println(Thread.currentThread().getName());
            return "a";
        });
        future.get();
    }

    //main线程被阻塞
    public static void demo2() {
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> 0)
                .thenApply((i) -> {
                    try {
                        TimeUnit.SECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    return 1;
                });
        System.out.println("main running");
    }

    public static void main(String[] args) {
        //usage1();
        //Facility.printLine();
        //usage2();
        //Facility.printLine();
        //usage3();
        //Facility.printLine();
        //usage41();
        usage42();
    }

}
