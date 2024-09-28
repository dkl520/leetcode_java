//package com.leetcode2.org.动态规划;
//
//import java.util.List;
//
//public class Solution3148_3 {
//
//    int maxScore(List<List<Integer>> grid) {
//
//        int[][] gridNew = grid.stream()
//                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
//                .toArray(int[][]::new);
//
//        int m = gridNew.length;
//        int n = gridNew[0].length;
//        int[][] dp = new int[m][n];
//
//        // 初始化DP表
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                dp[i][j] = Integer.MIN_VALUE;
//            }
//        }
//
//        // 计算最大得分
//        int maxScore = Integer.MIN_VALUE;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i > 0) {
//                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + gridNew[i][j] - gridNew[i - 1][j]);
//                }
//                if (j > 0) {
//                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + gridNew[i][j] - gridNew[i][j - 1]);
//                }
//                if (dp[i][j] == Integer.MIN_VALUE) {  // 起始点，第一次进入该单元格
//                    dp[i][j] = 0;
//                }
//                maxScore = Math.max(maxScore, dp[i][j]);
//            }
//        }
//
//        return maxScore;
//    }
//
//
//}
