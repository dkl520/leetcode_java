package com.leetcode2.org.博弈论;

public class Solution877 {
    /**
     * 解决石子游戏问题
     * @param piles 整数数组，表示每堆石子的数量
     * @return 布尔值，表示先手玩家是否能赢
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length; // 石子堆的数量
        int[][] dp = new int[n][n]; // 创建一个二维数组dp，用于存储每个子问题的最优解

        // 初始化dp数组，当只有一个石子堆时，当前玩家可以直接取走所有石子，所以dp[i][i] = piles[i]
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // 从长度为2的子数组开始，逐步扩展到整个数组
        for (int length = 2; length <= n; length++) {
            // 对于每个长度length，遍历所有可能的起始位置i
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1; // 计算当前子数组的结束位置
                // 当前玩家可以选择取走第i堆的所有石子或第j堆的所有石子
                // 取走后，下一个玩家面对的子问题分别是dp[i+1][j]和dp[i][j-1]
                // 当前玩家应该选择能让自己获得最大优势的策略
                // piles[i] - dp[i+1][j]表示取走第i堆石子后，当前玩家与下一个玩家的石子数之差
                // piles[j] - dp[i][j-1]表示取走第j堆石子后，当前玩家与下一个玩家的石子数之差
                // 当前玩家应该选择差值更大的那个策略
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);

            }
        }

        // 如果dp[0][n-1] > 0，表示先手玩家（Alice）通过最优策略可以确保自己赢得的石子数比对手多，因此她可以赢
        return dp[0][n-1] > 0;
    }

    public static void main(String[] args) {
        int [] piles = {3,7,2,3};
        System.out.println(new Solution877().stoneGame(piles));

    }
}