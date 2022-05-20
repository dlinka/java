package com.cr.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.cr.common.Facility.print;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        //write("陈钇");
        //read();
        //copy();
        transFrom();
    }

    static void write(String str) throws IOException {
        FileOutputStream fos = new FileOutputStream("file-channel.txt");
        FileChannel channel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();

        channel.write(buffer);
        fos.close();
    }

    static void read() throws IOException {
        FileInputStream fis = new FileInputStream("file-channel.txt");
        FileChannel channel = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        print(new String(buffer.array()));
        fis.close();
    }

    static void copy() throws IOException {
        FileInputStream fis = new FileInputStream("file-channel.txt");
        FileChannel ichannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("file-copy.txt");
        FileChannel ochannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (true) {
            buffer.clear();
            int read = ichannel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            ochannel.write(buffer);
        }
        fis.close();
        fos.close();
    }

    /**
     * transferFrom
     */
    static void transFrom() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/dlinka/电子书/Rocket MQ使用排查指南8-13.pdf");
        FileChannel ichannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("/Users/dlinka/电子书/hgjj-1.pdf");
        FileChannel ochannel = fos.getChannel();
        print(ichannel.size());
        ochannel.transferFrom(ichannel, 0, ichannel.size());
        fis.close();
        fos.close();
    }

}
