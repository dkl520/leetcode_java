package com.leetcode2.org.Address;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest {
    public static void main(String[] args) {
        TCPTest tcpTest = new TCPTest();
        tcpTest.client();
    }

    public void client() {
        InetAddress inetAddress;
        Socket socket = null;
        OutputStream os = null;
        try {
            inetAddress = InetAddress.getByName("192.168.1.6");
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


}