package com.cr.balance;

import java.util.HashMap;
import java.util.Map;

import static com.cr.common.Facility.print;

/**
 * 加权轮训算法
 */
public class WeightRoundRobin {
    static HashMap<String, Integer> SERVERS = new HashMap<>();
    static Integer requestId = 0; //请求数
    static Integer totalWeight = 0; //总权重
    static {
        SERVERS.put("192.168.1.0", 5); //权重5
        SERVERS.put("192.168.1.1", 2); //权重2
        SERVERS.put("192.168.1.2", 3); //权重3
        totalWeight = SERVERS.values().stream().mapToInt(Integer::intValue).sum();
    }

    public static String getServer() {
        requestId++;
        int index = requestId % totalWeight;
        for (Map.Entry<String, Integer> entry : SERVERS.entrySet()) {
            if (index >= entry.getValue()) {
                index = index - entry.getValue();
                continue;
            }
            return entry.getKey();
        }
        return null;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; ++i) {
            print(getServer());
        }
    }
}
