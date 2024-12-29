package com.leetcode2.广度优先搜索;

import java.util.*;
import java.util.stream.Collectors;

public class Solution694_2 {
    int[][] dicts = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    String bfs(int row, int col, int[][] grid, boolean[][] visited) {
        List<int[]> result = new ArrayList<>();
        visited[row][col] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        result.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] dict : dicts) {
                int newRow = dict[0] + pos[0];
                int newCol = dict[1] + pos[1];
                if (
                        newRow >= 0 && newCol >= 0
                                && newRow < grid.length
                                && newCol < grid[0].length && grid[newRow][newCol] == 1
                                && !visited[newRow][newCol]) {

                    visited[newRow][newCol] = true;
                    result.add(new int[]{newRow, newCol});

                    queue.add(new int[]{newRow, newCol});
                }

            }
        }
        for (int[] list : result) {
            list[0] = list[0] - row;
            list[1] = list[1] - col;
        }
        return result.stream().map(Arrays::toString).collect(Collectors.joining());

    }

    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> result = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    String listS = bfs(i, j, grid, visited);
                    result.add(listS);

                }
            }
        }
        return result.size();
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        System.out.println(new Solution694_2().numDistinctIslands(grid));
    }
}