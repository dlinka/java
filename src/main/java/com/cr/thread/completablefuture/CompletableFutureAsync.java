package com.cr.thread.completablefuture;

import com.cr.common.Facility;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAsync {

    //使用没有Async后缀的方法,会导致线程执行的不确定性
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
        cf.join();
    }

}
