package com.leetcode2.org.StringDemo;

import java.io.UnsupportedEncodingException;

public class StringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        StringBuilder str = new StringBuilder("HELLO");
        System.out.println(str);

        String s1 = "hello";
//         st1 = st1 + "world";
        String s2 = "helloworld中国";
        String s3 = "hello" + "world";
        String s5 = s1 + "world";
        String s8 = s5.intern();
        System.out.println(s8 == s3);

        char[] arr = s2.toCharArray();

//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }

        byte[] blist= s2.getBytes("utf-8");
        for (int i = 0; i <blist.length ; i++) {
            System.out.println(blist[i]);
        }
        String sByte= new String(blist);
        String sByte2 = new String(blist,"gbk");
        System.out.println(sByte);
        System.out.println(sByte2);


        StringBuffer ss= new StringBuffer("1212222");
        System.out.println(ss);
    }


}
