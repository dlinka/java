package com.cr.JCF.list;

import com.cr.common.Facility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.cr.common.Facility.print;

public class ArrayListDemo {

    public static void main(String[] args) {
        iterator();
    }

    //1.测试并发删除异常
    public static void remove() {
        List<String> list = Facility.initList(String.class, 10000);
        for (int i = 0; i != 10; ++i) {
            new Thread(() -> {
                while (list.size() > 0) {
                    print(list.remove(0));
                }
            }).start();
        }
    }

    //2.测试迭代器中使用ArrayList#remove的删除异常
    public static void iterator() {
        List<Integer> list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(2)) {
                list.remove(next);
            }
        }
    }

    //3.测试并发迭代器中删除异常
    public static void concurrentRemove() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Thread thread1 = new Thread(() -> {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                print("read - {}", iterator.next());
                Facility.sleep(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer integer = iterator.next();
                if (integer == 3)
                    print("remove - {}", integer);
                    iterator.remove();
            }
        });

        thread1.start();
        thread2.start();
    }

    //4.测试并发读写异常
    public static void concurrentRW() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Thread thread1 = new Thread(() -> {
            /**for (Integer i : list) {
             Facility.print("read - {}",i);
             Facility.sleep(1);
             }*/

            /**Iterator<Integer> iterator = list.iterator();
             while (iterator.hasNext()) {
             Facility.print("read - {}", iterator.next());
             Facility.sleep(1);
             }*/

            /**for (int i = 0; i < list.size(); i++) {
             System.out.println(list.get(i));
             Facility.sleep(100, TimeUnit.MILLISECONDS);
             }*/
        });

        Thread thread2 = new Thread(() -> {
            int i = 6;
            list.add(i);
            print("write - {}", i);
        });

        thread1.start();
        thread2.start();
    }

}
