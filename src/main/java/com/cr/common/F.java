package com.cr.common;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

/**
 * 便捷类Facility
 */
@Slf4j
public class F {

    public static void print(Exception e){
        log.error(e.toString(), e);
    }

    public static void print(Object o){
        log.info("{}", o);
    }

    public static void print(String format, Object... o){
        log.info(format, o);
    }

    public static void printThread(){
        log.info("thread name - {}", Thread.currentThread().getName());
    }

    public static void printThread(String txt){
        log.info("thread {} - {}", Thread.currentThread().getName(), txt);
    }

    public static void printLine() {
        log.info("--------------------华丽分割线--------------------");
    }

    public static int random(int bound) {
        int i = random.nextInt(bound);
        log.debug("random value - {}", i);
        return i;
    }

    public static void threadStartAndJoin(List<Thread> ts){
        long startTime = System.currentTimeMillis();
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.error("thread interrupt - {}", t.getName());
            }
        });
        long endTime = System.currentTimeMillis();
        log.info("threads total run time - {}", endTime-startTime);
    }

    private static Random random = new Random();
}
