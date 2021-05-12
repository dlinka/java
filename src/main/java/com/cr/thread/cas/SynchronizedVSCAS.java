package com.cr.thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.cr.common.Facility.start;

/**
 *
 * 前置条件: 1线程
 * Synchronized执行 - 25493
 * CAS执行 - 26180
 *
 * 前置条件: 2线程
 * Synchronized执行 - 27346
 * CAS执行 - 37513
 *
 * 前置条件：5线程
 * Synchronized执行 - 32049
 * CAS执行 - 55578
 *
 */
public class SynchronizedVSCAS {

    private int THREAD_NUM = 1;
    private List<String> list = new ArrayList<>();

    public void lock() {
        for (int i = 0; i != 500000; ++i) list.add(UUID.randomUUID().toString());
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_NUM; i++) {
            threads.add(
                    new Thread(() -> {
                        while (true) {
                            synchronized (list) {
                                if (list.size() <= 0) {
                                    break;
                                }
                                list.remove(0);
                            }
                        }
                    }));
        }
        start(threads, true);
    }

    public void cas() {
        for (int i = 0; i != 500000; ++i) list.add(UUID.randomUUID().toString());
        LockByCAS casLock = new LockByCAS();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_NUM; i++) {
            threads.add(
                    new Thread(() -> {
                        while (true) {
                            casLock.lock();
                            if (list.size() <= 0) {
                                casLock.unLock();
                                break;
                            }
                            list.remove(0);
                            casLock.unLock();
                        }
                    }));
        }
        start(threads, true);
    }

    public static void main(String[] args) {
        SynchronizedVSCAS svc = new SynchronizedVSCAS();
        svc.lock();
        svc.cas();
    }

}
