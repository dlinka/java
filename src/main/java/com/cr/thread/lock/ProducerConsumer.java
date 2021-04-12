package com.cr.thread.lock;

public class ProducerConsumer {

    private int count = 0;

    public synchronized void add() throws InterruptedException {
        /**
         * 不使用while会出现的问题流程:
         * 假设有两个消费者(C1和C2)和一个生产者(P1)
         * C1和C2此时在wait处等待
         * P1生产后通知C1和C2
         * C1先拿到锁进行消费,消费成功释放锁
         * C2拿到锁进行消费,如果不用while会直接进入消费,而这时已经C1已经消费完了
         */
        while (count == 1) {
            wait();
        }
        count++;
        System.out.printf("%s-%d%n", Thread.currentThread().getName(), count);
        //这里不能使用notify方法
        //因为可能唤醒的那个线程还是生产者线程
        this.notifyAll();
    }

    public synchronized void sub() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        System.out.printf("%s-%d%n", Thread.currentThread().getName(), count);
        count--;
        this.notifyAll();
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        for (int i = 0; i != 5; ++i) {
            new Thread(() -> {
                try {
                    pc.add();
                } catch (InterruptedException e) {
                }
            }, "producer" + i).start();
        }

        for (int i = 0; i != 5; ++i) {
            new Thread(() -> {
                try {
                    pc.sub();
                } catch (InterruptedException e) {
                }
            }, "consumer" + i).start();
        }
    }


}
