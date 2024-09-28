// 声明一个名为org.thread的包（package）。  
package com.leetcode2.org.thread;

// 声明一个公共类DeadlockExample，该类位于org.thread包内。  
public class DeadlockExample {
    // 声明两个静态对象，它们被用作锁。线程将通过这些锁来同步其操作。  
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    // 这是主方法，程序的入口点。  
    public static void main(String[] args) {
        // 创建一个新的线程thread1。这个线程将执行一个Lambda表达式，该表达式包含它的任务。  
        Thread thread1 = new Thread(() -> {
            // 使用lock1作为锁，同步进入的代码块。这意味着，在执行此代码块内的任何代码之前，必须首先获得lock1的锁。  
            synchronized (lock1) {
                // 打印一条消息，表明线程1已经获得了lock1的锁。  
                System.out.println("Thread 1: Holding lock 1");

                try {
                    // 让线程1休眠1000毫秒（即1秒）。这模拟线程在进行某项任务的过程中暂时无法获取锁的情况。  
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 如果线程1在休眠时被其他线程中断，那么它会抛出InterruptedException。这里打印出异常的堆栈跟踪信息。  
                    e.printStackTrace();
                }

                // 打印一条消息，表明线程1现在正在等待获取lock2的锁。  
                System.out.println("Thread 1: Waiting for lock 2");

                // 使用lock2作为锁，同步进入的代码块。这表示，在执行此代码块内的任何代码之前，必须首先获得lock2的锁。  
                synchronized (lock2) {
                    // 打印一条消息，表明线程1现在同时持有lock1和lock2的锁。这种情况通常被称为死锁，因为它是一个自我锁定的状态，每个线程都在等待另一个线程释放其锁。  
                    System.out.println("Thread 1: Holding lock 1 and lock 2");
                }
            }
        });

        // 创建第二个线程thread2，它将执行一个与thread1相同的Lambda表达式。  
        Thread thread2 = new Thread(() -> {
            // 使用lock2作为锁，同步进入的代码块。这意味着，在执行此代码块内的任何代码之前，必须首先获得lock2的锁。  
            synchronized (lock2) {
                // 打印一条消息，表明线程2已经获得了lock2的锁。  
                System.out.println("Thread 2: Holding lock 2");

                try {
                    // 让线程2休眠1000毫秒（即1秒）。这模拟线程在进行某项任务的过程中暂时无法获取锁的情况。  
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 如果线程2在休眠时被其他线程中断，那么它会抛出InterruptedException。这里打印出异常的堆栈跟踪信息。  
                    e.printStackTrace();
                }

                // 打印一条消息，表明线程2现在正在等待获取lock1的锁。  
                System.out.println("Thread 2: Waiting for lock 1");

                // 使用lock1作为锁，同步进入的代码块。这表示，在执行此代码块内的任何代码之前，必须首先获得lock1的锁。由于此时lock1已经被thread1持有，因此thread2会等待直到thread1释放lock1。同样地，由于lock2也被thread2持有，因此thread1也会等待直到thread2释放lock2。这就是死锁的情况：两个线程都在等待对方释放其锁。  
                synchronized (lock1) {
                    // 打印一条消息，表明线程2现在同时持有lock2和lock1的锁。但是因为两个线程都在等待对方释放其锁，所以它们都无法继续执行下去，形成了一个死锁。  
                    System.out.println("Thread 2: Holding lock 2 and lock 1");
                }
            }
        });

        // 启动线程thread1和thread2。它们将并发地执行其任务，但是由于存在死锁的可能性（即每个线程都持有一个锁并等待另一个线程释放其另一个锁），所以它们可能无法同时完成任务。这就是死锁的情况。  
        thread1.start();
        thread2.start();

    }
}