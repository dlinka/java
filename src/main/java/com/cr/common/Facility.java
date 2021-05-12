package com.cr.common;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 便捷类
 */
@Slf4j
public class Facility {

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

    /**
     * 启动线程
     * 根据isJoin判断是否等待传入线程执行完毕
     * @param ts
     * @param isJoin
     */
    public static void start(List<Thread> ts, boolean isJoin){
        long startTime = System.currentTimeMillis();
        ts.forEach(t -> t.start());
        if(isJoin){
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
    }

    /**
     * 当前线程sleep
     * 默认是秒
     * @param time
     */
    public static void sleep(long time){
        Thread t = Thread.currentThread();
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            log.error("thread interrupt - {}", t.getName());
        }
    }

    public static <T> List<T> initList(Class<T> clazz, int count){
        List<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (String.class.equals(clazz)){
                list.add((T) String.valueOf(i));
            } else if (Integer.class.equals(clazz)){
                list.add((T) Integer.valueOf(i));
            }
        }
        return list;
    }

    private static Random random = new Random();
}
