package com.leetcode2.org.树状数组;

public class Solution2907 {

    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int cur = prices[i];
            for (int j = 0; j < i; j++) {
                int pre = prices[j];
                if (pre >= cur) continue;
                for (int k = i + 1; k < n; k++) {
                    if (prices[k] <= cur) continue;
                    max = Math.max(max, profits[i] + profits[k] + profits[j]);
                }
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }

    public static void main(String[] args) {

        int[] prices = new int[]{10, 2, 3, 4};
        int[] profits = new int[]{100, 2, 7, 10};
        Solution2907 solution2907 = new Solution2907();
        System.out.println(solution2907.maxProfit(prices, profits));
    }
}
