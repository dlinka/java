package com.cr.thread.volatileUsage;

import com.cr.common.User;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class Visibility3 {

    private volatile User user = new User(1L, "cr", 27);

    public void method() {
        while(user.getUid() == 1L){
        }
        System.out.println("程序停止");
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility3 v3 = new Visibility3();
        new Thread(() -> v3.method()).start();

        TimeUnit.SECONDS.sleep(1);

        v3.getUser().setUid(2L);
    }

}
