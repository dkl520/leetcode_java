package com.leetcode2.org.动态规划;

import java.util.Arrays;
import java.util.Comparator;

public class Knapsack {
    static class Goods {
        int weight;
        int value;

        public Goods(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    int getMaxProfit(int[] weights, int[] values, int k) {

        int n = weights.length;
        Goods[] goods = new Goods[n];
        for (int i = 0; i < n; i++) {
            goods[i] = new Goods(weights[i], values[i]);
        }
//        Arrays.sort(goods, (a, b) -> a.weight - b.weight);
        int[][] dp = new int[k + 1][n + 1];
        Arrays.fill(dp[0], 0);
//        for (int i = 1; i <= k; i++) {
//            for (int j = 0; j < n; j++) {
//                if (j == 0) {
//                    if (i < goods[j].weight) {
//                        dp[i][j] = dp[i - 1][j];
//                    } else {
//                        dp[i][j] = goods[j].value;
//                    }
//                } else {
//                    if (i < goods[j].weight) {
//                        dp[i][j] = dp[i][j - 1];
//                    } else {
//                        dp[i][j] = Math.max(dp[i - goods[j].weight][j - 1] + goods[j].value, dp[i][j - 1]);
//                    }
//                }
//            }
//        }
        for (int i = 0; i <= k; i++) {  //容量
            for (int j = 1; j <= n; j++) {  // 物品 顺序
                if (i < goods[j - 1].weight) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - goods[j - 1].weight][j - 1] + goods[j - 1].value, dp[i][j - 1]);
                }
            }
        }


        return dp[k][n];
    }

    public static void main(String[] args) {
        int W = 10; // 背包容量
        int n = 4;  // 物品数量
        int[] weights = {5, 4, 6, 3}; // 物品重量数组
        int[] values = {10, 40, 30, 50}; // 物品价值数组
        Knapsack knapsack = new Knapsack();
        System.out.println(knapsack.getMaxProfit(weights, values, W));

    }
}
