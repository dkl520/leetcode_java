package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Soluition1696 {
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    max = Math.max(dp[i - j], max);
                }
            }
            dp[i] = nums[i] + max;
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, -5, -20, 4, -1, 3, -6, -3};
        int k = 2;
        Soluition1696 solution = new Soluition1696();
        System.out.println(solution.maxResult(nums, k));

    }
}
