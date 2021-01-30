package io.howework;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackendServer {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(() -> service(socket));
        }
    }

    private static void service(Socket socket) {
        try {
            InputStream in = socket.getInputStream();
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            String str = new String(bytes, 0, in.available());
            // System.out.println(bytes[0]);
            // System.out.println("service" + Thread.currentThread().getId());
            // Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
