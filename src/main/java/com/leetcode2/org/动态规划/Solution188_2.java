package com.leetcode2.org.动态规划;

public class Solution188_2 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        // 当k大于等于天数的一半时，等同于可以无限交易
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        int[][][] dp = new int[n][k + 1][2];
        // 初始化
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = -prices[0];
        }
        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                if (j > 0) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }

        return dp[n - 1][k][0];
    }
}


//定义状态：
//
//dp[i][j][0] 表示在第 i 天进行了 j 次交易且当前不持有股票的最大利润。
//dp[i][j][1] 表示在第 i 天进行了 j 次交易且当前持有股票的最大利润。
//初始条件：
//
//dp[0][0][0] = 0：第 0 天，没有交易，不持有股票，利润为 0。
//dp[0][j][0] = 0：第 0 天，不管进行了多少次交易，只要不持有股票，利润都为 0。
//dp[0][j][1] = -prices[0]：第 0 天，不管进行了多少次交易，只要持有股票，利润都为 -prices[0]（即买入股票）。
//状态转移方程：
//
//dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i])：第 i 天不持有股票的最大利润，要么就是前一天不持有股票，
//要么就是前一天持有股票且今天卖出。
//dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i])：第 i 天持有股票的最大利润，要么就是前一天持有股票，
//要么就是前一天不持有股票且今天买入。
