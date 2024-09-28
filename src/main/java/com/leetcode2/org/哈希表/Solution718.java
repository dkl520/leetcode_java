package com.leetcode2.org.哈希表;

import java.util.Arrays;

public class Solution718 {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int maxLength = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3, 2, 1};
//        int[] nums2 = {3, 2, 1, 4, 7};
        int[] nums1 = {0, 1, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 0, 1};
//        int[] nums1 = {1,1,0,1,1,0,0,0,0,0};
//        int[] nums2 = {1,0,1,0,0,0,0,0,1,1};

        Solution718 solution718 = new Solution718();
        System.out.println(solution718.findLength(nums1, nums2));
    }
}
