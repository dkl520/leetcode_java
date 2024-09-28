package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution188 {
    public int maxProfit(int k, int[] prices) {
        int[][] sell = new int[prices.length][k];
        int[][] hold = new int[prices.length][k];
        Arrays.fill(hold[0], -prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                sell[i][j] = Math.max(sell[i - 1][j], hold[i - 1][j] + prices[i]);
                if (j == 0) {
                    hold[i][j] = Math.max(hold[i - 1][j], -prices[i]);
                } else {
                    hold[i][j] = Math.max(hold[i - 1][j], sell[i - 1][j - 1] - prices[i]);
                }
            }
        }
        return sell[prices.length - 1][k - 1] < 0 ? 0 : sell[prices.length - 1][k - 1];
    }

    public static void main(String[] args) {
        int k = 4;
        int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        System.out.println(new Solution188().maxProfit(k, prices));

    }
}