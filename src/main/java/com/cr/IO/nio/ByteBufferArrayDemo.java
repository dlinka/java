package com.cr.IO.nio;

import com.cr.common.Facility;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import static com.cr.common.Facility.print;

public class ByteBufferArrayDemo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(7001));
        SocketChannel socketChannel = serverSocketChannel.accept();

        //ByteBuffer数组
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(4);
        buffers[1] = ByteBuffer.allocate(6);

        while (true) {
            int read = 0;
            while (read < 10) {
                long _read = socketChannel.read(buffers);
                read += _read;
                print(_read);
                Arrays.stream(buffers).map(buffer -> "position - " + buffer.position() + ", limit - " + buffer.limit()).forEach(Facility::print);
            }
            Arrays.stream(buffers).forEach(buffer -> buffer.clear());
        }
    }


}
