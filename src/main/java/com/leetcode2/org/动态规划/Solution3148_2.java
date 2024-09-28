package com.leetcode2.org.动态规划;

import java.util.Arrays;
import java.util.List;

public class Solution3148_2 {
    int maxScore(List<List<Integer>> grid) {




        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dp = new int[m + 2][n + 2];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int num = grid.get(i - 1).get(j - 1);
                dp[i][j] = Math.min(num, Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int num = grid.get(i - 1).get(j - 1);
                result = Math.max(result, num - Math.min(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        return result;
    }

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
        Solution3148_2 solution2 = new Solution3148_2();
        System.out.println(solution2.maxScore(gridList));

    }
}
