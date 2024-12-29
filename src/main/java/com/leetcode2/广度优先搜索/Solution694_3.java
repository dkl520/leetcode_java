package com.leetcode2.广度优先搜索;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution694_3 {


    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int numDistinctIslands(int[][] grid) {
        Set<String> uniqueIslands = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    uniqueIslands.add(serializeIslandShape(i, j, grid, visited));
                }
            }
        }

        return uniqueIslands.size();
    }

    private String serializeIslandShape(int row, int col, int[][] grid, boolean[][] visited) {
        StringBuilder shape = new StringBuilder();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            shape.append("(").append(current[0] - row).append(",").append(current[1] - col).append(")");

            for (int[] dir : DIRECTIONS) {
                int newRow = current[0] + dir[0];
                int newCol = current[1] + dir[1];

                if (isValidCell(newRow, newCol, grid) && grid[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return shape.toString();
    }

    private boolean isValidCell(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

//            System.out.println(new Solution694Optimized().numDistinctIslands(grid));
    }
}
