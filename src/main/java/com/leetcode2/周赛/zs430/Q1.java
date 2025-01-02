package com.leetcode2.周赛.zs430;

public class Q1 {
    public int minimumOperations(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] <= grid[i - 1][j]) {
                    count += (grid[i - 1][j] + 1) - grid[i][j];
                    grid[i][j] = grid[i - 1][j] + 1;
                }


            }
        }
        return count;
    }

    public static void main(String[] args) {
        Q1 Q = new Q1();
        int[][] grid = {
                {3, 2},
                {1, 3},
                {3, 4},
                {0, 1}
        };

        Q.minimumOperations(grid);

    }
}
