package com.leetcode2.周赛.zs130;

public class Q1 {
    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) return true;

        if (m == 1) {
            for (int j = n - 1; j > 0; j--) {
                if (grid[0][j] == grid[0][j - 1]) {
                    return false;
                }
            }
            return true;
        }
        if (n == 1) {
            for (int i = m - 1; i > 0; i--) {
                if (grid[i][0] != grid[i - 1][0]) {
                    return false;
                }
            }
            return true;
        }


        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int cur = grid[i][j];
                if (j > 0) {
                    int preLeft = grid[i][j - 1];
                    if (cur == preLeft) {
                        return false;
                    }
                }
                if (i > 0) {
                    int preUp = grid[i - 1][j];
                    if (cur != preUp) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q1 q = new Q1();
        int[][] grid = new int[][]{
                {1, 0, 2},
                {2, 0, 2},
                {1, 0, 2}

        };
        System.out.println(
                q.satisfiesConditions(grid)
        );


    }
}