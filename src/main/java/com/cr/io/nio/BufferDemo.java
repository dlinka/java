package com.cr.io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static com.cr.common.Facility.print;

public class BufferDemo {

    public static void main(String[] args) {
        demoReadOnlyBuffer();
    }

    /**
     * 只读的ByteBuffer
     */
    static void demoReadOnlyBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(7);
        buffer.flip();

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while(readOnlyBuffer.hasRemaining()){
            print(readOnlyBuffer.getInt());
        }
    }

    /**
     * IntBuffer
     */
    static void demoIntBuffer() {
        IntBuffer buffer = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            buffer.put(i);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            print(buffer.get());
        }
    }
}
