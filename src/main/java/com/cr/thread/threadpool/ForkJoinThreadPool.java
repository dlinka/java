package com.cr.thread.threadpool;

import com.cr.common.Facility;
import com.cr.common.User;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

public class ForkJoinThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long total = 0;
        long start = System.currentTimeMillis();
        for (long i = 1; i <= 50000000000L ; i++) {
            total+= i;
        }
        long end = System.currentTimeMillis();
        Facility.print("单线程执行时长       - {}", end-start);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        ComputingTask task = new ComputingTask(1, 50000000000L);
        long start1 = System.currentTimeMillis();
        ForkJoinTask<Long> submit = pool.submit(task);
        submit.get();
        long end1 = System.currentTimeMillis();
        Facility.print("ForkJoinPool执行时长- {}", end1-start1);

    }

    public static void test(User user){
        user = new User(1L, "a", 18);
    }
}
