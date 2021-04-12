package com.cr.jcf.queue;

import com.cr.common.F;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerByBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    int val = random.nextInt(1000);
                    F.print(val);
                    blockingQueue.put(val);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(val));
                } catch (InterruptedException e) {
                }
            }
        }, "生产者线程").start();

        for (int i = 1; i != 3; ++i) {
            new Thread(() -> {
                while(true){
                    try {
                        F.print(blockingQueue.take());
                    } catch (InterruptedException e) {
                    }
                }
            }, "消费者线程" + i).start();
        }
    }

}
