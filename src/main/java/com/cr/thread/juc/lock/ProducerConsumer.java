package com.cr.thread.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            while (count == 4) {
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.printf("%s-%d%n", Thread.currentThread().getName(), count);
            //对比notify/notifyAll方法,这里使用Condition#signal方法唤醒的都是消费者线程
            consumer.signal();
        } finally {
            lock.unlock();
        }

    }

    public void consume() {
        lock.lock();
        try {
            while (count == 0) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s-%d%n", Thread.currentThread().getName(), count);
            count--;
            producer.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer cu = new ProducerConsumer();

        for (int i = 0; i != 5; ++i) {
            new Thread(() -> {
                for (int j = 0; j != 10; ++j) {
                    cu.produce();
                }
            }, "producer" + i).start();
        }

        for (int i = 0; i != 5; ++i) {
            new Thread(() -> {
                for (int j = 0; j != 10; ++j) {
                    cu.consume();
                }
            }, "consumer" + i).start();
        }
    }

}
