package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thenCombine
 * 任务A和任务B并行执行，然后将两个任务结果传入任务C
 */
public class ThenCombine {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            Facility.sleep(1);
            return Facility.random(10);
        }, executorService);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            Facility.sleep(1);
            return Facility.random(10);
        }, executorService);

        CompletableFuture<Integer> future3 = future1.thenCombineAsync(future2, (i1, i2) -> {
            Facility.sleep(1);
            return Facility.random(i1 + i2);
        }, executorService);

        Facility.print(future1.join());
        Facility.print(future2.join());
        Facility.print(future3.join());
    }

}
