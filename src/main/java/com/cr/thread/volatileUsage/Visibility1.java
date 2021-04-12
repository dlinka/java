package com.cr.thread.volatileUsage;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class Visibility1 {

    private volatile boolean flag = true;

    public void method() {
        while (flag) {
        }
        System.out.println("程序停止");
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility1 v1 = new Visibility1();

        new Thread(() -> v1.method()).start();

        TimeUnit.SECONDS.sleep(2);

        v1.setFlag(false);
    }

}
