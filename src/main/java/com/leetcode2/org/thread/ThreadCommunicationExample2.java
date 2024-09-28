// 声明一个名为org.thread的包
package com.leetcode2.org.thread;

// 导入java.util.concurrent.locks.Condition类
import java.util.concurrent.locks.Condition;

// 导入java.util.concurrent.locks.Lock类
import java.util.concurrent.locks.Lock;

// 导入java.util.concurrent.locks.ReentrantLock类
import java.util.concurrent.locks.ReentrantLock;
//Java中的生产者-消费者模型是一种常见的并发编程模型，它用于描述两个进程或线程之间的通信和同步问题。在这个模型中，生产者负责生成数据，然后将这些数据推送到共享的缓冲区；
//        消费者则从缓冲区中取出数据进行处理。Java中的生产者-消费者模型通常使用wait()和notify()方法实现，这些方法可以用于协调生产和消费的速度。当缓冲区为空时，
//        消费者会调用wait()方法等待生产者生产数据；当缓冲区满时，生产者会调用notify()方法通知消费者可以继续消费。
//        除了wait()和notify()方法外，Java还提供了Lock和Condition接口来实现更灵活的同步和通信机制。使用Lock和Condition接口可以避免使用wait()和notify()方法的限制，
//        例如它们可以支持多个等待的线程，并且可以提供更精细的控制。
//        此外，Java还提供了BlockingQueue接口来实现高级的生产者-消费者模型。BlockingQueue接口提供了一个阻塞队列，生产者可以将数据添加到队列中，而消费者可以从队列中取出数据
//        。如果队列为空，消费者会被阻塞直到生产者生产数据；如果队列已满，生产者会被阻塞直到消费者消费了数据。这种方式可以避免手动调用wait()和notify()方法的麻烦，
//        而且可以更有效地利用资源。
// 定义一个公开类ThreadCommunicationExample2，该类包含main方法，用于启动线程通信的示例
// 定义一个公开类ThreadCommunicationExample2，该类包含main方法，用于启动线程通信的示例
public class ThreadCommunicationExample2 {
    // 定义main方法，接受一个字符串数组作为参数，该方法是Java程序的入口点
    public static void main(String[] args) {
        // 创建一个Msg对象，此对象将在发送线程和接收线程之间进行通信
        Msg message = new Msg();

        // 创建发送线程和接收线程，这两个线程将共享上面创建的Msg对象
        // 创建发送线程和接收线程
        Thread senderThread = new Thread(new SenderMachine(message));
        Thread receiverThread = new Thread(new ReceiverMachine(message));

        // 启动发送线程和接收线程，使它们并发执行
        // 启动线程
        senderThread.start();
        receiverThread.start();
    }
}

// 定义一个名为Msg的类，该类用于线程间的通信
class Msg {
    // 定义一个私有字符串变量content，表示消息的内容
    private String content;
    // 定义一个私有布尔变量hasNewMessage，表示是否有新消息要发送或接收
    private boolean hasNewMessage;
    // 定义一个私有的Lock对象，用于实现互斥锁，保证线程安全
    private final Lock lock;
    // 定义一个Condition对象，用于实现条件变量，使线程能够等待或通知其他线程
    private final Condition sendCondition;
    // 定义另一个Condition对象，用于实现另一个条件变量，使线程能够等待或通知其他线程
    private final Condition receiveCondition;

    // Msg类的构造函数，创建一个新的Msg对象时会被调用
    public Msg() {
        // 创建一个新的ReentrantLock对象作为互斥锁，保证线程安全
        lock = new ReentrantLock();
        // 使用lock对象的newCondition方法创建两个条件变量，sendCondition和receiveCondition
        sendCondition = lock.newCondition();
        receiveCondition = lock.newCondition();
    }

    // 定义一个名为send的方法，用于发送消息到接收线程。该方法使用互斥锁和条件变量实现同步和条件等待。
    // 发送消息
    public void send(String message) {
        // 使用互斥锁lock保证线程安全，如果当前线程不持有该锁，则阻塞当前线程直到获取到锁。
        lock.lock();
        try {
            // 使用while循环来检查是否有新消息要发送，如果有新消息则等待接收线程处理。
            while (hasNewMessage) {
                try { // 如果hasNewMessage为true，则当前发送线程等待接收线程处理消息。在此处可能发生阻塞。
                    // 使用sendCondition的await方法使当前线程等待，直到其他线程调用该condition的signal或signalAll方法唤醒。
                    sendCondition.await();
                } catch (InterruptedException e) { // 如果当前线程在等待时被中断，则抛出中断异常。
                    e.printStackTrace();
                }
            }
            // 设置content为输入的消息内容。
            this.content = message;
            // 设置hasNewMessage为true表示有新消息。
            hasNewMessage = true;
            // 打印消息发送成功的提示信息。
            System.out.println("发送消息：" + message);
            // 使用receiveCondition的signal方法通知正在等待的接收线程有新消息可以处理。
            receiveCondition.signal(); // 通知接收线程有新消息
        } finally { // 在finally块中释放锁，无论try块中是否发生异常。
            lock.unlock();
        }   // 结束发送方法。
    }   // 结束Msg类的定义。

    // 接收消息
    public String receive() {
        // 使用互斥锁保护下面的代码块，确保同时只有一个线程可以执行下面的代码
        lock.lock();
        try {
            // 如果hasNewMessage为false，表示没有新消息可以接收，那么就等待发送线程发送消息
            while (!hasNewMessage) {
                // 在此处，当前线程会被操作系统挂起，直到其他线程调用此处的receiveCondition.await()为止
                try {
                    // 等待其他线程的通知，如果没有接收到通知，那么这个线程会一直处于等待状态，直到收到为止
                    receiveCondition.await();
                } catch (InterruptedException e) {
                    // 如果在等待过程中线程被其他线程中断，那么就会抛出InterruptedException异常
                    e.printStackTrace();
                }
            }
            // 当接收到新消息后，设置hasNewMessage为false，表示已经接收到了新消息
            hasNewMessage = false;
            // 从content中取出消息内容，赋值给message变量
            String message = this.content;
            // 打印出接收到的消息内容
            System.out.println("接收消息：" + message);
            // 通知可能在等待的发送线程，已经有接收线程可以接收消息了
            sendCondition.signal(); // 通知发送线程已接收消息
            // 返回接收到的消息内容
            return message;
        } finally {
            // 在finally块中释放锁，无论try块中是否发生异常。确保在任何情况下，锁都能被正确的释放。
            lock.unlock();
        }
    }
}

// 定义一个名为SenderMachine的类，它实现了Runnable接口，表示这个类是一个线程。
class SenderMachine implements Runnable {

    // 定义一个私有变量message，类型为Msg，用来存储消息。
    private Msg message;

    // 定义一个构造函数，参数为Msg类型的message，用来初始化SenderMachine对象中的message。
    public SenderMachine(Msg message) {
        this.message = message;  // 将传入的message赋值给当前对象的message。
    }

    // 重写Runnable接口的run方法，这是线程启动后执行的方法。
    @Override
    public void run() {

        // 定义一个字符串数组messages，包含了三个消息："Hello", "World", "Goodbye"。
        String[] messages = {"Hello", "World", "Goodbye"};

        // 使用增强for循环遍历messages数组。
        for (String message : messages) {
            // 调用message的send方法发送消息。
            this.message.send(message);

            try {
                // 线程暂停1000毫秒（1秒），模拟发送消息的时间间隔。
                Thread.sleep(1000); // 模拟发送消息的时间间隔
            } catch (InterruptedException e) {
                // 如果线程在睡眠过程中被中断，会抛出InterruptedException异常，打印异常堆栈信息。
                e.printStackTrace();
            }
        }
    }  // run方法结束。
}  // SenderMachine类结束。

// 定义一个名为ReceiverMachine的类，它也实现了Runnable接口，表示这个类是一个线程。
class ReceiverMachine implements Runnable {

    // 定义一个私有变量message，类型为Msg，用来存储消息。
    private Msg message;

    // 定义一个构造函数，参数为Msg类型的message，用来初始化ReceiverMachine对象中的message。
    public ReceiverMachine(Msg message) {
        this.message = message;  // 将传入的message赋值给当前对象的message。
    }

    // 重写Runnable接口的run方法，这是线程启动后执行的方法。
    @Override
    public void run() {
        // 使用for循环遍历3次。
        for (int i = 0; i < 3; i++) {
            // 调用message的receive方法接收消息。
            this.message.receive();
        }
    }  // run方法结束。
}  // ReceiverMachine类结束。