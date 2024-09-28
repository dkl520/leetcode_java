package com.leetcode2.org.动态规划;

public  class Knapsack2 {

    public static void main(String[] args) {
        int W = 10;
        int n = 4;
        int[] weights = {5, 4, 6, 3};
        int[] values = {10, 40, 30, 50};

        int maxValue = knapsack(W, weights, values, n);
        System.out.println("最大价值: " + maxValue);
    }

    public static int knapsack(int W, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {  //物品
            for (int j = 0; j <= W; j++) { //容量
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }
}

