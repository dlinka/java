package com.cr.rocketmq.store;

import cn.hutool.core.date.DateUtil;
import com.cr.common.Facility;
import com.cr.common.FileUtil;
import com.cr.rocketmq.MQUtil;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Index {

    static String path = "/Users/dlinka/store/index/20211210110421105";

    public static void main(String[] args) throws IOException {
        index();
    }

    private static void index() throws IOException {
        ByteBuffer buffer = FileUtil.read(path);
        Facility.print("消息存储的最小时间               - {}",DateUtil.date(buffer.getLong()));
        Facility.print("消息存储的最大时间               - {}", DateUtil.date(buffer.getLong()));
        Facility.print("CommitLog最小偏移               - {}", buffer.getLong());
        Facility.print("CommitLog最大偏移               - {}", buffer.getLong());
        Facility.print("已使用的solt个数                 - {}", buffer.getInt());
        Facility.print("index个数                      - {}", buffer.getInt());
        Facility.printLine();

        //5百万个HASH SLOT
        for (int i = 0; i < 5000000; i++) {
            int soltValue = buffer.getInt();
            if (soltValue != 0) {
                Facility.print("solt位置                          - {}", i);
                Facility.print("solt值                            - {}", soltValue);
            }
        }

        //固定2千万个INDEX
        for (int i = 0; i < 20000000; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("i=" + i);
            sb.append(", hashcode=" + buffer.getInt());
            sb.append(", offset=" + buffer.getLong());
            sb.append(", timeDiff=" + buffer.getInt());
            sb.append(", preIndex=" + buffer.getInt());
            Facility.print(sb.toString());
        }

        //读取结束
        Facility.print(buffer.position() == buffer.capacity());
    }

}
