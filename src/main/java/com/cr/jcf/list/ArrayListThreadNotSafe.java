package com.cr.jcf.list;

import com.cr.common.F;

import java.util.List;

/**
 * ArrayList线程不安全
 */
public class ArrayListThreadNotSafe {

    List<String> list = F.initList(String.class, 10000);

    public void demo() {
        for (int i = 0; i != 10; ++i) {
            new Thread(() -> {
                while (list.size() > 0) {
                    F.print(list.remove(0));
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        ArrayListThreadNotSafe sltns = new ArrayListThreadNotSafe();
        sltns.demo();
    }

}
