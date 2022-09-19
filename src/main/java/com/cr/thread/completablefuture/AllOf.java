package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;

/**
 * 任务A，任务B，任务C并行运行，等待所有任务执行完成，再往下继续执行
 */
public class AllOf {

    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            Facility.sleepRandom();
            return Facility.random(100);
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
            Facility.sleepRandom();
            return Facility.random(100);
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(()->{
            Facility.sleepRandom();
            return Facility.random(100);
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);
        allOf.join();
    }

}
