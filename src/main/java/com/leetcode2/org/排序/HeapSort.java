package com.leetcode2.org.排序;

import java.util.*;

public class HeapSort {
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // 从堆中逐个取出元素，并进行排序
        for (int i = n - 1; i > 0; i--) {
            // 将当前根节点（最大值）与末尾元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 重新构建最大堆
            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }
        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);

        }

    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int n = arr.length;
        System.out.println("原始数组");
        printArray(arr);
        HeapSort heapSort = new HeapSort();
        heapSort.sort(arr);
        System.out.println("排序后的数组");
        printArray(arr);
//        Integer[] pqs = {5, 2, 8, 1, 10};
//        PriorityQueue<Integer> pq = new PriorityQueue<Integer>() {{
//            offer(3);
//            offer(1);
//            offer(2);
//        }};
//        System.out.println(pq.toString());
        PriorityQueue<Integer> pqss = new PriorityQueue<>(Comparator.reverseOrder());

        PriorityQueue<Integer> pqk = new PriorityQueue<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, Comparator.reverseOrder()) {{
            offer(3);
            offer(1);
            offer(2);
        }};
        System.out.println(pqss.toString());

        PriorityQueue<Integer> pqz = new PriorityQueue<>(List.of(3, 1, 2));
        pqz.add(3);
        pqz.add(1);
        pqz.add(2);
        pqz.add(4);
//
//        // 输出队列中的元素
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        System.out.println(pqz.toString());
    }

    // 打印数组元素
    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
