package com.cr.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {

    public static ByteBuffer read(String path) throws IOException {
        File file = new File(path);
        FileInputStream fin = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fin.read(bytes);
        return ByteBuffer.wrap(bytes);
    }

    public static MappedByteBuffer mmap(String path) throws IOException {
        FileChannel channel = new RandomAccessFile(new File(path), "rw").getChannel();
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        return byteBuffer;
    }

}
