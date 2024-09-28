// 定义一个在Java的ForkJoinPool中执行的并行算法任务，用于计算数组的总和。  
package com.leetcode2.org.并行算法;

import java.time.LocalTime;
import java.util.concurrent.RecursiveTask; // 导入Java的RecursiveTask类，它是ForkJoinTask的子类，用于递归任务。
import java.util.concurrent.ForkJoinPool;  // 导入Java的ForkJoinPool类，它是执行ForkJoinTask的线程池。  

// ForkJoinSum类继承自RecursiveTask<Long>，代表这是一个递归任务，并且它的返回值类型是Long。  
public class ForkJoinSum extends RecursiveTask<Long> {
    // 定义阈值，当任务的大小小于或等于这个值时，任务将不再被拆分。  
    private static final int THRESHOLD = 1000;
    // 数组，我们要计算其总和。  
    private final long[] array;
    // 任务的起始索引。  
    private final int start;
    // 任务的结束索引。  
    private final int end;
    // 构造函数，初始化数组和任务的起始、结束索引。  
    public ForkJoinSum(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    // 重写compute方法，这是RecursiveTask的核心方法，用于计算任务的结果。  
    @Override
    protected Long compute() {
        // 如果任务的大小（即end-start）小于或等于阈值，则直接计算并返回结果。  
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // 如果任务的大小大于阈值，则拆分任务。  
            int mid = (start + end) / 2;

            // 创建左半部分的任务。  
            ForkJoinSum leftTask = new ForkJoinSum(array, start, mid);
            // 创建右半部分的任务。  
            ForkJoinSum rightTask = new ForkJoinSum(array, mid, end);
            // 异步执行左半部分的任务，不等待其完成。  
            leftTask.fork();
            // 同步执行右半部分的任务，并获取其结果。  
            long rightResult = rightTask.compute();
            // 获取左半部分任务的结果，这会等待任务完成。  
            long leftResult = leftTask.join();
            // 返回两部分结果的和。  
            return leftResult + rightResult;
        }
    }

    // 主函数，程序的入口点。  
    public static void main(String[] args) {
        // 创建一个ForkJoinPool实例，它是执行并行任务的线程池。  
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 准备一个要计算总和的数组，数组的每个元素都是其索引加1。  
        int arraySize = 10000;
        long[] array = new long[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = i + 1;
        }
        long startTime = System.nanoTime();
        // 创建一个ForkJoinSum任务实例，代表整个数组的总和计算任务。  
        ForkJoinSum task = new ForkJoinSum(array, 0, array.length);
        // 将任务提交给ForkJoinPool执行，并获取结果。  
        long result = forkJoinPool.invoke(task);
        // 输出结果。
        System.out.println("Sum: " + result);
        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1_000_000;
        System.out.println("代码执行时间: " + durationMillis + " 毫秒");
        // 关闭ForkJoinPool，释放资源。  
        forkJoinPool.shutdown();
    }
}

//在这个例子中，ForkJoinSum继承自RecursiveTask，它表示一个可以拆分的任务。在compute方法中，
//  首先检查任务的大小是否小于等于阈值（THRESHOLD），如果是，则直接计算结
//        否则，将任务拆分成两个子任务，并使用fork()和join()来实现异步执行和获取结果。最后，在main方法中，创建一个ForkJoinPool并提交任务，得到最终的计算结果。
//在这个例子中，ForkJoinSum继承自RecursiveTask，它表示一个可以拆分的任务。
//        在compute方法中，首先检查任务的大小是否小于等于阈值（THRESHOLD），如果是，则直接计算结果；
//        否则，将任务拆分成两个子任务，并使用fork()和join()来实现异步执行和获取结果。
//        最后，在main方法中，创建一个ForkJoinPool并提交任务，得到最终的计算结果