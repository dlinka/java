package com.cr.IO.nio.chat;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static com.cr.common.Facility.print;
import static com.cr.common.Facility.sleep;


public class ChatClient {

    private Selector selector;
    private SocketChannel socketChannel;

    public ChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8686));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.run();
        sleep(20);
        client.send();
    }

    private void send() {
        try{
            String address = socketChannel.getLocalAddress().toString();
            socketChannel.write(ByteBuffer.wrap(address.getBytes()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void run() {
        new Thread(() -> {
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isReadable()) {
                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int read = channel.read(buffer);
                            String msg = new String(buffer.array());
                            print("{}, {}", read, msg);
                        }
                        iterator.remove();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
