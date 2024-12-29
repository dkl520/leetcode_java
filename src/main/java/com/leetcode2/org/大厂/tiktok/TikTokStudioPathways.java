package com.leetcode2.org.大厂.tiktok;

public class TikTokStudioPathways {
    public static int findPaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) {
                dp[i][0] = 1;
            } else {
                break;
            }

        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0) {
                break;
            }
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) continue;
                dp[i][j] = dp[i -1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
//        int[][] grid3 = {
//                {1, 1, 1, 1},
//                {1, 1, 1, 1},
//                {1, 1, 1, 1},
//                {1, 1, 1, 1}
//        };
//        System.out.println(findPaths(grid3)); // 输出 20
        // 测试 5: 5x5 网格，部分障碍
        int[][] grid5 = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        System.out.println(findPaths(grid5)); // 输出 10


        int[][] grid4 = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}
        };
        System.out.println(findPaths(grid4)); // 输出 4
    }
}
