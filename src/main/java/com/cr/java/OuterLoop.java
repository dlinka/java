package com.cr.java;

import com.cr.common.F;

public class OuterLoop {

    public static void main(String[] args) {
        //名字任意
        position1:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                F.print(i + "-" + j);
                if (i == 5 && j == 5) {
                    break position1;
                }
            }
        }

        F.printLine();

        position2:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                F.print(i + "-" +j);
                if (i == 5 && j == 5) {
                    continue position2;
                }
            }
        }
    }

}
