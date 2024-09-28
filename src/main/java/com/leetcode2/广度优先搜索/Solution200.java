package com.leetcode2.广度优先搜索;

import java.util.LinkedList;
import java.util.Queue;

public class Solution200 {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visitGrid = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char el = grid[i][j];
                if (el == '1' && !visitGrid[i][j]) {
                    visitGrid[i][j] = true;
                    bfs(grid, i, j, visitGrid);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void bfs(char[][] grid, int row, int col, boolean[][] visitGrid) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        // 下 上 右 左
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            for (int index = 0; index < dirX.length; index++) {
                int newY = currentRow + dirY[index];
                int newX = currentCol + dirX[index];
                if (newX >= 0 && newX < grid[0].length && newY >= 0 && newY < grid.length && grid[newY][newX] == '1' && !visitGrid[newY][newX]) {
                    visitGrid[newY][newX] = true;
                    queue.add(new int[]{newY, newX});
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '1', '1'},
                {'1', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '1'},
                {'1', '1', '0', '1', '1'}
        };

        Solution200 solution200 = new Solution200();
        System.out.println(solution200.numIslands(grid));
    }
}


