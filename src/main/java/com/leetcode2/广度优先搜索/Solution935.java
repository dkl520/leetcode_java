package com.leetcode2.广度优先搜索;

public class Solution935 {


    int[][] DIRS = new int[][]{
            {2, 1},
            {2, -1},
            {-2, 1},
            {-2, -1},
            {1, 2},
            {1, -2},
            {-1, 2},
            {-1, -2}
    };

    public int knightDialer(int n) {

        int[][] board = new int[4][3];
        int[][][] dp = new int[n + 1][4][3];
        int MOD = 1_000_000_007;
        for (int x = 0; x <= n; x++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 3 && j == 0 || i == 3 && j == 2) {
                        dp[x][i][j] = 0;
                        continue;
                    }
                    if (x == 1 || x == 0) {
                        dp[x][i][j] = 1;
                    } else {

                        for (int[] dir : DIRS) {
                            int newX = dir[0] + i;
                            int newY = dir[1] + j;
                            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                                if ((newX == 3 && newY == 0) || (newX == 3 && newY == 2)) {
                                    continue;
                                }
                                dp[x][i][j] += dp[x - 1][newX][newY];
                                dp[x][i][j] %= MOD;
                            }
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                result += dp[n][i][j];
                result %= MOD;
            }
        }
        return result;


    }

    public static void main(String[] args) {
        Solution935 solution935 = new Solution935();
        System.out.println(
                solution935.knightDialer(2)
        );


    }
}