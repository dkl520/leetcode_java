package com.leetcode2.广度优先搜索;

import java.util.PriorityQueue;

class PointWithBomb2 {
    int x;
    int y;
    int distance;
    int bomb;
    int steps;
    int estimatedTotalCost;

    public PointWithBomb2(int x, int y, int distance, int bomb, int steps) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.bomb = bomb;
        this.steps = steps;
        this.estimatedTotalCost = distance + steps;
    }
}


public class Solution1293_2 {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<PointWithBomb2> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.estimatedTotalCost , b.estimatedTotalCost ));
//        PriorityQueue<PointWithBomb2> queue = new PriorityQueue<>((a, b) -> {
//            // 首先按bomb从多到少排序
//            if (a.bomb != b.bomb) {
//                return Integer.compare(b.bomb, a.bomb);
//            }
//            // 然后按estimatedTotalCost从小到大排序
//            return Integer.compare(a.estimatedTotalCost, b.estimatedTotalCost);
//        });
        PointWithBomb2 firstPoint = new PointWithBomb2(0, 0, heuristic(grid, new int[]{0, 0}), k, 0);

        queue.offer(firstPoint);
        return aStar(grid, queue,k);
    }

    int aStar(int[][] board, PriorityQueue<PointWithBomb2> queue,int maxK) {
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
            PointWithBomb2 point = queue.poll();
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
                        PointWithBomb2 nextPoint = new PointWithBomb2(newX, newY, heuristic(board, new int[]{newX, newY}), newK, point.steps + 1);
                        queue.offer(nextPoint);
                        visited[newX][newY][newK] = true;
                    }
                }
            }
        }
        return -1;
    }


    private int heuristic(int[][] board, int[] target) {
        int m = board.length;
        int n = board[0].length;
        return Math.abs(m - 1 - target[0]) + Math.abs(n - 1 - target[1]);
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
        Solution1293_2 solution = new Solution1293_2();
        System.out.println(solution.shortestPath(grid, 1));
    }

}
