package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution2742_5 {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {  //物品
            for (int j = 0; j <= n; j++) {  //容量
                int v;
                if (j > time[i - 1] + 1) {
                    v = dp[i - 1][j - (time[i - 1] + 1)];
                } else {
                    v = 0;
                }
                if (v == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j], v + cost[i - 1]);
            }
        }
        return dp[n][n];
    }
    public static void main(String[] args) {
        Solution2742_5 solution = new Solution2742_5();
        int[] cost1 = {1, 2, 3, 2};
        int[] time1 = {1, 2, 3, 2};
        System.out.println(solution.paintWalls(cost1, time1)); // 输出：3

        int[] cost2 = {2, 3, 4, 2};
        int[] time2 = {1, 1, 1, 1};
        System.out.println(solution.paintWalls(cost2, time2)); // 输出：4
    }
}
