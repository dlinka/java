package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExceptionDemo {

    public static void main(String[] args) {
        //CompletableFutureExceptionDemo.throwException();
        CompletableFutureExceptionDemo.exceptionally();
    }

    /**
     * 异常抛出后,调用链后面不会执行
     */
    public static void throwException() {
        CompletableFuture<Integer> cf = CompletableFuture.completedFuture(1)
                .thenApply(v -> {
                    if (v == 1)
                        throw new RuntimeException();
                    return 2;
                }).thenApply(v -> {
                    //这里不会打印
                    Facility.print(v);
                    return 3;
                });
        cf.join();
    }

    /**
     * exceptionally
     */
    public static void exceptionally() {
        CompletableFuture<Integer> cf = CompletableFuture
                .completedFuture(1)
                .thenApply((v) -> {
                    if (v == 1) {
                        throw new RuntimeException("cr");
                    }
                    return 2;
                }).exceptionally(ex -> {
                    Facility.print(ex.getMessage());
                    return 3;
                });
        Facility.print(cf.join());
    }

    /**
     * 没有异常exceptionally不会执行
     */
    public static void exceptionallyOfNoException() {
        CompletableFuture<Integer> cf = CompletableFuture
                .completedFuture(1)
                .thenApplyAsync(v -> 2)
                .exceptionally(ex -> 3);
        Facility.print(cf.join());
    }

    /**
     * handle
     */
    public static void handle() {
        CompletableFuture<String> cf = CompletableFuture
                .supplyAsync(() -> {
                    if (Facility.random(10) % 2 == 0) throw new RuntimeException("异常");
                    return 1;
                })
                .handle((i, ex) -> {
                    Facility.print(i);
                    Optional.ofNullable(ex).ifPresent(e -> Facility.print(e.getMessage()));
                    return "CR";
                });
        Facility.print(cf.join());
    }

}
