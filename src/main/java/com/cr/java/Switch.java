package com.cr.java;

import com.cr.common.Facility;

public class Switch {

    public static void main(String[] args) {
        int code = 310;
        switch (code){
            case 310:
                Facility.print(310);
            case 10:
                Facility.print(10);
            default:
                break;
        }
    }
}
