package com.cr.IO.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static com.cr.common.Facility.print;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(6699));
        SocketChannel socket = server.accept();

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        while (true) {
            int read = socket.read(buffer);
            print("read - {}", read);
            if (read == -1) {
                break;
            }
            buffer.clear();
        }

    }

}
