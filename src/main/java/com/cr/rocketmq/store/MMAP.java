package com.cr.rocketmq.store;

import com.cr.common.Facility;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.cr.common.Facility.print;

public class MMAP {

    static String path = "/Users/dlinka/store/commitlog/00000000000000000000";

    public static void main(String[] args) throws IOException {
        //read();
        mmap();
    }

    private static void read() throws IOException {
        Facility.printRunTime(() -> {
            FileInputStream fis = new FileInputStream(path);
            byte[] data = new byte[1024];
            int index = 0;
            while (fis.read(data) != -1) {
                ++index;
                if (index % 10000 == 0) {
                    print(index);
                }
            }
            fis.close();
        });
    }

    private static void mmap() throws IOException {
        Facility.printRunTime(() -> {
            FileChannel channel = new RandomAccessFile(new File(path), "rw").getChannel();
            //position表示被映射文件的起始位置，size表示映射大小
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
            int index = 0;
            while (mappedByteBuffer.hasRemaining()) {
                mappedByteBuffer.get(1024);
                ++index;
                if (index % 10000 == 0) {
                    print(index);
                }
                mappedByteBuffer.position(1024 * index);
            }
        });
    }
}
