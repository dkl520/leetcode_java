package com.leetcode2.org.thread;

public class Test {

    public static void main(String[] args) {
        PrintNum p1 = new PrintNum();
        OddNumberPrint p2 = new OddNumberPrint();
        p1.start();
        p2.start();
//        p1.run();
//        p2.run();
//        for (int i = 0; i < 10000; i++) {
//            if (i % 2 == 0) {
//                System.out.println(Thread.currentThread().getName()+":"+i+"********");
//            }
//        }
    }




}
