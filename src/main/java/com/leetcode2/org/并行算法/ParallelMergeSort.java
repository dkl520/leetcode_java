package com.leetcode2.org.并行算法;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

// 并行归并排序类，继承自RecursiveTask，用于并行处理数组排序
public class ParallelMergeSort extends RecursiveTask<int[]> {
    // 待排序的数组
    private final int[] array;

    // 构造函数，接收待排序的数组
    public ParallelMergeSort(int[] array) {
        this.array = array;
    }

    // 重写compute方法，这是RecursiveTask的核心方法，用于定义任务的具体逻辑
    @Override
    protected int[] compute() {
        // 如果数组长度小于等于1，则无需排序，直接返回
        if (array.length <= 1) {
            return array;
        }

        // 找到数组中间位置，用于将数组拆分为两部分
        int mid = array.length / 2;

        // 创建两个子任务，分别处理数组的两部分
        // 左侧子任务处理前半部分数组
        ParallelMergeSort leftTask = new ParallelMergeSort(Arrays.copyOfRange(array, 0, mid));
        // 右侧子任务处理后半部分数组
        ParallelMergeSort rightTask = new ParallelMergeSort(Arrays.copyOfRange(array, mid, array.length));

        // 使用ForkJoinPool的invokeAll方法并行执行两个子任务
        // 注意：这里应该使用ForkJoinPool的实例来调用invokeAll方法，而不是直接在ParallelMergeSort类中调用
        // 假设存在一个ForkJoinPool的实例叫做pool，则应该这样调用：pool.invokeAll(leftTask, rightTask);
         invokeAll(leftTask, rightTask);

        // 等待两个子任务完成，并获取它们的排序结果
        // 注意：这里应该使用leftTask.get()和rightTask.get()来获取结果，而不是join()
        // 假设上面使用了invokeAll方法，则这里应该这样获取结果：leftTask.get()和rightTask.get()
         return merge(leftTask.join(), rightTask.join());

        // 合并两个已排序的子数组，并返回最终排序结果
        // 注意：这里应该使用上面提到的正确方式获取子任务的结果，然后传递给merge方法
        // 假设正确获取了子任务的结果leftArr和rightArr，则这里应该这样调用：return merge(leftArr, rightArr);
//        return merge(leftTask.join(), rightTask.join());
    }

    // 合并两个已排序的数组
    private int[] merge(int[] left, int[] right) {
        // 创建一个新数组，用于存放合并后的结果
        int[] result = new int[left.length + right.length];
        // 定义三个指针，分别指向新数组、左数组和右数组的当前位置
        int i = 0, j = 0, k = 0;

        // 遍历左数组和右数组，将较小的元素放入新数组中
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        // 将左数组中剩余的元素放入新数组中
        while (i < left.length) {
            result[k++] = left[i++];
        }
        // 将右数组中剩余的元素放入新数组中
        while (j < right.length) {
            result[k++] = right[j++];
        }
        // 返回合并后的结果数组
        return result;
    }

    public static void main(String[] args) {
        // 创建ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 准备数据
        int[] array = {5, 3, 8, 1, 7, 6, 2, 4};
        // 创建任务并提交给ForkJoinPool
        ParallelMergeSort task = new ParallelMergeSort(array);
        int[] sortedArray = forkJoinPool.invoke(task);
        // 输出排序结果
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
        // 关闭ForkJoinPool
        forkJoinPool.shutdown();
    }
}
