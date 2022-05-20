package com.cr.thread.threadpool;

import com.cr.common.Facility;

import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class ComputingTask extends RecursiveTask<Long> {

    private long begin;
    private long end;

    public ComputingTask(long begin, long end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long diff = end - begin;
        //大于80000,fork出两个任务
        if (diff > 800000) {
            long mid = (begin + end) / 2;
            ComputingTask task1 = new ComputingTask(begin, mid);
            ComputingTask task2 = new ComputingTask(mid + 1, end);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        } else {
            long num = 0;
            for (long i = begin; i <= end; ++i) {
                num += i;
            }
            return num;
        }
    }

}
