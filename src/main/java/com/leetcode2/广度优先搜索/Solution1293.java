package com.leetcode2.广度优先搜索;

import java.util.LinkedList;
import java.util.Queue;

class PointWithBomb {
    int x;
    int y;
    int bomb;
    int steps;

    public PointWithBomb(int x, int y, int bomb, int steps) {
        this.x = x;
        this.y = y;
        this.bomb = bomb;
        this.steps = steps;
    }
}

public class Solution1293 {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<PointWithBomb> queue = new LinkedList<>();
        PointWithBomb firstPoint = new PointWithBomb(0, 0, k, 0);

        queue.offer(firstPoint);
        return bfs(grid, queue, k);
    }

    int bfs(int[][] board, Queue<PointWithBomb> queue, int maxK) {
        int m = board.length;
        int n = board[0].length;
        boolean[][][] visited = new boolean[m][n][maxK + 1];
        visited[0][0][maxK] = true;
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1}
        };
        while (!queue.isEmpty()) {
            PointWithBomb point = queue.poll();
            int x = point.x;
            int y = point.y;
            if (x == m - 1 && y == n - 1) {
                return point.steps;
            }
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                int newK = point.bomb;
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (board[newX][newY] == 1 && newK == 0) {
                        continue;
                    }
                    if (board[newX][newY] == 1 && newK > 0) {
                        newK -= 1;
                    }
                    if (!visited[newX][newY][newK]) {
                        PointWithBomb nextPoint = new PointWithBomb(newX, newY, newK, point.steps + 1);
                        queue.offer(nextPoint);
                        visited[newX][newY][newK] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
        };
        Solution1293 solution = new Solution1293();
        System.out.println(solution.shortestPath(grid, 1));
    }
}
