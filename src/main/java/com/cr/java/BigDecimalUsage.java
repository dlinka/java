package com.cr.java;

import com.cr.common.Facility;

import java.math.BigDecimal;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.printLine;

public class BigDecimalUsage {

    public static void main(String[] args) {

        /**
         * BigDecimal初始化小数时，请使用字符串形式
         */
        BigDecimal b1 = new BigDecimal(0.1);
        print(b1);
        BigDecimal b2 = new BigDecimal("0.1");
        print(b2);

        printLine();

        /**
         * BigDecimal比较大小时使用compareTo
         */
        BigDecimal b3 = new BigDecimal("0.1");
        BigDecimal b4 = new BigDecimal("0.100");
        print(b3.equals(b4)); //返回false
        print(b3.compareTo(b4));

        printLine();

        /**
         * BigDecimal除法时，除了要考虑除数是否为0，还要考虑是否能除尽的问题，使用divide(BigDecimal divisor, int scale, int roundingMode)避免除不尽的问题
         */
        BigDecimal b5 = new BigDecimal("1");
        BigDecimal b6 = new BigDecimal("3");
        //b5.divide(b6); //抛出异常
        print(b5.divide(b6, 2, BigDecimal.ROUND_HALF_UP));

        printLine();

    }

}
