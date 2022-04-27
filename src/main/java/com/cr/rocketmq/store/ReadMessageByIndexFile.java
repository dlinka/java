package com.cr.rocketmq.store;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.cr.common.FileUtil;
import com.cr.rocketmq.MQUtil;
import org.apache.rocketmq.common.message.MessageDecoder;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class ReadMessageByIndexFile {

    private static String INDEXFILE_PATH = "/Users/dlinka/store/index/20201106140544803";
    private static String COMMITLOG_PATH = "/Users/dlinka/store/commitlog/00000000000000000000";
    private static String UNIQ_KEY = "TOPIC_ROCKETQMQ#0A00C3347A1518B4AAC22C94FE070004";

    public static void main(String[] args) throws IOException {
        ByteBuffer indexBuffer = FileUtil.read(INDEXFILE_PATH);
        int hash = Math.abs(UNIQ_KEY.hashCode());
        int slot = hash % 5000000;
        int absSlotPos = 40 + slot * 4;
        int slotValue = indexBuffer.getInt(absSlotPos);
        int absIndexPos = 40 + 5000000 * 4 + slotValue * 20;
        int hashcode = indexBuffer.getInt(absIndexPos);
        long offset = indexBuffer.getLong(absIndexPos + 4);
        System.out.println("HASH                           - " + hash);
        System.out.println("SLOT                           - " + slot);
        System.out.println("ABS SLOT POS                   - " + absSlotPos);
        System.out.println("SLOT VALUE                     - " + slotValue);
        System.out.println("ABS INDEX POS                  - " + absIndexPos);
        System.out.println("HASHCODE                       - " + hashcode);
        System.out.println("COMMIT LOG OFFSET              - " + offset);

        System.out.println("------------------------------------");

        FileChannel channel = new RandomAccessFile(new File(COMMITLOG_PATH), "rw").getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        buffer.position((int) offset);
        System.out.println("消息大小                        - " + buffer.getInt());
        System.out.println("MAGIC CODE                     - " + buffer.getInt());
        System.out.println("CRC校验码                       - " + buffer.getInt());
        System.out.println("QUEUE ID                       - " + buffer.getInt());
        System.out.println("FLAG                           - " + buffer.getInt());
        System.out.println("CONSUME QUEUE OFFSET           - " + buffer.getLong());
        System.out.println("PHYSICAL OFFSET                - " + buffer.getLong());
        System.out.println("SYS FLAG                       - " + buffer.getInt());
        System.out.println("生成消息的时间                   - " + DateUtil.date(buffer.getLong()));
        System.out.println("PRODUCER HOST:PORT             - " + MQUtil.host(buffer) + ":" + buffer.getInt());
        System.out.println("存储消息的时间                   - " + DateUtil.date(buffer.getLong()));
        System.out.println("BROKER HOST:PORT               - " + MQUtil.host(buffer) + ":" + buffer.getInt());
        System.out.println("RECONSUME TIMES                - " + buffer.getInt());
        System.out.println("PREPARED TRANSACTION OFFSET    - " + buffer.getLong());

        int bodyLen = buffer.getInt();
        byte[] body = new byte[bodyLen];
        buffer.get(body);
        System.out.println("BODY LENGTH                    - " + bodyLen);
        System.out.println("BODY                           - " + new String(body));

        byte topicLen = buffer.get();
        byte[] topic = new byte[topicLen];
        buffer.get(topic);
        System.out.println("TOPIC LENGTH                   - " + topicLen);
        System.out.println("TOPIC                          - " + new String(topic));

        short propertiesLen = buffer.getShort();
        byte[] properties = new byte[propertiesLen];
        buffer.get(properties);
        Map<String, String> map = MessageDecoder.string2messageProperties(new String(properties));
        System.out.println("PROPERTIES LENGTH              - " + propertiesLen);
        System.out.println("PROPERTIES                     - " + map);
    }

}
