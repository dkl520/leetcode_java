package com.leetcode2.CogeSignal.Q3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boucing_2 {
    int[] calcmatrixBoucing(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Integer[] result = new Integer[m];
        Arrays.setAll(result, (k) -> k);
        Map<Integer, Integer> memo = new HashMap<>();
        int[][] visited = new int[m][n];
        for (int index : result) {
            int[] dir = index == 0 ? new int[]{1, 1} : new int[]{-1, 1};
            memo.put(index, dfs(index, 0, matrix, 0, visited, dir));
        }

        Arrays.sort(result, (a, b) -> memo.get(a) - memo.get(b));

        return Arrays.stream(result).mapToInt(v -> v).toArray();
    }

    int dfs(int row, int col, int[][] grid, int amount, int[][] visited, int[] dir) {
        int m = grid.length;
        int n = grid[0].length;
        if (col == 0 && visited[row][col] == 1) {
            return amount;
        }
        amount += grid[row][col];
        visited[row][col] = 1;

        if ((row == 0 && col == n - 1) || (row == m - 1 && col == n - 1)) {
            return amount;
        }

        if (row == 0) dir = new int[]{1, 1};
        if (row == m - 1) dir = new int[]{-1, -1};
        if (col == n - 1) dir = new int[]{1, -1};
        if (row == m - 1 && col == 0) dir = new int[]{-1, 1};

        return dfs(row + dir[0], col + dir[1], grid, amount, visited, dir);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 4, 3, 9},
                {3, 6, 4, 7},
                {9, 3, 2, 5},
                {4, 1, 3, 3}
        };

        Boucing_2 boucing = new Boucing_2();
        System.out.println(Arrays.toString(boucing.calcmatrixBoucing(matrix)));
    }
}