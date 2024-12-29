package com.leetcode2.广度优先搜索;

import java.util.ArrayDeque;
import java.util.Queue;

public class SolutionLCR105 {

    int[][] dicts = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && (grid[i][j] == 1)) {
                    max = Math.max(max, bfs(i, j, grid, visited));
                }
            }
        }
        return max;

    }
    int bfs(int i, int j, int[][] grid, boolean[][] visited) {
        int count = 1;
        visited[i][j] = true;
        Queue<int[]> list = new ArrayDeque<>();
        list.add(new int[]{i, j});
        while (!list.isEmpty()) {
            int[] cur = list.poll();
            for (int[] dict : dicts) {
                int newRow = dict[0] + cur[0];
                int newCol = dict[1] + cur[1];
                if (newRow >= 0 && newCol >= 0
                        && newRow < grid.length
                        && newCol < grid[0].length
                        && grid[newRow][newCol] == 1
                        && !visited[newRow][newCol]) {
                    count++;
                    System.out.println(count);
                    visited[newRow][newCol] = true;
                    list.add(new int[]{newRow, newCol});
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println(new SolutionLCR105().maxAreaOfIsland(grid));


    }


}
