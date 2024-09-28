package com.leetcode2.org.thread;

public class TrainTicketBooking {
    public static void main(String[] args) {
        TicketCounterLock ticketCounterLock = new TicketCounterLock(100);
        // 创建多个售票窗口
        Thread window1 = new Thread(new Windows("窗口1", ticketCounterLock));
        Thread window2 = new Thread(new Windows("窗口2", ticketCounterLock));
        Thread window3 = new Thread(new Windows("窗口3", ticketCounterLock));
        Thread window4 = new Thread(new Windows("窗口4", ticketCounterLock));

        // 启动窗口线程
        window1.start();
        window2.start();
        window3.start();
        window4.start();
        // 等待所有窗口线程结束
        try {
            window1.join();
            window2.join();
            window3.join();
            window4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有窗口售票结束，关闭售票窗口");
    }
}
