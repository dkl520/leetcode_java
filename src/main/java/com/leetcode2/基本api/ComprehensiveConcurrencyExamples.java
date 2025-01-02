package com.leetcode2.基本api;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



//基本Thread类
//Runnable接口
//Callable和Future
//CompletableFuture
//各种线程池
//synchronized关键字
//ReentrantLock
//BlockingQueue
//CountDownLatch
//CyclicBarrier
//Semaphore
//Atomic类
//
//每个示例都包含了基本的用法演示。主要特点：
//
//Thread：最基本的线程创建方式
//Runnable：更灵活的线程任务定义方式
//Callable：可以返回结果的任务
//Future：获取异步计算结果
//CompletableFuture：支持组合的异步编程
//线程池：管理线程的生命周期
//synchronized：内置的同步机制
//Lock：更灵活的锁机制
//BlockingQueue：线程安全的队列
//CountDownLatch：等待多个线程完成
//CyclicBarrier：同步多个线程
//Semaphore：控制并发访问数量
//Atomic：原子操作类

public class ComprehensiveConcurrencyExamples {

    // 1. 基本的Thread类示例
    static class BasicThreadExample {
        public static void demonstrate() {
            // 创建一个新的线程，并在其中运行一个简单的任务
            Thread thread = new Thread(() -> {
                System.out.println("Running in " + Thread.currentThread().getName());
            });
            thread.start(); // 启动线程
        }
    }

    // 2. Runnable接口示例
    static class RunnableExample implements Runnable {
        public void run() {
            // 在当前线程中运行任务
            System.out.println("Runnable running in " + Thread.currentThread().getName());
        }

        public static void demonstrate() {
            // 创建一个新的线程，并传入Runnable任务
            Thread thread = new Thread(new RunnableExample());
            thread.start(); // 启动线程
        }
    }

    // 3. Callable和Future示例
    static class CallableExample {
        public static void demonstrate() throws ExecutionException, InterruptedException {
            // 创建一个单线程的ExecutorService
            ExecutorService executor = Executors.newSingleThreadExecutor();
            // 提交一个Callable任务，并返回一个Future对象
            Future<String> future = executor.submit(() -> {
                Thread.sleep(1000); // 模拟耗时操作
                return "Callable result"; // 返回结果
            });
            // 获取并打印Future的结果
            System.out.println("Future result: " + future.get());
            executor.shutdown(); // 关闭ExecutorService
        }
    }

    // 4. CompletableFuture示例
    static class CompletableFutureExample {
        public static void demonstrate() throws ExecutionException, InterruptedException {
            // 创建一个异步任务，并在完成后进行转换
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000); // 模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "First result"; // 返回初始结果
            }).thenApply(result -> result + " transformed"); // 转换结果

            // 获取并打印CompletableFuture的结果
            System.out.println("CompletableFuture result: " + future.get());
        }
    }

    // 5. 线程池示例
    static class ThreadPoolExample {
//        public static void demonstrate() {
//            // 创建不同类型的线程池
//            ExecutorService fixedPool = Executors.newFixedThreadPool(2); // 固定大小线程池
//            ExecutorService cachedPool = Executors.newCachedThreadPool(); // 缓存线程池
//            ExecutorService singlePool = Executors.newSingleThreadExecutor(); // 单线程池
//            ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2); // 调度线程池
//
//            // 使用固定线程池提交任务
//            fixedPool.submit(() -> System.out.println("Task in fixed pool"));
//
//            // 使用调度线程池定期执行任务
//            scheduledPool.scheduleAtFixedRate(
//                () -> System.out.println("Scheduled task"),
//                0, 1, TimeUnit.SECONDS
//            );
//
//            // 注意：实际使用时需要适当关闭线程池
//            fixedPool.shutdown();
//            cachedPool.shutdown();
//            singlePool.shutdown();
//            scheduledPool.shutdown();
//        }
    }

    // 6. synchronized关键字示例
    static class SynchronizedExample {
        private int count = 0;

        // 使用synchronized关键字同步方法
        public synchronized void increment() {
            count++;
        }

        public static void demonstrate() throws InterruptedException {
            SynchronizedExample example = new SynchronizedExample();
            // 创建两个线程，并在其中调用increment方法
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) example.increment();
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) example.increment();
            });

            t1.start(); // 启动线程t1
            t2.start(); // 启动线程t2
            t1.join(); // 等待线程t1结束
            t2.join(); // 等待线程t2结束

            // 打印最终的计数值
            System.out.println("Synchronized count: " + example.count);
        }
    }

    // 7. ReentrantLock示例
    static class ReentrantLockExample {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private boolean flag = false;

        // 等待信号的方法
        public void waitForSignal() throws InterruptedException {
            lock.lock(); // 获取锁
            try {
                while (!flag) {
                    condition.await(); // 等待条件满足
                }
                System.out.println("Received signal");
            } finally {
                lock.unlock(); // 释放锁
            }
        }

        // 发送信号的方法
        public void sendSignal() {
            lock.lock(); // 获取锁
            try {
                flag = true;
                condition.signal(); // 发送信号
            } finally {
                lock.unlock(); // 释放锁
            }
        }

        public static void demonstrate() throws InterruptedException {
            ReentrantLockExample example = new ReentrantLockExample();
            // 创建并启动等待信号的线程
            Thread waiter = new Thread(() -> {
                try {
                    example.waitForSignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            waiter.start();
            Thread.sleep(1000); // 模拟延迟
            example.sendSignal(); // 发送信号
            waiter.join(); // 等待线程结束
        }
    }

    // 8. BlockingQueue示例
    static class BlockingQueueExample {
        public static void demonstrate() throws InterruptedException {
            BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

            // 生产者线程
            Thread producer = new Thread(() -> {
                try {
                    queue.put("First"); // 放入元素
                    queue.put("Second"); // 放入元素
                    System.out.println("Queue is full");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // 消费者线程
            Thread consumer = new Thread(() -> {
                try {
                    Thread.sleep(1000); // 模拟延迟
                    System.out.println("Consumed: " + queue.take()); // 取出元素
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            producer.start(); // 启动生产者线程
            consumer.start(); // 启动消费者线程
            producer.join(); // 等待生产者线程结束
            consumer.join(); // 等待消费者线程结束
        }
    }

    // 9. CountDownLatch示例
    static class CountDownLatchExample {
        public static void demonstrate() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(3);

            // 创建并启动三个线程，每个线程完成任务后减少计数
            for (int i = 0; i < 3; i++) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000); // 模拟任务耗时
                        System.out.println("Task completed");
                        latch.countDown(); // 减少计数
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            latch.await(); // 等待所有任务完成
            System.out.println("All tasks completed");
        }
    }

    // 10. CyclicBarrier示例
    static class CyclicBarrierExample {
        public static void demonstrate() {
            // 创建一个CyclicBarrier，指定屏障动作
            CyclicBarrier barrier = new CyclicBarrier(3, () ->
                System.out.println("All threads reached barrier")
            );

            // 创建并启动三个线程，每个线程在屏障处等待
            for (int i = 0; i < 3; i++) {
                new Thread(() -> {
                    try {
                        System.out.println("Thread waiting at barrier");
                        barrier.await(); // 等待其他线程到达屏障
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    // 11. Semaphore示例
    static class SemaphoreExample {
        public static void demonstrate() {
            Semaphore semaphore = new Semaphore(2);

            // 创建并启动四个线程，每个线程获取和释放信号量
            for (int i = 0; i < 4; i++) {
                new Thread(() -> {
                    try {
                        semaphore.acquire(); // 获取信号量
                        System.out.println("Thread acquired semaphore");
                        Thread.sleep(1000); // 模拟任务耗时
                        semaphore.release(); // 释放信号量
                        System.out.println("Thread released semaphore");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    // 12. AtomicInteger示例
    static class AtomicExample {
        private static AtomicInteger atomicCount = new AtomicInteger(0);

        public static void demonstrate() throws InterruptedException {
            // 创建并启动两个线程，每个线程增加计数
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) atomicCount.incrementAndGet();
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) atomicCount.incrementAndGet();
            });

            t1.start(); // 启动线程t1
            t2.start(); // 启动线程t2
            t1.join(); // 等待线程t1结束
            t2.join(); // 等待线程t2结束

            // 打印最终的计数值
            System.out.println("Atomic count: " + atomicCount.get());
        }
    }

    // 主方法，演示所有并发示例
    public static void main(String[] args) throws Exception {
        System.out.println("1. Basic Thread Example:");
        BasicThreadExample.demonstrate();
        Thread.sleep(100);

        System.out.println("\n2. Runnable Example:");
        RunnableExample.demonstrate();
        Thread.sleep(100);

        System.out.println("\n3. Callable and Future Example:");
        CallableExample.demonstrate();

        System.out.println("\n4. CompletableFuture Example:");
        CompletableFutureExample.demonstrate();

//        System.out.println("\n5. Thread Pool Example:");
//        ThreadPoolExample.demonstrate();
//        Thread.sleep(100);

        System.out.println("\n6. Synchronized Example:");
        SynchronizedExample.demonstrate();

        System.out.println("\n7. ReentrantLock Example:");
        ReentrantLockExample.demonstrate();

        System.out.println("\n8. BlockingQueue Example:");
        BlockingQueueExample.demonstrate();

        System.out.println("\n9. CountDownLatch Example:");
        CountDownLatchExample.demonstrate();

        System.out.println("\n10. CyclicBarrier Example:");
        CyclicBarrierExample.demonstrate();
        Thread.sleep(100);

        System.out.println("\n11. Semaphore Example:");
        SemaphoreExample.demonstrate();
        Thread.sleep(100);

        System.out.println("\n12. AtomicInteger Example:");
        AtomicExample.demonstrate();
    }
}