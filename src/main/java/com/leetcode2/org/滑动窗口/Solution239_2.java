package com.leetcode2.org.滑动窗口;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution239_2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 检查输入数组是否为空、长度为零或 k 小于等于零
        if (nums == null || nums.length == 0 || k <= 0) {
            // 如果条件满足，返回一个空数组
            return new int[0];
        }

        int n = nums.length; // 获取输入数组的长度
        int[] result = new int[n - k + 1]; // 结果数组，用于存放每个滑动窗口的最大值
        Deque<Integer> deque = new ArrayDeque<>(); // 双端队列，用于存放当前窗口内的元素索引

        // 遍历输入数组
        for (int i = 0; i < n; i++) {
            // 移除当前窗口外的元素索引
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst(); // 如果队列头部元素已经超出窗口范围，则移除它
            }

            // 移除所有比当前元素小的元素索引
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast(); // 因为当前元素更大，移除小于当前元素的索引
            }

            // 将当前元素的索引添加到双端队列的末尾
            deque.offerLast(i);

            // 当滑动窗口有效时，添加当前窗口的最大值到结果数组
            if (i >= k - 1) {
                Integer firstIndex = deque.peekFirst(); // 获取当前窗口的最大值的索引
                if (firstIndex != null) {
                    result[i - k + 1] = nums[firstIndex]; // 将最大值放入结果数组
                } else {
                    // 这种情况在正常情况下不会发生，但为了安全起见，我们处理一下
                    result[i - k + 1] = Integer.MIN_VALUE; // 或其他适当的值
                }
            }
        }

        return result; // 返回结果数组
    }




    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        Solution239_2 solution239 = new Solution239_2();
        int[] result = solution239.maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(result));
    }
}