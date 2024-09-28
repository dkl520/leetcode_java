
package com.leetcode2.org.thread;

class EvenNumPrint implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "******************");
            }
        }
    }
}

public class ThreaTest {

    public static void main(String[] args) {

        EvenNumPrint p1 = new EvenNumPrint();
        Thread t1 = new Thread(p1);
        t1.start();
    }


}
