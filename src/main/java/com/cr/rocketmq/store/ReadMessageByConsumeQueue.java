package com.cr.rocketmq.store;

import com.cr.common.Facility;
import com.cr.common.FileUtil;
import com.cr.rocketmq.MQUtil;
import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageExt;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ReadMessageByConsumeQueue {

    static String commitLogPath = "/Users/dlinka/store/commitlog/00000000000000000000";
    static String consumeQueuePath = "/Users/dlinka/store/consumequeue/ROCKETMQ_1/1/00000000000000000000";

    public static void main(String[] args) throws IOException {
        ByteBuffer commitLog = FileUtil.mmap(commitLogPath);
        ByteBuffer consumeQueue = FileUtil.mmap(consumeQueuePath);
        print(commitLog, consumeQueue);
    }

    static void print(ByteBuffer commitLog, ByteBuffer consumeQueue) {
        while (true) {
            //消息偏移量
            int offset = (int) consumeQueue.getLong();
            //消息的大小
            int size = consumeQueue.getInt();
            //没有数据了
            if (offset == 0 && size == 0) { break; }

            commitLog.position(offset);
            commitLog.limit(offset + size);
            //切割出来数据
            ByteBuffer slice = commitLog.slice();
            commitLog.clear();

            //解析
            MessageExt messageExt = MessageDecoder.decode(slice);
            Facility.print(new String(messageExt.getBody()));

            //下一次循环需要+8(Tag Hashcode)
            consumeQueue.position(consumeQueue.position() + 8);
        }
    }

}
