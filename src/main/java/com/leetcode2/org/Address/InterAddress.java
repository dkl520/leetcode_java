package com.leetcode2.org.Address;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InterAddress {
    public static void main(String[] args) {

        try {
            InetAddress inet1= InetAddress.getByName("192.168.10.1");
            System.out.println(inet1);
            InetAddress inet3= InetAddress.getLocalHost();
            System.out.println(inet3);
            InetAddress inet2= InetAddress.getByName("www.douyin.com");
            System.out.println(inet2);

        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
