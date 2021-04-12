package com.cr.thread.volatileUsage;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NotAtomic {

    private volatile int count;

    public void method() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        NotAtomic na = new NotAtomic();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            threads.add(new Thread(() -> na.method()));
        }
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //volatile不保证原子性
        //输出结果小于预期10000
        System.out.println(na.getCount());
    }

}
