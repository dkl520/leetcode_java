package com.leetcode2.org.扫描线;

import java.util.Arrays;
import java.util.OptionalInt;

public class Solution807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int countSum = 0;
        int n = grid.length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                countSum += findPaddingNum(i, j, grid)-grid[i][j];
            }
        }
        return countSum;
    }
    int findPaddingNum(int i, int j, int[][] grid) {
        int arrRowMax = Arrays.stream(grid[i]).max().orElse(Integer.MIN_VALUE);  // 使用默认值
        int arrColMax = Arrays.stream(grid).mapToInt(row -> row[j]).max().orElse(Integer.MIN_VALUE);  // 使用默认值
        return Math.min(arrColMax, arrRowMax);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {3, 0, 8, 4},
                {2, 4, 5, 7},
                {9, 2, 6, 3},
                {0, 3, 1, 0}
        };
        System.out.println(new Solution807().maxIncreaseKeepingSkyline(grid));
    }
}
