package com.leetcode2.org.algorithm;

import java.util.Arrays;

class Solution {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).reduce(0, (a, b) -> a + b);
        if (sum % 2 == 1) {
            return false;
        }
        int half = sum / 2;
        int index = Arrays.binarySearch(nums, half);
        if (index > 0) {
            return true;
        }
        int[][] dp = new int[nums.length + 1][half + 1];
        for (int i = 1; i <= nums.length; i++) {
            int target = nums[i - 1];
            for (int j = 0; j <= half; j++) {
                if (j < target) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(target + dp[i - 1][j - target], dp[i - 1][j]);
                }
            }
        }
        int result = dp[nums.length][half];
        return result == half;
    }
}