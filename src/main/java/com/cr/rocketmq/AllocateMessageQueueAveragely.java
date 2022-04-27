package com.cr.rocketmq;

import com.cr.common.Facility;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消费者负载均衡算法
 */
public class AllocateMessageQueueAveragely {

    public static void main(String[] args) {
        List<String> cidAll = Arrays.asList("1", "2", "3");
        String cid = "3";
        List<Integer> qidAll = Arrays.asList(1, 2, 3, 4);
        Facility.print(allocate(cid, qidAll, cidAll));
    }

    public static List<Integer> allocate(String currentCID, List<Integer> qidAll, List<String> cidAll) {
        List<Integer> result = new ArrayList<>();

        int index = cidAll.indexOf(currentCID);

        int mod = qidAll.size() % cidAll.size();

        int averageSize = qidAll.size() <= cidAll.size() ? 1 :
                (mod > 0 && index < mod ? qidAll.size() / cidAll.size() + 1 :
                        qidAll.size() / cidAll.size());

        int startIndex = (mod > 0 && index < mod) ? index * averageSize : index * averageSize + mod;

        int range = Math.min(averageSize, qidAll.size() - startIndex);

        for (int i = 0; i < range; i++) {
            result.add(qidAll.get((startIndex + i) % qidAll.size()));
        }

        return result;
    }
}
