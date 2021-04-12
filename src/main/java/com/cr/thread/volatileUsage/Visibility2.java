package com.cr.thread.volatileUsage;

import com.cr.common.User;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class Visibility2 {

    private volatile User vUser;
    private User user = new User();

    public void method() {
        while (this.getVUser() == this.getUser()) { }
        System.out.println("程序停止");
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility2 v2 = new Visibility2();
        v2.setVUser(v2.getUser());

        new Thread(() -> v2.method()).start();

        TimeUnit.SECONDS.sleep(2);

        v2.setVUser(new User());
    }

}
