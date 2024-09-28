package com.leetcode2.org.Address;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSever {
    public static void main(String[] args) {
        TCPSever tcpSever = new TCPSever();
        tcpSever.server();
    }

    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        try {
            int port = 8989;
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("服务器已启动");

            is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }
            System.out.println("数据接收完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
