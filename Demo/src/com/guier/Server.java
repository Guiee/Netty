package com.guier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket=new ServerSocket(port);
            System.out.println("服务端启动成功，端口：" + port);
        } catch (IOException e) {
            System.out.println("服务端启动失败");
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this::doStart).start();

    }

    private void doStart() {
        while (true) {
            try {
                Socket client = this.serverSocket.accept();
                new ClientHandler(client).start();
            } catch (IOException e) {
                System.out.println("服务端异常");
            }
        }
    }
}
