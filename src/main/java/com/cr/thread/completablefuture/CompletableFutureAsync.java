package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAsync {

    //没有Async后缀的方法,线程执行的不确定性
    public static void demo1() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            //Facility.sleep(1);
            return 0;
        }).thenApply((i) -> {
            //如果开启上面注释,会打印ForkJoinPool.commonPool-worker-1
            //如果关闭上面注释,会打印main,这也就意味着main线程可能被阻塞
            Facility.printThread();
            return i;
        });
        Facility.print(cf.join());
    }

    //Async后缀的方法,都是使用ForkJoinPool.commonPool()中的线程执行
    public static void demo2() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 0)
                .thenApplyAsync(i -> {
                    Facility.printThread();
                    Facility.sleep(1000);
                    return i;
                });
        Facility.printThread();
    }

    //加强理解
    public static void demo3() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "CR1");
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            String result = "CR2";
            Facility.printThread(result);
            return result;
        });
        CompletableFuture<String> cf = cf1.applyToEither(cf2, result -> {
            Facility.printThread("main");
            return result;
        });
        cf.join();
    }

    public static void main(String[] args) {
        demo3();
    }

}
