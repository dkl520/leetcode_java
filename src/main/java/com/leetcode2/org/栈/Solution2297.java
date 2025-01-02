package com.leetcode2.org.栈;

import java.util.Arrays;
import java.util.Stack;

public class Solution2297 {
    /**
     * 计算将数组 nums 转换为非递减数组的最小成本
     *
     * @param nums  数组，表示需要转换的数字
     * @param costs 数组，表示将 nums 中的每个元素转换为非递减所需的成本
     * @return 转换为非递减数组的最小成本
     */
    public long minCost(int[] nums, int[] costs) {
        int n = nums.length;
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE); // 初始化 dp 数组，初始值为 Long.MAX_VALUE
        dp[0] = 0; // 第一个元素的成本为 0

        Stack<Integer> maxStack = new Stack<>(), minStack = new Stack<>();
        for (int j = 0; j < n; j++) {
            // 处理 minStack，确保栈中的元素保持递增顺序
            while (!minStack.isEmpty() && nums[minStack.peek()] <= nums[j]) {
                int i = minStack.pop();
                dp[j] = Math.min(dp[j], dp[i] + costs[j]);
            }
            minStack.push(j);
            // 处理 maxStack，确保栈中的元素保持递减顺序
            while (!maxStack.isEmpty() && nums[maxStack.peek()] > nums[j]) {
                dp[j] = Math.min(dp[j], dp[maxStack.pop()] + costs[j]);
            }
            maxStack.push(j);
        }
        return dp[n - 1]; // 返回将数组转换为非递减数组的最小成本
    }
}