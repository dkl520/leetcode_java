package com.leetcode2.CogeSignal.Q3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boucing {

    int[] calcmatrixBoucing(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Integer[] result = new Integer[m];
        Arrays.setAll(result, (k) -> k);
        Map<Integer, Integer> memo = new HashMap<>();
        int[][] visited = new int[m][n];
        for (int index : result) {
            if (index == 0) {
                memo.put(index, dfs(index, 0, matrix, 0, visited, new int[]{1, 1}));
            } else {
                memo.put(index, dfs(index, 0, matrix, 0, visited, new int[]{-1, 1}));
            }
        }

        Arrays.sort(result, (a, b) -> memo.get(a) - memo.get(b));

        return Arrays.stream(result).mapToInt(v -> v.intValue()).toArray();
    }

    int dfs(int row, int col, int[][] grid, int amount, int[][] visited, int[] dir) {
        int m = grid.length;
        int n = grid[0].length;
        if (col == 0 && visited[row][col] == 1) {
            return amount;
        }
        amount += grid[row][col];
        visited[row][col] = 1;
        if (row == 0 && col == n - 1) {
            return amount;
        }
        if (row == m - 1 && col == n - 1) {
            return amount;
        }

        if (row == 0) {
            dir = new int[]{1, 1};
        }
        if (row == m - 1) {
            dir = new int[]{-1, -1};
        }
        if (col == n - 1) {
            dir = new int[]{1, -1};
        }
        if (row == m - 1 && col == 0) {
            dir = new int[]{-1, 1};
        }
        int newRow = dir[0] + row;
        int newCol = dir[1] + col;
        return dfs(newRow, newCol, grid, amount, visited, dir);


    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 4, 3, 9},
                {3, 6, 4, 7},
                {9, 3, 2, 5},
                {4, 1, 3, 3}
        };

        Boucing boucing = new Boucing();
        System.out.println(
                Arrays.toString(boucing.calcmatrixBoucing(matrix)));
    }
}
