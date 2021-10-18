package com.cr.thread.juc;

import com.cr.common.Facility;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomLock implements Lock {
    static CustomLock lock = new CustomLock();
    static int count = 0;
    static CountDownLatch latch = new CountDownLatch(2);

    public static void test() {
        lock.lock();
        Facility.print("1");
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 100000; i++) {
                count++;
            }
            lock.lock();
            for (int i = 0; i < 100000; i++) {
                count++;
            }
            lock.unlock();
            //测试可重入效果
            Facility.sleep(1);
            lock.unlock();
            latch.countDown();
        });
        t0.start();

        Thread t1 = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 100000; i++) {
                count++;
            }
            lock.unlock();
            latch.countDown();
        });
        t1.start();

        latch.await();
        Facility.print(count);
    }

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    //可重入独占锁
    static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            Thread thread = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(thread);
                    Facility.print("{} 获取锁 - {}", thread.getName(), String.valueOf(getState()));
                    return true;
                }
            } else if (thread == getExclusiveOwnerThread()) {
                setState(getState() + arg);
                Facility.print("{} 获取锁 - {}", thread.getName(), String.valueOf(getState()));
                return true;
            }
            Facility.print("{} 没有拿到锁 - {}", thread.getName(), String.valueOf(getState()));
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            setState(state);
            Facility.print("{} 释放锁 - {}", Thread.currentThread().getName(), String.valueOf(state));
            if (state == 0) {
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }
    }


}
