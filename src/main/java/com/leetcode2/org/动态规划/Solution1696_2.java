package com.leetcode2.org.动态规划;

import java.util.Deque;
import java.util.LinkedList;

public class Solution1696_2 {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // dp数组用于存储到达每个位置的最大得分
        int[] dp = new int[n];
        // 初始化dp数组的第一个元素，即起点的得分
        dp[0] = nums[0];
        // 双端队列用于维护当前窗口范围内的最大值索引
        Deque<Integer> deque = new LinkedList<>();
        // 将起点索引加入队列
        deque.offer(0);
        // 从第1个元素开始遍历数组
        for (int i = 1; i < n; i++) {
            // 队列头部的索引如果超出范围（即不在当前窗口范围内）则移除
            if (!deque.isEmpty() && deque.peek() < i - k) {
                deque.poll();
            }
            // 当前dp值为nums[i]加上队列头部索引所对应的dp值
            dp[i] = nums[i] + dp[deque.peek()];
            // 从队列尾部开始移除所有小于当前dp值的索引，因为这些索引对应的dp值不会在未来成为最大值
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            // 将当前索引加入队列
            deque.offer(i);
        }
        // 返回到达最后一个位置的最大得分
        return dp[n - 1];
    }

    public static void main(String[] args) {

        int[] nums = {1,-1,-2,4,-7,3};
        int k = 2;
        Solution1696_2 solution = new Solution1696_2();
        System.out.println(solution.maxResult(nums, k));
    }
}
