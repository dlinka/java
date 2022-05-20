package com.cr.java;

import com.cr.common.Facility;

import java.nio.ByteBuffer;

public class ByteBufferUsage {

    public static void main(String[] args) {
        slice();
    }

    //初始化
    private static void init() {
        //实现类为HeapByteBuffer
        ByteBuffer buffer1 = ByteBuffer.allocate(10);

        byte[] arr = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(arr);
    }

    private static void slice() {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        buffer1.putInt(10);

        //返回position到limit之间的数据
        ByteBuffer buffer2 = buffer1.slice();
        //0
        buffer2.position();
        //6
        buffer2.limit();
        //6
        buffer2.capacity();
    }

    private static void flip() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //int占用4个字节
        buffer.putInt(101);
        //4
        buffer.position();
        //10
        buffer.limit();

        buffer.flip();

        //这里就看出来limit的作用:如果想读取已经写入的数据,就需要一个标识去标识数据的结尾
        //0
        buffer.position();
        //4
        buffer.limit();
        //101
        buffer.getInt();
    }

    private static void loop() {
        ByteBuffer buffer;
        byte[] num = new byte[10];
        for (int i = 0; i < num.length; i++) {
            num[i] = (byte) (i + 1);
        }
        buffer = ByteBuffer.wrap(num);
        while (buffer.hasRemaining()) {
            Facility.print(buffer.get());
        }
    }

}
