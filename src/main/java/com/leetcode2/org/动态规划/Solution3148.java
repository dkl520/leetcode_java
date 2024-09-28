package com.leetcode2.org.动态规划;

import java.util.*;

public class Solution3148 {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dp = new int[m + 1][n + 1];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, Integer.MIN_VALUE));
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int num = grid.get(i - 1).get(j - 1);
                for (int k = 1; k < i; k++) {
                    int topNum = grid.get(k - 1).get(j - 1);
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[k][j], 0) + (num - topNum));
                }
                for (int z = 1; z < j; z++) {
                    int leftNum = grid.get(i - 1).get(z - 1);
                    dp[i][j] = Math.max(dp[i][j], Math.max(0, dp[i][z]) + (num - leftNum));
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
    //        int[][][] minList = new int[m + 1][n + 1][3];
//        Arrays.stream(minList).forEach(row -> Arrays.stream(row).forEach(col -> Arrays.fill(col, Integer.MAX_VALUE)));
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                int num = grid.get(i - 1).get(j - 1);
//                minList[i][j][0] = Math.min(num, minList[i - 1][j][0]);
//                minList[i][j][1] = Math.min(num, minList[i][j - 1][1]);
//                minList[i][j][2] = Math.min(minList[i][j][0], minList[i][j][1]);
//            }
//        }
//        int maxNum = Integer.MIN_VALUE;
//
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                int num = grid.get(i - 1).get(j - 1);
////                    dp[i][j] = Math.min(num, dp[i - 1][j] + 1);
//                maxNum = Math.max(maxNum, num - minList[i][j][2]);
////                System.out.println(maxNum);
//            }
//        }
    public static void main(String[] args) {
//        int[][] grid = {
//                {9, 5, 7, 3},
//                {8, 9, 6, 1},
//                {6, 7, 14, 3},
//                {2, 5, 3, 1}
//        };
        int[][] grid = {
                {4, 3, 2},
                {3, 2, 1}
        };

        List<List<Integer>> gridList = Arrays.stream(grid).map(row -> Arrays.stream(row).boxed().toList()).toList();
        Solution3148 solution = new Solution3148();
        System.out.println(solution.maxScore(gridList));

    }
}
