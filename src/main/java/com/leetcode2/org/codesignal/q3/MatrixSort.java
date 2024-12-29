package com.leetcode2.org.codesignal.q3;

import java.util.ArrayList;
import java.util.List;

public class MatrixSort {

    List<int[]> getMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int[][] visited = new int[m][n];
            int[] curR = new int[2];
            curR[0] = matrix[i][0];
            if (i == 0) {
                curR[1] = dfs(i, 0, matrix, new int[]{1, 1}, visited, 0);
            } else {
                curR[1] = dfs(i, 0, matrix, new int[]{-1, 1}, visited, 0);
            }
            result.add(curR);
        }
        result.sort((a, b) -> a[1] - b[1]);
        return result;
    }

    int dfs(int row, int col, int[][] grid, int[] dir, int[][] visited, int start) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0) return start;
        if (visited[row][col] == 1) {
            return start;
        }
        visited[row][col] = 1;
        start += grid[row][col];

        if (row == 0) {
            dir = new int[]{1, 1};
        } else if (row == grid.length - 1 && col != 0) {
            dir = new int[]{-1, -1};
        } else if (col == grid[0].length - 1) {
            dir = new int[]{1, -1};
        }
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        return dfs(newRow, newCol, grid, dir, visited, start);

    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]
                {
                        {3, 0, 1, 4, 2},
                        {5, 6, 3, 2, 1},
                        {1, 2, 0, 1, 5},
                        {4, 1, 0, 1, 7},
                        {1, 0, 3, 0, 5}
                };

        MatrixSort matrixSort = new MatrixSort();
        System.out.println(matrixSort.getMatrix(matrix));
    }
}
