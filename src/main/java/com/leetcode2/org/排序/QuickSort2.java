package com.leetcode2.org.排序;

public class QuickSort2 {
    // 快速排序主函数
    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right); // 获取分区点
            quickSort(array, left, pivotIndex - 1); // 排序左子数组
            quickSort(array, pivotIndex + 1, right); // 排序右子数组
        }
    }

    // 分区函数
    private static int partition(int[] array, int left, int right) {
        int pivot = array[right]; // 选择最右边的元素作为基准点
        int i = left - 1; // i 初始化为 left - 1

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) { // 如果当前元素小于等于基准点
                i++;
                swap(array, i, j); // 交换 i 和 j 位置的元素
            }
        }
        swap(array, i + 1, right); // 最后将基准点元素交换到 i + 1 位置
        return i + 1; // 返回基准点索引
    }

    // 交换数组中两个元素的位置
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 主函数，用于测试快速排序算法
    public static void main(String[] args) {
        int[] array = {9, 3, 7, 5, 6, 2, 8, 1, 4}; // 测试数组
        System.out.println("排序前:");
        printArray(array);
        quickSort(array, 0, array.length - 1); // 调用快速排序函数
        System.out.println("排序后:");
        printArray(array);
    }

    // 打印数组
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
