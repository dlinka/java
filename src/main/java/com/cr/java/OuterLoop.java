package com.cr.java;

import com.cr.common.Facility;

public class OuterLoop {

    public static void main(String[] args) {
        //名字任意
        position1:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Facility.print(i + "-" + j);
                if (i == 5 && j == 5) {
                    break position1;
                }
            }
        }

        Facility.printLine();

        position2:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Facility.print(i + "-" +j);
                if (i == 5 && j == 5) {
                    continue position2;
                }
            }
        }
    }

}
