package com.cr.thread.future;

import com.cr.common.Facility;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureException {

    //不调用join,控制台不会报错
    public static void usage1() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            int random = Facility.random(10);
            Facility.print(random);
            if (random % 2 == 0) {
                throw new RuntimeException();
            }
            return random;
        });
        //cf.join();
    }

    //异常抛出后,调用链后面不会执行
    public static void usage2() {
        CompletableFuture<Integer> cf = CompletableFuture
                .completedFuture(1)
                .thenApplyAsync(v -> {
                    if (v == 1)
                        throw new RuntimeException();
                    return 2;
                }).thenApplyAsync(v -> {
                    //这里不会打印
                    Facility.print(v);
                    return 3;
                });
        Facility.print(cf.join());
    }

    //exceptionally
    public static void usage31() {
        CompletableFuture<Integer> cf = CompletableFuture
                .completedFuture(1)
                .thenApplyAsync((v) -> {
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

    //没有异常exceptionally不会执行
    public static void usage32() {
        CompletableFuture<Integer> cf = CompletableFuture
                .completedFuture(1)
                .thenApplyAsync(v -> 2)
                .exceptionally(ex -> 3);
        Facility.print(cf.join());
    }

    //handle
    public static void usage4() {
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> {
                    if (Facility.random(10) % 2 == 0) throw new RuntimeException("usage 4");
                    return 1;
                })
                .handleAsync((i, ex) -> {
                    Facility.print(i);
                    Optional.ofNullable(ex).ifPresent(e -> Facility.print(e.getMessage()));
                    return 2;
                });
        Facility.print(cf.join());
    }

    public static void main(String[] args) {
        //usage1();
        //Facility.printLine();
        //usage2();
        //Facility.printLine();
        //usage31();
        //Facility.printLine();
        //usage32();
        //Facility.printLine();
        usage4();
        Facility.printLine();
    }

}
