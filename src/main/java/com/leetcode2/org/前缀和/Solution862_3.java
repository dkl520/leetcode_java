package com.leetcode2.org.前缀和;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution862_3 {
    // 方法：查找和至少为 k 的最短子数组
    public int shortestSubarray(int[] nums, int k) {
        // 获取输入数组的长度
        int n = nums.length;
        // 创建前缀和数组，多一个元素用于初始化
        long[] preSumArr = new long[n + 1];
        // 计算前缀和数组
        for (int i = 0; i < n; i++) {
            preSumArr[i + 1] = preSumArr[i] + nums[i];
        }
        // 初始化结果为 n+1（不可能的长度）
        int res = n + 1;

        // 创建一个双端队列，用于存储前缀和的索引
        Deque<Integer> queue = new ArrayDeque<Integer>();
        // 遍历前缀和数组
        for (int i = 0; i <= n; i++) {
            // 获取当前的前缀和
            long curSum = preSumArr[i];
            // 如果子数组的和大于等于 k，则从队列前端移除索引
            while (!queue.isEmpty() && curSum - preSumArr[queue.peekFirst()] >= k) {
                // 更新最短长度的结果
                res = Math.min(res, i - queue.pollFirst());
            }
            // 为保持队列的单调性，从队列后端移除不需要的索引在这段代码中，
            // 如果当前的前缀和 curSum 小于或等于队列尾部索引对应的前缀和 preSumArr[queue.peekLast()]，那么就会移除队列尾部的索引。
            // 这是为了确保队列中的前缀和值保持单调递增（即从队列头到尾部的前缀和值逐渐增大）。
            //这样做的目的是保证，当我们在队列头部进行计算时，能够找到最早满足条件的子数组，同时保持最短的长度。

            while (!queue.isEmpty() && preSumArr[queue.peekLast()] >= curSum) {
                queue.pollLast();
            }
            // 将当前索引加入队列
            queue.offerLast(i);
            // 打印当前队列的状态（用于调试）
        }
        // 返回结果，如果没有找到符合条件的子数组则返回 -1
        return res < n + 1 ? res : -1;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        // 测试的 k 值
        int k = 167;
        // 测试的输入数组
        int[] nums = new int[]{84, -37, 32, 40, 95};
        // 创建解决方案实例并调用方法
        System.out.println(new Solution862_3().shortestSubarray(nums, k));
    }
}
