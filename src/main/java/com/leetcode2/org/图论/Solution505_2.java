package com.leetcode2.org.图论;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

record Point(int row, int col, int dis) {}
public class Solution505_2 {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] disMap = new int[m][n];
        for (int[] row : disMap) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Point> queue = new PriorityQueue<>((a, b) -> a.dis() - b.dis());
        queue.add(new Point(start[0], start[1], 0));
        disMap[start[0]][start[1]]=0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int[] dir : dirs) {
                int x = dir[0] + point.row();
                int y = dir[1] + point.col();
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x = dir[0] + x;
                    y = dir[1] + y;
                }
                Point nextPoint = new Point(x - dir[0], y - dir[1],
                        point.dis() + Math.abs(x - dir[0] - point.row()) + Math.abs(y - dir[1] - point.col()));
                if (nextPoint.dis() >= disMap[nextPoint.row()][nextPoint.col()]) {
                    continue;
                }
                queue.add(nextPoint);
                disMap[nextPoint.row()][nextPoint.col()] = nextPoint.dis();
            }
        }
        return disMap[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : disMap[destination[0]][destination[1]];
    }

    public static void main(String[] args) {

        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] destination = {4, 4};

        System.out.println(new Solution505_2().shortestDistance(maze, start, destination));

    }

}
