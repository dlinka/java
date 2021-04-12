package com.cr.thread.lock;

public class SynchronizedCode {

    //使用this加锁
    public void lockThis() {
        synchronized (this) {

        }
    }

    private Object o = new Object();
    //使用一个Object进行加锁
    public void lockObject(){
        synchronized (o) {

        }
    }

}
