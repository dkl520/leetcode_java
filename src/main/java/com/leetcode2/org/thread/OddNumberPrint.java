package com.leetcode2.org.thread;

public class OddNumberPrint extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

