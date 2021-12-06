package com.cr.java;

import com.cr.common.Facility;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Arguments {

    public static void main(String[] args) {
        for (String arg : args) {
            Facility.print(arg);
        }

        Facility.printLine();

        Map<String, String> getenv = System.getenv();
        Set<Map.Entry<String, String>> entries1 = getenv.entrySet();
        for (Map.Entry<String, String> entry : entries1) {
            Facility.print(entry.getKey() + " - " + entry.getValue());
        }

        Facility.printLine();

        Properties p = System.getProperties();
        Set<Map.Entry<Object, Object>> entries2 = p.entrySet();
        for (Map.Entry<Object, Object> entry: entries2) {
            Facility.print(entry.getKey() + " - " + entry.getValue());
        }
    }
}
