package com.cr.IO.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class SendClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress("127.0.0.1", 6699));

        String filename = "中国通史.pdf";
        FileInputStream fis = new FileInputStream(filename);
        FileChannel channel = fis.getChannel();
        channel.transferTo(0, channel.size(), socket);

        fis.close();
        socket.close();
    }

}
