package com.cr.thread;

import com.cr.common.Facility;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ThreadLocalRandomUsage {

    static int PROBE_INCREMENT = 0x9e3779b9;
    static int SQMASK          = 0x007e;
    static AtomicInteger PROBE = new AtomicInteger(PROBE_INCREMENT);

    //probe偶数
    static void probeEven() {
        int probe = PROBE_INCREMENT;
        for (int i = 0; i < 20; i++) {
            int k = probe & (16 - 1) & SQMASK;
            Facility.print(k);
            probe += PROBE_INCREMENT;
        }
    }

    //probe的作用
    static void probeUsage(){
        int length = 15; //2的N次方 - 1
        int probe = PROBE_INCREMENT;
        for (int i = 0; i <= length; i++) {
            int index = length & probe;
            Facility.print(index);
            probe = probe + PROBE_INCREMENT;
        }
    }

    //多线程下advanceProbe的重复率
    public static void checkRepeatProbe(){
        Set<Integer> probes = Collections.synchronizedSet(new HashSet<>());
        List<Thread> threadList = new ArrayList<>();
        int threadCount = 10000;
        int probeCount = 82;

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                int probe = PROBE.getAndAdd(PROBE_INCREMENT);
                for (int j = 0; j < probeCount; j++) {
                    probes.add(probe);
                    probe = advanceProbe(probe);
                }
            });
            threadList.add(thread);
        }

        Facility.start(threadList);
        Facility.print(probes.size());
    }

    static int advanceProbe(int probe) {
        probe ^= probe << 13;
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        return probe;
    }

    public static void main(String[] args) {
        probeEven();
        //probeUsage();
        //checkRepeatProbe();
    }

}
