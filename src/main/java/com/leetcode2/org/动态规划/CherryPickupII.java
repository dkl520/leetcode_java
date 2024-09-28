package com.leetcode2.org.动态规划;

public class CherryPickupII {
    // 方法用于计算两个机器人从grid的左上角和右上角开始，能够收集到的最大樱桃数量
    public int cherryPickup(int[][] grid) {
        int rows = grid.length;       // 网格的行数
        int cols = grid[0].length;    // 网格的列数
        int[][][] dp = new int[rows][cols][cols]; // 动态规划数组，dp[i][j][k]表示第一个机器人在(i,j)，第二个机器人在(i,k)时能收集到的最大樱桃数
        // 初始化DP数组为-1，表示未访问状态
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        // 初始状态，两个机器人分别在(0,0)和(0,cols-1)
        dp[0][0][cols - 1] = grid[0][0] + grid[0][cols - 1]; // 初始时收集到的樱桃数是两个起始位置的樱桃数之和
        // 遍历每一行
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    // 跳过未访问过的状态
                    if (dp[i - 1][j][k] == -1) continue;
                    // 遍历两个机器人所有可能的移动方向
                    for (int dj = -1; dj <= 1; dj++) {
                        for (int dk = -1; dk <= 1; dk++) {
                            int nj = j + dj; // 第一个机器人新位置
                            int nk = k + dk; // 第二个机器人新位置
                            // 检查新位置是否在网格内
                            if (nj >= 0 && nj < cols && nk >= 0 && nk < cols) {
                                int cherries = grid[i][nj]; // 当前行第一个机器人所在位置的樱桃数
                                if (nj != nk) { // 如果两个机器人不在同一列，则加上第二个机器人所在位置的樱桃数
                                    cherries += grid[i][nk];
                                }

                                // 更新dp[i][nj][nk]为当前最大值
                                dp[i][nj][nk] = Math.max(dp[i][nj][nk], dp[i - 1][j][k] + cherries);
                            }
                        }
                    }
                }
            }
        }
        // 在最后一行中找到最大的樱桃数
        int maxCherries = 0;
        for (int j = 0; j < cols; j++) {
            for (int k = 0; k < cols; k++) {
                maxCherries = Math.max(maxCherries, dp[rows - 1][j][k]);
            }
        }
        return maxCherries; // 返回最大樱桃数
    }
}