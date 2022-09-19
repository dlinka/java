package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;

/**
 * 任务A，任务B，任务C中有一个任务执行完成就往下继续执行
 */
public class AnyOf {

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

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2, future3);
        Facility.print(anyOf.join());
    }

}
