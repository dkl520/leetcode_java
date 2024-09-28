package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution473_3 {

    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;
        if (n < 4) return false;
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;
        int sideLength = sum / 4;
        // dp[i][j][k][l] 表示前 i 根火柴是否可以构成边长分别为 j, k, l 和 sideLength-j-k-l 的正方形
        boolean[][][][] dp = new boolean[n + 1][sideLength + 1][sideLength + 1][sideLength + 1];
        dp[0][0][0][0] = true;

        for (int i = 1; i <= n; i++) {
            int length = matchsticks[i - 1];
            for (int j = 0; j <= sideLength; j++) {
                for (int k = 0; k <= sideLength; k++) {
                    for (int l = 0; l <= sideLength; l++) {
                        if (dp[i - 1][j][k][l]) {
                            // 当前火柴不放入任何边
                            dp[i][j][k][l] = true;
                        }
                        if (j >= length && dp[i - 1][j - length][k][l]) {
                            // 当前火柴放入第一条边
                            dp[i][j][k][l] = true;
                        }
                        if (k >= length && dp[i - 1][j][k - length][l]) {
                            // 当前火柴放入第二条边
                            dp[i][j][k][l] = true;
                        }
                        if (l >= length && dp[i - 1][j][k][l - length]) {
                            // 当前火柴放入第三条边
                            dp[i][j][k][l] = true;
                        }
                        // 当前火柴放入第四条边的情况自动被包含
                    }
                }
            }
        }

        return dp[n][sideLength][sideLength][sideLength];
    }
}
