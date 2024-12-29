package com.leetcode2.org.类实现;

public class NeighborSum {
    int[][] grid;
    int n;
    int[][] adjacentGrid = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };
    int[][] diagonalGrid = new int[][]{
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
    };

    public NeighborSum(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
    }

    public int adjacentSum(int value) {
        int[] vPos = findPosition(value);
        int result = 0;
        for (int[] aPos : adjacentGrid) {
            int newRow = vPos[0] + aPos[0];
            int newCol = vPos[1] + aPos[1];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                result += grid[newRow][newCol];
            }
        }
        return result;
    }

    public int diagonalSum(int value) {
        int[] vPos = findPosition(value);
        int result = 0;
        for (int[] aPos : diagonalGrid) {
            int newRow = vPos[0] + aPos[0];
            int newCol = vPos[1] + aPos[1];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                result += grid[newRow][newCol];
            }
        }
        return result;
    }

    public int[] findPosition(int value) {
        int[] result = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == value) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}