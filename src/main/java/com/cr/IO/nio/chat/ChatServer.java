package com.cr.IO.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.sleep;

public class ChatServer {

    private Selector selector;
    private ServerSocketChannel serverChannel;

    public ChatServer() {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8686));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                print("事件数量 - {}", selectionKeys.size());

                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        connect();
                    }
                    if (key.isReadable()) {
                        String msg = read(key);
                        write(msg, (SocketChannel) key.channel());
                    }
                    iterator.remove();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void connect() throws Exception {
        SocketChannel socket = serverChannel.accept();
        socket.configureBlocking(false);
        socket.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        print("{} connect", socket.getRemoteAddress());
    }

    private String read(SelectionKey key) throws Exception {
        SocketChannel socket = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = socket.read(buffer);
        String msg = new String(buffer.array());
        print("客户端:{} - 发送字节数量:{} - {}", socket.getRemoteAddress(), read, msg);
        return msg;
    }

    private void write(String msg, SocketChannel expect) {
        selector.keys().forEach(key -> {
            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel && channel != expect) {
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    ((SocketChannel) channel).write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new ChatServer().run();
    }

}
