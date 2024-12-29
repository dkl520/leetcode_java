package com.leetcode2.org.动态规划;

public class Solution3393 {
    public int countPathsWithXorValue(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 20;
        double MOD = 1e9 + 7;
        int[][][] dp = new int[m][n][max];
        int original = grid[0][0];
        dp[0][0][original] = 1;
        for (int i = 1; i < m; i++) {
            original ^= grid[i][0];
            dp[i][0][original]++;
        }
        original = grid[0][0];
        for (int i = 1; i < n; i++) {
            original ^= grid[0][i];
            dp[0][i][original]++;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                for (int l = 0; l < max; l++) {
                    if (dp[i - 1][j][l] == 0) continue;
                    int newVal = l ^ grid[i][j];
                    dp[i][j][newVal] += dp[i - 1][j][l];
                    dp[i][j][newVal] %= MOD;
                }
                for (int o = 0; o < max; o++) {
                    if (dp[i][j - 1][o] == 0) continue;
                    int newVal = o ^ grid[i][j];
                    dp[i][j][newVal] += dp[i][j - 1][o];
                    dp[i][j][newVal] %= MOD;
                }
            }
        }
        return dp[m - 1][n - 1][k];


    }

    public static void main(String[] args) {

        int[][] grid = {
                {2, 1, 5},
                {7, 10, 0},
                {12, 6, 4}
        };
        int k = 11;
        Solution3393 solution = new Solution3393();
        int result = solution.countPathsWithXorValue(grid, k);
        System.out.println(result);
    }


}
