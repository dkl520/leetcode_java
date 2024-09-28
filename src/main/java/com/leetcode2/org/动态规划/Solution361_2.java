package com.leetcode2.org.动态规划;

public class Solution361_2 {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, killEnemies(grid, i, j));
                }
            }
        }
        return res;
    }

    private int killEnemies(char[][] grid, int x, int y) {
        int count = 0;
        for (int[] dir : dirs) {
            int newx = x;
            int newy = y;
            while (newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length && grid[newx][newy] != 'W') {
                if (grid[newx][newy] == 'E')
                    count++;
                newx += dir[0];
                newy += dir[1];
            }
        }
        return count;
    }

    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
}
