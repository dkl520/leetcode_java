package com.leetcode2.org.前缀和;

public class Solution3388 {
    private static final int MAXN = 5000;
    private int[][] prefixLength = new int[MAXN + 1][MAXN + 1];

    public int beautifulSplits(int[] nums) {
        int n = nums.length;
        // Compute prefix lengths for all substrings
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    prefixLength[i][j] = prefixLength[i + 1][j + 1] + 1;
                }
            }
        }
        int count = 0;
        // Iterate to find valid splits
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int len1 = i + 1, len2 = j - i, len3 = n - 1 - j;
                // Check if the left partition is a prefix of the middle partition and if the middle partition is a prefix of the right partition
                if ((len1 <= len2 && prefixLength[0][i + 1] >= len1) ||
                        (len2 <= len3 && prefixLength[i + 1][j + 1] >= len2)) {
                    count++;
                }
            }
        }
        return count;
    }
}
