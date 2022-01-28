package com.cr.IO.nio;

import com.cr.common.Facility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelVSMMAP {

    static String path = "/Users/dlinka/128——1MB.txt";

    public static void main(String[] args) throws Exception {
        FileChannelVSMMAP fcvm = new FileChannelVSMMAP();

        /**
         * 写入一个新文件时间在28秒左右
         * 写入一个旧文件时间在65秒左右
         */
        //fcvm.write();

        /**
         * 写入一个新文件时间在150毫秒左右，后续多次写入时间不变
         * 写入一个旧文件时间在650毫秒左右，后续多次写入在150毫秒左右
         */
        //fcvm.write4kb();

        /**
         * 写入一个新文件时间在60毫秒左右，后续多次写入时间不变
         * 写入一个旧文件时间在200毫秒左右，后续多次写入时间在60毫秒左右
         */
        //fcvm.writeByMMAP();

        /**
         * 写入一个新文件时间在40毫秒左右，后续多次写入时间不变
         * 写入一个旧文件时间在200毫秒左右，后续多次写入时间在40毫秒左右
         */
        fcvm.write4kbByMMAP();

        //fcvm.read();                  //19714
        //fcvm.readByMMAP();            //19
    }

    void write4kb() throws IOException {
        long time = 0L;
        for (int i = 0; i < (INT_NUM_COUNT / 1024); i++) {
            _4kbBufferInner.position(0);

            long startTime = System.currentTimeMillis();
            channel.write(_4kbBufferInner);
            time += (System.currentTimeMillis() - startTime);

            _4kbBufferInner.position(0);
        }
        Facility.print(time);
    }

    void write() throws IOException {
        long time = 0L;
        for (int i = 0; i < INT_NUM_COUNT; i++) {
            _intBufferInner.putInt(i);
            _intBufferInner.position(0);

            long startTime = System.currentTimeMillis();
            channel.write(_intBufferInner);
            time += (System.currentTimeMillis() - startTime);

            _intBufferInner.position(0);
        }
        Facility.print(time);
    }

    void write4kbByMMAP() {
        Facility.printRunTime(() -> {
            for (int i = 0; i < (INT_NUM_COUNT / 1024); i++) {
                mmap.put(_4kb);
            }
        });
    }

    void writeByMMAP() {
        Facility.printRunTime(() -> {
            for (int i = 0; i < INT_NUM_COUNT; i++) {
                mmap.putInt(i);
            }
        });
    }

    void read() throws IOException {
        long time = 0L;
        for (int i = 0; i < INT_NUM_COUNT; i++) {
            long startTime = System.currentTimeMillis();
            channel.read(_intBufferInner);
            time += (System.currentTimeMillis() - startTime);

            _intBufferInner.position(0);
        }
        Facility.print(time);
    }

    void readByMMAP() {
        Facility.printRunTime(() -> {
            for (int i = 0; i < INT_NUM_COUNT; i++) {
                mmap.getInt();
            }
        });
    }


    static RandomAccessFile raf;
    static FileChannel channel;
    static MappedByteBuffer mmap;
    static int fileSize = 1024 * 1024 * 128;
    static int INT_NUM_COUNT = fileSize / 4;
    static ByteBuffer _intBufferInner = ByteBuffer.allocate(4);           //复用4b的ByteBuffer
    static ByteBuffer _4kbBufferInner = ByteBuffer.allocate(4 * 1024);    //复用4kb的ByteBuffer
    static byte[] _4kb = _4kbBufferInner.array();

    static {
        try {
            raf = new RandomAccessFile(path, "rw");
            channel = raf.getChannel();
            mmap = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
            for (int i = 0; i < 1024; ++i) {
                _4kbBufferInner.putInt(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
