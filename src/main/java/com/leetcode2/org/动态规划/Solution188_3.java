package com.leetcode2.org.动态规划;

public class Solution188_3 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        // 初始化两个数组来保存当前的最大利润
        int[] daysK_0 = new int[k + 1];
//        daysK_0[j]:
//        表示在第 i 天进行了 j 次交易且当前不持有股票时的最大利润。
//        初始时，daysK_0[j] 被初始化为 0，表示在没有任何交易的情况下利润为 0
        int[] daysK_1 = new int[k + 1];
//        daysK_1[j]:
//        表示在第 i 天进行了 j 次交易且当前持有股票时的最大利润。
//        初始时，daysK_1[j] 被初始化为 -prices[0]，表示在第 0 天买入股票后（即进行第一次交易）的最大利润，
//        因为买入股票后利润为负数（花费了股票的价格）。
        // 初始化daysK_1数组，表示在第0天持有股票的情况
        for (int j = 0; j <= k; j++) {
            daysK_1[j] = -prices[0];
        }
        // 动态规划求解最大利润
        for (int i = 1; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                daysK_0[j] = Math.max(daysK_0[j], daysK_1[j] + prices[i]);
                daysK_1[j] = Math.max(daysK_1[j], daysK_0[j - 1] - prices[i]);
            }
        }
        return daysK_0[k];
    }
}
