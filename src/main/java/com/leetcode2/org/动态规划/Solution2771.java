package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution2771 {

    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 1);
        }
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i] >= nums1[i - 1]) {
                dp[i][0] = Math.max(dp[i - 1][0] + 1, dp[i][0]);
            }
            if (nums1[i] >= nums2[i - 1]) {
                dp[i][0] = Math.max(dp[i - 1][1] + 1, dp[i][0]);
            }
            if (nums2[i] >= nums1[i - 1]) {
                dp[i][1] = Math.max(dp[i - 1][0] + 1, dp[i][1]);
            }
            if (nums2[i] >= nums2[i - 1]) {
                dp[i][1] = Math.max(dp[i - 1][1] + 1, dp[i][1]);
            }
            max = Math.max(max, dp[i][1]);
            max = Math.max(max, dp[i][0]);
        }
        return max;/**/
    }

}
