// 定义一个线程通信的示例类，其中涉及两个线程（senderThread和receiverThread）和一个Message对象。
package com.leetcode2.org.thread;

// 主类，程序的入口点。它创建了两个线程和Message对象，并启动这两个线程。
public class ThreadCommunicationExample {
    public static void main(String[] args) {
        // 创建一个Message对象。
        Message message = new Message();
        // 创建一个新的发送线程，将Message对象作为参数传递给线程。
        Thread senderThread = new Thread(new Sender(message));
        // 创建一个新的接收线程，同样将Message对象作为参数传递给线程。
        Thread receiverThread= new Thread(new Receiver(message));
        // 启动发送线程。
        senderThread.start();
        // 启动接收线程。
        receiverThread.start();
    }
}

// Message类，用于在线程之间发送和接收消息。它包含两个成员变量：content（消息内容）和hasNewmessage（表示是否有新消息的标志）。
class Message {
    // 消息内容。
    private String content;
    // 是否有新消息的标志。
    private boolean hasNewmessage;

    // send方法，向Message对象发送一个新的消息。如果hasNewmessage为true，则当前线程会等待，直到它变为false。然后，新的消息会被设置到content变量中，hasNewmessage被设置为true，并打印一条消息表明新的消息已经被发送。之后唤醒其他正在等待的线程。
    public synchronized void send(String message) {
        while (hasNewmessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.content = message;
        hasNewmessage = true;
        System.out.println("发送消息：" + message);
        notify();
    }
    // receive方法，从Message对象中接收一条消息。如果hasNewmessage为false，则当前线程会等待，直到它变为true。
    // 然后，新的消息会被从content变量中获取，hasNewmessage被设置为false，并打印一条消息表明新的消息已经被接收。
    // 之后唤醒其他正在等待的线程。
    public synchronized String receive() {
        while (!hasNewmessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasNewmessage = false;
        String message = this.content;
        System.out.println("接受消息" + message);
        notify();
        return message;
    }
}
// 定义一个名为Sender的类，它实现了Runnable接口，表示这个类可以被线程执行
class Sender implements Runnable {
    // 声明一个私有的Message类型的成员变量message，表示发送的消息
    private Message message;

    // 定义一个构造函数，参数是Message类型，将传入的消息赋值给成员变量message
    public Sender(Message message) {
        this.message = message;
    }

    // 实现Runnable接口的run方法，这是线程启动后首先执行的方法
    @Override
    public void run() {
        // 定义一个字符串数组messages，包含三个元素：Hello, World, Goodbye
        String[] messages = {"Hello", "World", "Goodbye"};
        // 遍历messages数组
        for (String message : messages) {
            // 使用成员变量message的send方法发送消息，参数是当前循环到的字符串message
            this.message.send(message);
            // 使当前线程休眠1000毫秒（即1秒），模拟发送消息后的延迟或接收者的接收过程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 如果线程被中断，抛出一个运行时异常，中断原始线程并终止当前线程的执行
                throw new RuntimeException(e);
            }
        }
    }
}
// 定义一个名为Receiver的类，它也实现了Runnable接口，表示这个类也可以被线程执行
class Receiver implements Runnable{
    // 声明一个私有的Message类型的成员变量message，表示接收的消息
    private Message message;
    // 定义一个构造函数，参数是Message类型，将传入的消息赋值给成员变量message
    public Receiver(Message message){
        this.message= message;
    }
    // 实现Runnable接口的run方法，这是线程启动后首先执行的方法
    @Override
    public void run(){
        // 循环三次，表示接收三次消息
        for (int i = 0; i <3 ; i++) {
            // 使用成员变量message的receive方法接收消息，但代码中并没有给出该方法的定义，我假设它是一个接收消息的方法
            this.message.receive();
        }
    }
}