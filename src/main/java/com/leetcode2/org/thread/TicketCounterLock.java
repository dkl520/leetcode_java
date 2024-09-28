// 声明一个名为org.thread的包（package）。
package com.leetcode2.org.thread;

// 导入java.util.concurrent.atomic包下的AtomicInteger类，这个类提供原子性增强的int值。
import java.util.concurrent.atomic.AtomicInteger;
// 导入java.util.concurrent.locks包下的Lock和ReentrantLock类，这些类提供线程间的同步机制。
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 声明一个名为TicketCounterLock的公共类，该类位于org.thread包内。
public class TicketCounterLock {
    // 声明一个私有的整型变量TOTAL_TICKETS，表示总票数。
    private int TOTAL_TICKETS ;
    // 声明一个私有的AtomicInteger类型的变量currentTicketNumber，初始化为1，表示当前售出的票号。
    private AtomicInteger currentTicketNumber = new AtomicInteger(1);
    // 声明一个私有的ReentrantLock类型的变量lock，用于同步访问。
    private Lock lock = new ReentrantLock();

    // 构造函数，接受一个int类型的参数TOTAL_TICKETS，用于设置总票数。
    public TicketCounterLock(int TOTAL_TICKETS) {
        this.TOTAL_TICKETS = TOTAL_TICKETS;
    }

    // 定义一个公共方法getNextTicketNumber，用于获取下一个可用的票号。
    public int getNextTicketNumber() {
        // 使用lock对象进行加锁，确保多线程安全。
        lock.lock();
        try {
            // 获取当前票号，并递增。
            int ticket = currentTicketNumber.getAndIncrement();
            // 如果新生成的票号小于等于总票数，则返回该票号。否则返回-1表示售罄。
            return ticket <= TOTAL_TICKETS ? ticket : -1;
        } finally {
            // 使用lock对象进行解锁。
            lock.unlock();
        }
    };

    // 定义一个公共方法hasAvailableTickets，用于判断是否还有可用的票。
    public boolean hasAvailableTickets() {
        // 返回当前票号是否小于等于总票数。
        return currentTicketNumber.get() <= TOTAL_TICKETS;
    };

    // 定义一个公共方法bookTicket，接受一个String类型的参数windowName，用于预订票。
    public void bookTicket(String windowName) {
        // 调用getNextTicketNumber方法获取下一个票号。
        int ticket = getNextTicketNumber();
        // 如果票号不等于-1（表示有票），则打印出售出的票号和窗口名称。否则打印出窗口名称和提示信息（表示票已售罄）。
        if (ticket != -1) {
            System.out.println(windowName + " 售出票号为 " + ticket + "的票");
        } else {
            System.out.println(windowName + "票已售罄");
        }
    }
}



// 定义一个名为Windows的类，它实现了Runnable接口，这使得它能够被线程执行。
class Windows implements Runnable {
    // 声明一个私有的String类型的变量name，表示窗口名称。
    private String name;
    // 声明一个私有的TicketCounterLock类型的变量TicketCounterLock，这是一个售票计数器，用于同步控制窗口的售票操作。
    private TicketCounterLock TicketCounterLock;

    // 构造函数，接受两个参数，一个是String类型的name（窗口名称），一个是TicketCounterLock类型的TicketCounterLock（售票计数器）。
    public Windows(String name, TicketCounterLock TicketCounterLock) {
        // 将传入的窗口名称赋值给本地的窗口名称变量。
        this.name = name;
        // 将传入的售票计数器赋值给本地的售票计数器变量。
        this.TicketCounterLock = TicketCounterLock;
    }

    // 重写Runnable接口的run方法，这是线程运行的入口。在这个方法中，Windows类会不断地检查售票计数器是否有可用的票，如果有则预订并打印出信息。如果没有则等待100毫秒后再次检查。
    @Override
    public void run() {
        // 当售票计数器还有可用票时，执行循环。
        while (TicketCounterLock.hasAvailableTickets()) {
            // 使用售票计数器的bookTicket方法预订票，并传入窗口名称作为参数。
            TicketCounterLock.bookTicket(name);
            try {
                // 使当前线程休眠100毫秒（0.1秒），以让其他线程有机会执行。这可以防止CPU资源的浪费。如果在这段时间内发生线程中断，则抛出InterruptedException异常。
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // 当出现InterruptedException异常时，打印异常的堆栈跟踪信息。这是Java的标准错误处理方式。
                e.printStackTrace();
            }
        }
    }
}