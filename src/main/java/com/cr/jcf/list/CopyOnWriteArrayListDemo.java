package com.cr.jcf.list;

import com.cr.common.Facility;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void concurrentRW() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Thread thread1 = new Thread(() -> {
            for (Integer i : list) {
                Facility.print("read - {}", i);
                Facility.sleep(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            int i = 6;
            list.add(i);
            Facility.print("write - {}", i);
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        CopyOnWriteArrayListDemo demo = new CopyOnWriteArrayListDemo();
        demo.concurrentRW();
    }

}
