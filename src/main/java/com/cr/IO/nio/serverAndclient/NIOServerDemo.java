package com.cr.IO.nio.serverAndclient;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static com.cr.common.Facility.print;

public class NIOServerDemo {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(7007));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        print("服务器启动，等待连接");

        while (true) {
            selector.select(); //阻塞

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    print("尝试连接");

                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(4));
                }

                if (selectionKey.isReadable()) {
                    print("读取数据");

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    int read; //读取的字节数量
                    while ((read = socketChannel.read(buffer)) > 0) {
                        print("read - {}", read);
                        String msg = new String(buffer.array());
                        print("字符串:{}", msg);
                        buffer.clear();
                    }
                }
                iterator.remove();
            }
        }
    }
}
