package com.leetcode2.org.扫描线;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

class Solution807_2 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] rowMaxes = new int[n];
        int[] colMaxes = new int[n];

        // 计算每行和每列的最大值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowMaxes[i] = Math.max(rowMaxes[i], grid[i][j]);
                colMaxes[j] = Math.max(colMaxes[j], grid[i][j]);
            }
        }

        int countSum = 0;

        // 计算增量总和
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                countSum += Math.min(rowMaxes[i], colMaxes[j]) - grid[i][j];
            }
        }

        return countSum;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {3, 0, 8, 4},
                {2, 4, 5, 7},
                {9, 2, 6, 3},
                {0, 3, 1, 0}
        };
        System.out.println(new Solution807_2().maxIncreaseKeepingSkyline(grid));

    }
}
