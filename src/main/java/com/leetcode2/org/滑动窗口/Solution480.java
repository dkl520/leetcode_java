package com.leetcode2.org.滑动窗口;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution480 {
    static class SlidingWindowMedian {
        PriorityQueue<Integer> maxHeap; // 大根堆，存储较小的一半元素
        PriorityQueue<Integer> minHeap; // 小根堆，存储较大的一半元素
        public SlidingWindowMedian() {
            // 初始化堆
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }
        // 向滑动窗口中添加元素
        void addNum(int num) {
            // 如果大根堆为空或者新元素小于等于大根堆的堆顶元素，加入大根堆
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else { // 否则加入小根堆
                minHeap.offer(num);
            }
            // 平衡两个堆的大小，保证大根堆的大小不大于小根堆
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        // 从滑动窗口中移除元素
        void removeNum(int num) {
            // 判断要移除的元素属于哪个堆，并移除
            if (num <= maxHeap.peek()) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }
            // 平衡两个堆的大小
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        // 获取当前滑动窗口的中位数
        double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                // 如果堆的大小相等，取两个堆顶元素的平均值
                return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
            } else {
                // 否则，返回大根堆的堆顶元素作为中位数
                return (double) maxHeap.peek();
            }
        }
    }
    // 主方法，用于测试
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();

        for (int i = 0; i < n; i++) {
            slidingWindowMedian.addNum(nums[i]); // 向滑动窗口中添加元素
            if (i >= k) {
                slidingWindowMedian.removeNum(nums[i - k]); // 移除窗口左侧元素
            }
            if (i >= k - 1) {
                result[i - k + 1] = slidingWindowMedian.findMedian(); // 获取当前窗口的中位数
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        Solution480 solution480 = new Solution480();
        double[] result = solution480.medianSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result)); // 输出结果: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }

}
