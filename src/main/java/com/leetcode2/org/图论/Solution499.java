package com.leetcode2.org.图论;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;


public class Solution499 {
    static record Point(int row, int col, int dis, String path) {
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] disMap = new int[m][n];
        for (int[] row : disMap) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int[] start = ball;
        int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        String[] action = {"d", "l", "r", "u"}; // 下左右上四个方向, 对应上面dirs
        Queue<Point> queue = new PriorityQueue<>((a, b) ->{
            if(a.dis()==b.dis()){
                return  a.path.compareTo(b.path);
            }
            return a.dis()-b.dis();
        });
        queue.add(new Point(start[0], start[1], 0, ""));
        disMap[start[0]][start[1]] = 0;
        Point resultPoint = null;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.row() == hole[0] && point.col() == hole[1]) {
                System.out.println(point.path());
                if (resultPoint == null || resultPoint.dis() > point.dis() || (resultPoint.dis() == point.dis() && point.path().compareTo(resultPoint.path()) < 0)) {
                    resultPoint = point;
                }
                continue;
            }
            outer:
            for (int i = 0; i < dirs.length; i++) {
                int[] dir = dirs[i];
                int x = dir[0] + point.row();
                int y = dir[1] + point.col();

                if (x == hole[0] && y == hole[1]) {
                    Point nextPoint = new Point(
                            x, y,
                            point.dis() + Math.abs(x - point.row()) + Math.abs(y - point.col()),
                            point.path + action[i]
                    );
                    if (nextPoint.dis() > disMap[nextPoint.row()][nextPoint.col()]) {
                        continue outer;
                    }
                    queue.offer(nextPoint);
                    disMap[nextPoint.row()][nextPoint.col()] = nextPoint.dis();
                    continue outer;
                }

                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x = dir[0] + x;
                    y = dir[1] + y;

                    if (x == hole[0] && y == hole[1]) {
                        Point nextPoint = new Point(
                                x, y,
                                point.dis() + Math.abs(x - point.row()) + Math.abs(y - point.col()),
                                point.path + action[i]
                        );
                        if (nextPoint.dis() > disMap[nextPoint.row()][nextPoint.col()]) {
                            continue outer;
                        }
                        queue.offer(nextPoint);
                        disMap[nextPoint.row()][nextPoint.col()] = nextPoint.dis();
                        continue outer;
                    }
                }
                Point nextPoint = new Point(
                        x - dir[0], y - dir[1],
                        point.dis() + Math.abs(x - dir[0] - point.row()) + Math.abs(y - dir[1] - point.col()),
                        point.path + action[i]
                );
                if (nextPoint.dis() >= disMap[nextPoint.row()][nextPoint.col()]) {
                    continue;
                }
                if (nextPoint.row()==3 && nextPoint.col()==5) {
                    System.out.println(nextPoint);
                }
                queue.offer(nextPoint);
                disMap[nextPoint.row()][nextPoint.col()] = nextPoint.dis();
            }

        }

        return resultPoint != null ? resultPoint.path() : "impossible";
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0}
        };
        int[] ball = {2, 4};
        int[] hole = {7, 6};
        Solution499 solution = new Solution499();
        System.out.println(solution.findShortestWay(maze, ball, hole));

    }
}
