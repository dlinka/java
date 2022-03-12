package com.cr.IO.bio;

import com.cr.common.Facility;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(63791);
        ThreadPoolExecutor threadpool = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        while (true) {
            Socket socket = server.accept();
            Facility.print("connect");
            threadpool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            InputStream stream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                int read = stream.read(bytes);
                Facility.print("read - {}", read);
                if (read != -1) {
                    Facility.print(bytes[0]);
                    Facility.print(bytes[1]);
                    Facility.print(bytes[2]);
                    Facility.print(bytes[3]);
                    Facility.print(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
