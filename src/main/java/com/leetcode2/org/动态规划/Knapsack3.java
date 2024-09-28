package com.leetcode2.org.动态规划;

public class Knapsack3 {

    public static void main(String[] args) {
        int W = 10;
        int n = 4;
        int[] weights = {5, 4, 6, 3};
        int[] values = {10, 40, 30, 50};
        int maxValue = knapsack(W, weights, values, n);
        System.out.println("最大价值: " + maxValue);
    }
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++) { // 遍历物品
            for (int j = W; j >= weights[i - 1]; j--) { // 遍历背包容量，从后往前
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                System.out.println(dp[j]);
            }
        }
        return dp[W];
    }
}
//，0/1背包问题的动态规划可以通过简化成一维数组来优化空间复杂度。我们只需要维护一个长度为 W + 1 的数组来存储当前的状态。
//这种方法在处理状态转移时需要从后往前遍历，以确保每个物品只被考虑一次。

