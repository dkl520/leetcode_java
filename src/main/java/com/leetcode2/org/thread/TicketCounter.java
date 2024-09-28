package com.leetcode2.org.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketCounter {
    private static final int TOTAL_TICKETS = 100;
    private AtomicInteger currentTicketNumber = new AtomicInteger(1);

    public int getNextTicketNumber() {
        int ticket = currentTicketNumber.getAndIncrement();
        if (ticket <= TOTAL_TICKETS) {
            return ticket;
        } else {
            return -1; // 表示票已售罄
        }

    }
    public synchronized boolean hasAvailableTickets() {
        return currentTicketNumber.get() <= TOTAL_TICKETS;
    }
    public void bookTicket(String windowName) {
        int ticket = getNextTicketNumber();
        if (ticket != -1) {
            System.out.println(windowName + " 售出票号为 " + ticket + " 的票");
        } else {
            System.out.println(windowName + " 票已售罄");
        }
    }
}

 class Window implements Runnable {
    private String name;
    private TicketCounter ticketCounter;

    public Window(String name, TicketCounter ticketCounter) {
        this.name = name;
        this.ticketCounter = ticketCounter;
    }

    @Override
    public void run() {
        while (ticketCounter.hasAvailableTickets()) {
            ticketCounter.bookTicket(name);
            try {
                Thread.sleep(100); // 模拟售票过程中的其他操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

