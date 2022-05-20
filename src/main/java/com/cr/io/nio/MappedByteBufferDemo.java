package com.cr.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile rsf = new RandomAccessFile("file-channel", "rw");
        FileChannel channel = rsf.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1);
        map.put(0, (byte) 'A');
        rsf.close();
    }
}
