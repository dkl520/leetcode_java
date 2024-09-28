package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution2742 {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int maxTime = Arrays.stream(time).sum(); // 所有时间之和，用于确定dp数组的大小
        int[][] dp = new int[n + 1][maxTime + 1]; // 动态规划数组

        // 初始化dp数组
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2); // 用较大的数填充，表示不可达状态
        }
        dp[0][0] = 0; // 初始状态，刷0堵墙，时间和开销都为0

        // 动态规划转移方程
        for (int i = 1; i <= n; i++) {
            for (int t = 0; t <= maxTime; t++) {
                // 如果不使用付费油漆匠刷第i堵墙
                dp[i][t] = dp[i - 1][t];
                // 如果使用付费油漆匠刷第i堵墙
                if (t >= time[i - 1]) { // 确保当前时间足够刷这堵墙
                    dp[i][t] = Math.min(dp[i][t], dp[i - 1][t - time[i - 1]] + cost[i - 1]);
                }
            }
        }

        // 找到最小开销
        int minCost = Integer.MAX_VALUE;
        for (int t = 0; t <= maxTime; t++) {
            if (dp[n][t] != Integer.MAX_VALUE / 2) {
                minCost = Math.min(minCost, dp[n][t]);
            }
        }

        return minCost; // 返回最小开销
    }

    public static void main(String[] args) {
        Solution2742 solution = new Solution2742();
        int[] cost1 = {1, 2, 3, 2};
        int[] time1 = {1, 2, 3, 2};
        System.out.println(solution.paintWalls(cost1, time1)); // 输出：3

        int[] cost2 = {2, 3, 4, 2};
        int[] time2 = {1, 1, 1, 1};
        System.out.println(solution.paintWalls(cost2, time2)); // 输出：4
    }
}
