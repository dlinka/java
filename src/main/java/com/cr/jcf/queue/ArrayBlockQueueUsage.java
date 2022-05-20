package com.cr.jcf.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueueUsage {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        queue.put(1);
        Integer i = queue.take();
    }

}
