package com.leetcode2.org.dynamicPrograming;

//1444. 切披萨的方案数
public class Solution1444 {
    public int ways(String[] pizza, int k) {
        int MOD = 1_000_000_007;
        int m = pizza.length;
        int n = pizza[0].length();
        // preSum[i][j] 表示从 (i,j) 到右下角的苹果总数
        int[][] preSum = new int[m + 1][n + 1];
        // dp[k][i][j] 表示从(i,j)开始切k次的方案数
        int[][][] dp = new int[k][m][n];
        // 1. 计算前缀和
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 计算(i,j)位置到右下角的苹果数
                preSum[i][j] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i + 1][j + 1] +
                        (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
        // 2. 动态规划
        for (int remain = 0; remain < k; remain++) {  // 剩余切割次数
            for (int i = 0; i < m; i++) {            // 起始行
                for (int j = 0; j < n; j++) {        // 起始列
                    // 如果没有苹果了，跳过
                    if (preSum[i][j] == 0) continue;
                    // 如果是最后一刀，且当前区域有苹果，就是一种方案
                    if (remain == 0) {
                        dp[remain][i][j] = 1;
                        continue;
                    }
                    // 尝试水平切割
                    for (int nextRow = i + 1; nextRow < m; nextRow++) {
                        // 上半部分必须有苹果
                        int upApples = preSum[i][j] - preSum[nextRow][j];
                        if (upApples > 0 && preSum[nextRow][j] > 0) {
                            dp[remain][i][j] = (dp[remain][i][j] + dp[remain - 1][nextRow][j]) % MOD;
                        }
                    }
                    // 尝试垂直切割
                    for (int nextCol = j + 1; nextCol < n; nextCol++) {
                        // 左半部分必须有苹果
                        int leftApples = preSum[i][j] - preSum[i][nextCol];
                        if (leftApples > 0 && preSum[i][nextCol] > 0) {
                            dp[remain][i][j] = (dp[remain][i][j] + dp[remain - 1][i][nextCol]) % MOD;
                        }
                    }
                }
            }
        }
        return dp[k - 1][0][0];
    }
}