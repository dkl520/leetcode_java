package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution123 {
    public int maxProfit(int[] prices) {
        int k = 2;
        int[][] sell = new int[prices.length][k];
        int[][] hold = new int[prices.length][k];
        sell[0] = new int[]{0, 0};
        hold[0] = new int[]{-prices[0], -prices[0]};
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
//        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = {7,6,4,3,1};
//        int[] prices = {1,2,3,4,5};

        Solution123 solution123 = new Solution123();
        System.out.println(solution123.maxProfit(prices));

    }
}
