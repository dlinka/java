package com.cr.thread.threadpool;

import com.cr.common.Facility;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinThreadPool {

    static final int SMASK        = 0xffff;
    static final int MAX_CAP      = 0x7fff;
    static final int EVENMASK     = 0xfffe;
    static final int SQMASK       = 0x007e;
    static final int FIFO_QUEUE   = 1 << 16;
    static final int MODE_MASK    = 0xffff << 16;
    static final int SHARED_QUEUE = 1 << 31;

    private static final int  AC_SHIFT   = 48;
    private static final long AC_UNIT    = 0x0001L << AC_SHIFT;
    private static final long AC_MASK    = 0xffffL << AC_SHIFT;
    private static final int  TC_SHIFT   = 32;
    private static final long TC_UNIT    = 0x0001L << TC_SHIFT;
    private static final long TC_MASK    = 0xffffL << TC_SHIFT;
    private static final long ADD_WORKER = 0x0001L << (TC_SHIFT + 15); // sign


    private static final int  RSLOCK     = 1;
    private static final int  RSIGNAL    = 1 << 1;
    private static final int  STARTED    = 1 << 2;
    private static final int  STOP       = 1 << 29;
    private static final int  TERMINATED = 1 << 30;
    private static final int  SHUTDOWN   = 1 << 31;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Facility.print(Integer.toBinaryString(SMASK));
        Facility.print(Integer.toBinaryString(MAX_CAP));
        Facility.print(Integer.toBinaryString(EVENMASK));
        Facility.print(Integer.toBinaryString(SQMASK));
        Facility.print(Integer.toBinaryString(FIFO_QUEUE));
        Facility.print(Integer.toBinaryString(MODE_MASK));
        Facility.print(Integer.toBinaryString(SHARED_QUEUE));
        Facility.print(Long.toBinaryString(7));
        Facility.print(Long.toBinaryString(-7));
        Facility.print(Long.toBinaryString(TC_UNIT));
        Facility.print(Long.toBinaryString(TC_MASK));
        //00000000 00000000 10000000 00000000 00000000 00000000 00000000 00000000
        Facility.print(Long.toBinaryString(ADD_WORKER));
        //0100 0000 0000 0000 0000 0000 0000 0000
        Facility.print(Integer.toBinaryString(TERMINATED));
        //1000 0000 0000 0000 0000 0000 0000 0000
        Facility.print(Integer.toBinaryString(SHUTDOWN));
    }
}
