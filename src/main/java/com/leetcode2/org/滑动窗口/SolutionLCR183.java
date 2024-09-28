package com.leetcode2.org.滑动窗口;

import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

public class SolutionLCR183 {
    /**
     * 使用滑动窗口和双端队列找出每个窗口的最大高度
     * @param heights 整数数组，表示高度
     * @param limit 窗口的大小
     * @return 每个窗口的最大高度数组
     */
    public int[] maxAltitude(int[] heights, int limit) {
        Deque<Integer> deque = new ArrayDeque<Integer>(); // 使用双端队列来存储当前窗口内可能的最大高度索引
        if (heights.length == 0) return heights; // 如果输入数组为空，直接返回
        int[] results = new int[heights.length - limit + 1]; // 初始化结果数组，长度为原数组长度减去窗口大小加1

        // 处理第一个窗口的元素
        for (int i = 0; i < limit; i++) {
            // 从队列尾部移除比当前高度小的索引，保证队列尾部始终是窗口内的最大高度索引
            while (!deque.isEmpty() && heights[deque.getLast()] < heights[i]) {
                deque.removeLast();
            }
            deque.addLast(i); // 将当前索引加入队列
        }
        results[0] = heights[deque.getFirst()]; // 第一个窗口的最大高度

        // 处理剩余窗口的元素
        for (int i = limit; i < heights.length; i++) {
            // 同上，从队列尾部移除比当前高度小的索引
            while (!deque.isEmpty() && heights[deque.getLast()] < heights[i]) {
                deque.removeLast();
            }

            // 如果队列头部的索引已经不在当前窗口内，则从队列头部移除
            if (!deque.isEmpty() && i - deque.peek() >= limit) {
                deque.pollFirst();
            }
            deque.addLast(i); // 将当前索引加入队列

            // 当前窗口的最大高度为队列头部的索引对应的高度
            if (!deque.isEmpty()) {
                results[i - limit + 1] = heights[deque.peek()];
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int[] heights = {14, 2, 27, -5, 28, 13, 39};
        int limit = 3;
        SolutionLCR183 sol = new SolutionLCR183();
        System.out.println(Arrays.toString(sol.maxAltitude(heights, limit)));
    }
}