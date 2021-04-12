package com.cr.thread.lock;

/**
 * 这段代码有什么问题?
 * 可能会输出相同数字
 *  执行count--后,没有立即执行System.out.println
 */
public class ThreadUnSafeDemo1 {

    private int count = 100;

    public void run() {
        count--;
        System.out.println("count:" + count);
    }

    public static void main(String[] args) {
        ThreadUnSafeDemo1 tusd = new ThreadUnSafeDemo1();
        for (int i = 0; i < 50; ++i) {
            new Thread(() -> tusd.run()).start();
        }
    }

}
