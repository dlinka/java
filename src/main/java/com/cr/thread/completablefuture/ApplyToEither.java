package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务A，任务B有一个完成了，执行任务C
 */
public class ApplyToEither {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            Facility.sleepRandom();
            Facility.print("A");
            return Facility.random(100);
        }, executorService);

        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
            Facility.sleepRandom();
            Facility.print("B");
            return Facility.random(100);
        }, executorService);

        CompletableFuture<String> futureC = futureA.applyToEitherAsync(futureB, result -> {
            Facility.print("C");
            Facility.print(result);
            return "applyToEither";
        }, executorService);

        Facility.print(futureA.join());
        Facility.print(futureB.join());
        Facility.print(futureC.join());
    }

}
