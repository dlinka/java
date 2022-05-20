package com.cr.jcf.map;

import com.cr.common.Facility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoopMap {

    static HashMap<String, Integer> map = new HashMap();

    public static void main(String[] args) {
        loop4();
    }

    //遍历key的方式遍历value
    static void loop1() {
        for (String key : map.keySet()) {
            Facility.print(key);
            Facility.print(map.get(key));
        }
        map.keySet().forEach(key -> {
            map.get(key);
        });
    }

    //单独遍历value
    static void loop11() {
        for (Integer value : map.values()) {
        }

        map.values().forEach(Facility::print);
    }

    static void loop2() {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }

        map.entrySet().iterator().forEachRemaining(entry -> {
            entry.getKey();
            entry.getValue();
        });
    }

    static void loop3() {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }

        map.entrySet().forEach(entry -> {
            entry.getKey();
            entry.getValue();
        });
    }

    static void loop4() {
        map.forEach((k, v) -> {
        });
    }

}
