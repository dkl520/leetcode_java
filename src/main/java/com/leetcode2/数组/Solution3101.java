package com.leetcode2.数组;

import java.util.Arrays;

public class Solution3101 {
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            int cur = nums[i];
            int next = nums[i + 1];
            if (cur == next) {
                dp[i][i + 1] = 0;
            } else {
                dp[i][i + 1] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i][j - 1] & dp[j - 1][j];
                if (dp[i][j] == 0) break;
            }
        }

        long ans = Arrays.stream(dp)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == 1)
                .count();
        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 1};
        long ans = new Solution3101().countAlternatingSubarrays(nums);
        System.out.println(ans);
    }
}
