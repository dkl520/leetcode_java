package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution2312_2 {

    /**
     * 木材销售问题：给定木材的长m、宽n和一系列不同尺寸木材的价格表，
     * 计算通过切割（水平和垂直）木材所能获得的最大收益。
     *
     * @param m      木材的最大长度
     * @param n      木材的最大宽度
     * @param prices 价格表，每个元素为[height, width, cost]，表示尺寸为height x width的木材的售价
     * @return 通过切割木材所能获得的最大收益
     */
    public long sellingWood(int m, int n, int[][] prices) {
        // 创建一个二维动态规划数组dp，dp[i][j]表示尺寸为i x j的木材的最大收益
        long[][] dp = new long[m + 1][n + 1];

        // 初始化dp数组，将价格表中的值填入dp（注意可能存在多个价格对应同一尺寸，取最大值）
        for (int[] price : prices) {
            int height = price[0];
            int width = price[1];
            long cost = price[2];
            dp[height][width] = Math.max(dp[height][width], cost);
        }
        System.out.println(dp);
        // 填充dp数组，通过尝试所有可能的切割方式（水平和垂直）来更新最大收益
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 尝试垂直切割
                // 遍历所有可能的切割点k（从1到i/2），将木材垂直切割为两部分，并计算收益
                for (int k = 1; k <= i / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                    System.out.println(dp[i][j]);
                }
                // 尝试水平切割
                // 遍历所有可能的切割点k（从1到j/2），将木材水平切割为两部分，并计算收益
                for (int k = 1; k <= j / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);

                }
                // 注意：这里我们没有考虑不切割的情况，因为dp[i][j]在初始化时已经从价格表中取了最大值
                // 如果价格表中没有对应尺寸的价格，dp[i][j]将保持为0，这表示不切割也没有收益
            }
        }

        // 返回尺寸为m x n的木材的最大收益
        return dp[m][n];
    }

    public static void main(String[] args) {
        int m1 = 3, n1 = 5;
        int[][] prices1 = {{1, 4, 2}, {2, 2, 7}, {2, 1, 3}, {1, 2, 2}};
        System.out.println(new Solution2312_2().sellingWood(m1, n1, prices1)); // 输出：19

        int m2 = 4, n2 = 6;
        int[][] prices2 = {{3, 2, 10}, {1, 4, 2}, {4, 1, 3}};
        System.out.println(new Solution2312_2().sellingWood(m2, n2, prices2)); // 输出：32
    }
}