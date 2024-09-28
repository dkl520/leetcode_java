package com.leetcode2.org.Address;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//import org.junit.jupiter.*;

public class TCPExample {
    public static void main(String[] args) {
        TCPExample tcpExample = new TCPExample();
        new Thread(() -> tcpExample.server()).start(); // 在新线程中启动服务器
        tcpExample.client();
        String classpath = System.getProperty("java.class.path");
        System.out.println(classpath);
    }

    public void client() {
        InetAddress inetAddress;
        Socket socket = null;
        OutputStream os = null;
        try {
            inetAddress = InetAddress.getByName("172.31.224.1");
            int port = 8080;

            socket = new Socket(inetAddress, port);
            os = socket.getOutputStream();
            os.write("Hello, server!".getBytes()); // 发送数据

            System.out.println("数据发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        try {
            int port = 8989;
            serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动，等待连接...");

            socket = serverSocket.accept();
            System.out.println("客户端已连接");

            is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.println("接收到数据: " + str);
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