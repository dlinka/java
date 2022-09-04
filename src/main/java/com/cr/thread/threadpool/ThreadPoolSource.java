package com.cr.thread.threadpool;

import com.cr.common.Facility;

/**
 * ThreadPoolExecutor源码
 */
public class ThreadPoolSource {

    public static void main(String[] args) {
        int CAPACITY = (1 << 29) - 1;
        Facility.printBinary(CAPACITY);

        int RUNNING = -1 << 29;
        Facility.printBinary(RUNNING);

        int SHUTDOWN = 0<<29;
        Facility.printBinary(SHUTDOWN);

        int STOP = 1 << 29;
        Facility.printBinary(STOP);
    }
}
