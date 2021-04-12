package com.cr.jcf.map;

import com.cr.common.F;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestThreadSafe {

    Map<String, Integer> map = new HashMap<>();
    //Map<String, Integer> map = new ConcurrentHashMap<>();

    public void demo() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i != 100; ++i) {
            String key = i + "-";

            threads.add(new Thread(() -> {

                for (int j = 0; j != 1000; ++j) {
                    map.put(key + j, 0);
                }
            }));
        }
        F.threadStartAndJoin(threads);
        F.print(map.size());
    }

    public static void main(String[] args) {
        TestThreadSafe tts = new TestThreadSafe();
        tts.demo();
    }
}
