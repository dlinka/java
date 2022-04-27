package com.cr.rocketmq;

import cn.hutool.core.date.DateUtil;
import com.cr.common.Facility;
import org.apache.rocketmq.common.message.MessageDecoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class MQUtil {

    public static InetAddress host(ByteBuffer buffer) throws UnknownHostException {
        byte[] host = new byte[4];
        buffer.get(host, 0, 4);
        return InetAddress.getByAddress(host);
    }

    public static void print(ByteBuffer buffer) throws UnknownHostException {
        Facility.print("消息大小                        - " + buffer.getInt());
        Facility.print("MAGIC CODE                     - " + buffer.getInt());
        Facility.print("CRC校验码                       - " + buffer.getInt());
        Facility.print("QUEUE ID                       - " + buffer.getInt());
        Facility.print("FLAG                           - " + buffer.getInt());
        Facility.print("CONSUME QUEUE OFFSET           - " + buffer.getLong());
        Facility.print("PHYSICAL OFFSET                - " + buffer.getLong());
        Facility.print("SYS FLAG                       - " + buffer.getInt());
        Facility.print("生成消息的时间                   - " + DateUtil.date(buffer.getLong()));
        Facility.print("PRODUCER HOST:PORT             - " + MQUtil.host(buffer) + ":" + buffer.getInt());
        Facility.print("存储消息的时间                   - " + DateUtil.date(buffer.getLong()));
        Facility.print("BROKER HOST:PORT               - " + MQUtil.host(buffer) + ":" + buffer.getInt());
        Facility.print("RECONSUME TIMES                - " + buffer.getInt());
        Facility.print("PREPARED TRANSACTION OFFSET    - " + buffer.getLong());

        int bodyLen = buffer.getInt();
        byte[] body = new byte[bodyLen];
        buffer.get(body);
        Facility.print("BODY LENGTH                    - " + bodyLen);
        Facility.print("BODY                           - " + new String(body));

        byte topicLen = buffer.get();
        byte[] topic = new byte[topicLen];
        buffer.get(topic);
        Facility.print("TOPIC LENGTH                   - " + topicLen);
        Facility.print("TOPIC                          - " + new String(topic));

        short propertiesLen = buffer.getShort();
        byte[] properties = new byte[propertiesLen];
        buffer.get(properties);
        Map<String, String> map = MessageDecoder.string2messageProperties(new String(properties));
        Facility.print("PROPERTIES LENGTH              - " + propertiesLen);
        Facility.print("PROPERTIES                     - " + map);
    }

}
