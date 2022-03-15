package com.cr.java;

import java.util.BitSet;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.printBinary;

public class BitSetDemo {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(1000000000);
        bitSet.set(78);
        print(bitSet.size());
        print(bitSet.length());
        print(bitSet.get(66677));
        print(bitSet.size());
        print(bitSet.length());

        long i = 1L << 68;
        printBinary(68);
        printBinary(i);
    }

}
