package com.cr.java;

import com.cr.common.Facility;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.printLine;

public class Arguments {

    public static void main(String[] args) {
        /**
         * Program arguments中设置a b c
         *  a
         *  b
         *  c
         */
        for (String arg : args) {
            print(arg);
        }
        printLine();

        /**
         * Environment variables中设置p=1;q=2
         *  p=1
         *  q=2
         */
        Map<String, String> envs = System.getenv();
        envs.forEach((k, v) -> {
            print(k + "=" + v);
        });
        printLine();

        /**
         * VM options中设置-Dk1=v1 -Dk2=v2
         *  k2=v2
         *  k1=v1
         */
        Properties p = System.getProperties();
        for (Map.Entry<Object, Object> entry: p.entrySet()) {
            print(entry.getKey() + "=" + entry.getValue());
        }
    }
}
