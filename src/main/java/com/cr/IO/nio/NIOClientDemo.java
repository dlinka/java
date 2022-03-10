package com.cr.IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.sleep;

public class NIOClientDemo {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 7007))) {
            while (!socketChannel.finishConnect()) {
                print("connecting");
            }
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(32);

        byteBuffer.put("Hello World".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        sleep(10);
        byteBuffer.put("NIO".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        System.in.read();
    }
}
