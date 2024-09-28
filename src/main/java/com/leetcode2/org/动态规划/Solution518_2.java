package com.leetcode2.org.动态规划;

public class Solution518_2 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;  // 初始化，当金额为0时，有1种组合方式（不选取任何硬币）

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                int newCoin = i - coin;
                dp[i] += dp[newCoin];
            }
        }

         return dp[amount];
    }

    public static void main(String[] args) {

        int amount = 5;
        int[] coins = {2, 1, 5};
        System.out.println(new Solution518_2().change(amount, coins));
    }

}
