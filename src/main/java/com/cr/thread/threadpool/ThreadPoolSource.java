package com.cr.thread.threadpool;

/**
 * ThreadPoolExecutor源码
 */
public class ThreadPoolSource {

    public static void main(String[] args) {
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;
        System.out.println(Integer.toBinaryString(CAPACITY));
        int RUNNING = -1 << COUNT_BITS;
        System.out.println(Integer.toBinaryString(RUNNING));
        int SHUTDOWN = 0<<COUNT_BITS;
        System.out.println(Integer.toBinaryString(SHUTDOWN));
        int STOP = 1 << COUNT_BITS;
        System.out.println(Integer.toBinaryString(STOP));
    }
}
