package com.leetcode2.org.动态规划;

public class Solution486 {

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        if (n % 2 == 0) return true; // 如果数组长度为偶数，玩家1总是可以获胜
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                    System.out.println(dp[i][j]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

    public static void main(String[] args) {
        Solution486 solution486 = new Solution486();
        // 示例 1
        int[] nums1 = {3606449, 6, 5, 9, 452429, 7, 9580316, 9857582, 8514433, 9, 6, 6614512, 753594, 5474165, 4, 2697293, 8, 7, 1};
        System.out.println(solution486.predictTheWinner(nums1)); // 输出: false
    }
}
