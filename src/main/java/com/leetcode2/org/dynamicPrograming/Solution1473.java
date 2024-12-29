package com.leetcode2.org.dynamicPrograming;

import java.util.Arrays;

public class Solution1473 {

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {

        int[][][] dp = new int[m + 1][n + 1][target + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for (int j = 0; j <= n; j++) {
            Arrays.fill(dp[0][j], 0);
        }


        outer:
        for (int i = 1; i <= m; i++) {

            for (int j = 0; j < n; j++) {


                if (houses[i - 1] != 0) {

                    for (int k = 1; k <= target && k <= i; k++) {
                        if (houses[i - 1] - 1 != j) {
                            if (dp[i - 1][j][k - 1] != Integer.MAX_VALUE) {
                                dp[i][houses[i - 1] - 1][k] = Math.min(dp[i][houses[i - 1] - 1][k], dp[i - 1][j][k - 1]);
                            }
                        } else {
                            if (dp[i - 1][j][k] != Integer.MAX_VALUE) {
                                dp[i][houses[i - 1] - 1][k] = Math.min(dp[i][houses[i - 1] - 1][k], dp[i - 1][j][k]);
                            }
                        }

                    }

                    continue;
                }


                int curCost = cost[i - 1][j];

                for (int k = 1; k <= target && k <= i; k++) {

                    for (int l = 0; l < n; l++) {

                        if (l != j) {
                            if (dp[i - 1][l][k - 1] != Integer.MAX_VALUE) {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][l][k - 1] + curCost);
                            }
                        } else {
                            if (dp[i - 1][j][k] != Integer.MAX_VALUE) {

                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k] + curCost);
                            }
                        }
//                        System.out.println(dp[i][j][k]);

                    }


                }
            }

        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j <= n; j++) {
            result = Math.min(result, dp[m][j][target]);
        }

        return result == Integer.MAX_VALUE ? -1 : result;

    }

    public static void main(String[] args) {
//        int[] houses = {0, 0, 0, 3}; // 房屋的颜色状态，0表示未涂色，3表示已经涂成第3种颜色
//        int[][] cost = {
//                {2, 2, 5}, // 第1栋房子涂成每种颜色的花费
//                {1, 5, 5}, // 第2栋房子涂成每种颜色的花费
//                {5, 1, 2}, // 第3栋房子涂成每种颜色的花费
//                {5, 2, 5}  // 第4栋房子涂成每种颜色的花费
//        };
//        int m = 4; // 房子的数量
//        int n = 3; // 可用颜色的数量
//        int target = 3; // 目标街区的数量

        int[] houses = {0, 0, 0, 1}; // 房屋的颜色状态，0 表示未涂色，1 表示已经涂成第 1 种颜色
        int[][] cost = {
                {1, 5}, // 第 1 栋房子涂成每种颜色的花费
                {4, 1}, // 第 2 栋房子涂成每种颜色的花费
                {1, 3}, // 第 3 栋房子涂成每种颜色的花费
                {4, 4}  // 第 4 栋房子涂成每种颜色的花费
        };
        int m = 4; // 房子的数量
        int n = 2; // 可用颜色的数量
        int target = 4; // 目标街区的数量


        Solution1473 solution1473 = new Solution1473();
        System.out.println(solution1473.minCost(houses, cost, m, n, target));
    }

}
