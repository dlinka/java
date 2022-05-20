package com.cr.java;

import com.cr.common.Facility;

public class StringUsage {

    public static void main(String[] args) {
        String str = "0";
        Facility.print(str.substring(0, 1));

        StringBuilder result = new StringBuilder();
        result.append("01234");
        Facility.print(result.insert(3, "a"));
        Facility.print(result);
        result.setLength(0);
        Facility.print(result);
    }

}
