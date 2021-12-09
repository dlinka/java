package com.cr.rocketmq.store;

import com.cr.common.Facility;
import com.cr.rocketmq.MQUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ConsumeQueue {

    static String path = "/Users/dlinka/store/consumequeue/ROCKETMQ_1/0/00000000000000000000";

    public static void main(String[] args) throws IOException {
        FileChannel channel = new RandomAccessFile(new File(path), "rw").getChannel();
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 300000 * 20);
        while (byteBuffer.hasRemaining()) {
            Facility.print("COMMIT LOG OFFSET           - " + byteBuffer.getLong());
            Facility.print("MESSAGE SIZE                - " + byteBuffer.getInt());
            Facility.print("TAG HASHCODE                - " + byteBuffer.getLong());
            Facility.printLine();
        }
    }

}
