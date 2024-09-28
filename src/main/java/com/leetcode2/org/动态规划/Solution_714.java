package com.leetcode2.org.动态规划;

public class Solution_714 {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] purchase = new int[n + 1];
        int[] sell = new int[n + 1];
        int[] hold = new int[n + 1];
        int[] wait = new int[n + 1];
        purchase[0] = -prices[0] - fee;
        sell[0] = 0;
        hold[0] =  -prices[0] - fee;;
        wait[0] = 0;
        for (int i = 1; i < n; i++) {
            int price = prices[i];
            purchase[i] = Math.max(wait[i - 1], sell[i - 1]) - fee - price;
            sell[i] = Math.max(hold[i - 1], purchase[i - 1]) + price;
            hold[i] = Math.max(purchase[i - 1], hold[i - 1]);
            wait[i] = Math.max(wait[i - 1], sell[i - 1]);
        }
        return    Math.max(sell[n-1], wait[n-1]);

    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        Solution_714 solution = new Solution_714();
        System.out.println(solution.maxProfit(prices, fee));

    }
}
