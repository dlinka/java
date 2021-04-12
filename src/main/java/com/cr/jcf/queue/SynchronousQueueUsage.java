package com.cr.jcf.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueUsage {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        //异常
        //queue.add(1);

        //false
        //queue.offer(1);

        //阻塞
        queue.put(1);

        //这块代码放到上面,程序就可以结束
        new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
    }

}
