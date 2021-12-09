package com.cr.rocketmq.store;

import com.cr.common.Facility;
import com.cr.rocketmq.MQUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CommitLog {

    static String path = "/Users/dlinka/store/commitlog/00000000000000000000";

    public static void main(String[] args) throws IOException {
        FileChannel channel = new RandomAccessFile(new File(path), "rw").getChannel();
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        while (byteBuffer.hasRemaining()) {
            MQUtil.print(byteBuffer);
            Facility.print("---ByteBuffer position         - {}", byteBuffer.position());
        }
    }


}
