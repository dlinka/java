package com.cr.java;

import com.cr.common.Facility;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.printBinary;

/**
 * 位运算
 * 左移<<：补码进行左移n位，符号位也移动，右边补零，移动的位数如果大于等于32位，就对32取余(移动32位，相当于不移动，移动40位，相当于移动8位)
 * 右移>>：补码进行右移n位，左边补上符号位，正数全补上0，负数全补上1，移动的位数如果大于等于32位，就对32取余(移动32位，相当于不移动，移动40位，相当于移动8位)
 * 无符号右移>>>：补码进行右移n位，左边全补上0，移动的位数如果大于等于32位，就对32取余(移动32位，相当于不移动，移动40位，相当于移动8位)
 * 与运算&：两个位都为1时，才为1
 * 或运算|：两个位都为0时,才为0
 * 异或运算^：两个位相同为0，相异为1
 * 取反~：补码每一位都取反
 */
public class BitOperation {

    /**
     * 左移<<
     */
    public static void leftMove() {
        int num = 12333292;
        printBinary(num);
        printBinary(num << 8);
        print(num << 8);
    }

    /**
     * 右移>>
     */
    public static void rightMove() {
        int num = 12333292;
        printBinary(num);
        printBinary(num >> 8);
        print(num >> 8);
    }

    /**
     * 无符号右移>>>
     */
    public static void noSymbolRightMove() {
        int num = -1;
        printBinary(num);
        printBinary(num >>> 8);
        print(num >>> 8);
    }

    /**
     * 运算&
     */
    public static void bitAND() {
        System.out.println(1 & 1); //1
        System.out.println(1 & 0); //0
        System.out.println(0 & 0); //0
    }

    /**
     * 或运算|
     */
    public static void bitOR() {
        System.out.println(1 | 1); //1
        System.out.println(1 | 0); //1
        System.out.println(0 | 0); //0
    }

    /**
     * 异或运算^
     */
    public static void bitXOR() {
        System.out.println(1 ^ 1); //0
        System.out.println(0 ^ 0); //0
        System.out.println(1 ^ 0); //1
    }

    /**
     * 取反~
     */
    public static void bitReverse() {
        int num = 5;
        printBinary(num);
        int numReverse = ~num;
        printBinary(numReverse);
    }

    public static void main(String[] args) {
        //leftMove();
        //rightMove();
        noSymbolRightMove();
        bitReverse();
    }

}
