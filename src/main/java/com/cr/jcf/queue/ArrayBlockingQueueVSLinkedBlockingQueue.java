package com.cr.jcf.queue;

import com.cr.common.Facility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.cr.common.Facility.start;

/**
 * 前置条件:
 * 5分钟
 * 10个生产者和10个消费者线程
 * <p>
 * 目的:
 * 统计生产者生产了多少个和消费者消费了多少个
 * <p>
 * LinkedBlockingQueue:
 * 生产者生产了 - 409503966
 * 消费者消费了 - 409502983
 * ArrayBlockingQueue:
 * 生产者生产了 - 339770184
 * 消费者消费了 - 339769184
 */
public class ArrayBlockingQueueVSLinkedBlockingQueue {

    //运行时间
    static final LocalDateTime RUN_TIME = LocalDateTime.now().plusMinutes(3);
    //生产者线程和消费者线程数量
    static final int THREAD_COUNT = 10;
    //统计生产了多少个
    static AtomicInteger productCount = new AtomicInteger();
    //统计消费了多少个
    static AtomicInteger consumerCount = new AtomicInteger();

    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1000);

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads.add(
                    new Thread(() -> {
                        while (true) {
                            try {
                                queue.put(1);
                                productCount.incrementAndGet();
                            } catch (InterruptedException e) {
                            }
                            //超过指定时间线程停止
                            if (LocalDateTime.now().compareTo(RUN_TIME) >= 0) {
                                break;
                            }
                        }
                    }));
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads.add(
                    new Thread(() -> {
                        while (true) {
                            try {
                                queue.take();
                                consumerCount.incrementAndGet();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            //超过指定时间线程停止
                            if (LocalDateTime.now().compareTo(RUN_TIME) >= 0) {
                                break;
                            }
                        }
                    }));
        }

        start(threads , true);
        Facility.print("生产数量 - {}", productCount.get());
        Facility.print("消费数量 - {}", consumerCount.get());
    }

}
