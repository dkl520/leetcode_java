package com.leetcode2.org.动态规划;

public class Solution221 {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int result=0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    int aroundNum = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    aroundNum = Math.min(aroundNum, dp[i - 1][j - 1]);
                    dp[i][j] = (aroundNum + 1);
                    result = Math.max(result, dp[i][j] *dp[i][j]);
                }
            }
        }
        return result;
    }


}
