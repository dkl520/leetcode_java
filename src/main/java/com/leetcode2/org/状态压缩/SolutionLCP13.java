package com.leetcode2.org.状态压缩;

import java.util.*;

public class SolutionLCP13 {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private static final char WALL = '#';
    private static final char BUTTON = 'M';
    private static final char STONE = 'O';
    private static final char START = 'S';
    private static final char TARGET = 'T';

    public int minimalSteps(String[] maze) {
        int n = maze.length;
        int m = maze[0].length();

        List<Point> buttons = new ArrayList<>();
        List<Point> stones = new ArrayList<>();
        Point start = null, target = null;

        // 收集关键点位置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (maze[i].charAt(j)) {
                    case BUTTON:
                        buttons.add(new Point(i, j));
                        break;
                    case STONE:
                        stones.add(new Point(i, j));
                        break;
                    case START:
                        start = new Point(i, j);
                        break;
                    case TARGET:
                        target = new Point(i, j);
                        break;
                }
            }
        }

        // 边界情况：没有机关
        if (buttons.isEmpty()) {
            return calculateDirectDistance(start, target, maze);
        }

        int nb = buttons.size();
        int ns = stones.size();
        int[][] startDist = breadthFirstSearch(start.x, start.y, maze);

        // 从某个机关到其他机关 / 起点与终点的最短距离
        int[][] dist = new int[nb][nb + 2];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        // 中间结果
        int[][][] dd = new int[nb][][];
        for (int i = 0; i < nb; i++) {
            Point button = buttons.get(i);
            int[][] d = breadthFirstSearch(button.x, button.y, maze);
            dd[i] = d;
            // 从某个点到终点不需要拿石头
            dist[i][nb + 1] = d[target.x][target.y];
        }

        // 计算关键点之间的最短距离
        for (int i = 0; i < nb; i++) {
            // 起点经过石头到机关的最短距离
            int tmp = -1;
            for (Point stone : stones) {
                int midX = stone.x, midY = stone.y;
                if (dd[i][midX][midY] != -1 && startDist[midX][midY] != -1) {
                    int potentialDist = dd[i][midX][midY] + startDist[midX][midY];
                    if (tmp == -1 || tmp > potentialDist) {
                        tmp = potentialDist;
                    }
                }
            }
            dist[i][nb] = tmp;

            // 机关之间经过石头的最短距离
            for (int j = 0; j < nb; j++) {  // 修改为从0开始
                if (i == j) continue;  // 跳过自身
                int mn = -1;
                for (Point stone : stones) {
                    int midX = stone.x, midY = stone.y;
                    if (dd[i][midX][midY] != -1 && dd[j][midX][midY] != -1) {
                        int potentialDist = dd[i][midX][midY] + dd[j][midX][midY];
                        if (mn == -1 || mn > potentialDist) {
                            mn = potentialDist;
                        }
                    }
                }
                dist[i][j] = dist[j][i] = mn;
            }
        }

        // 检查是否存在无法达成的情形
        for (int i = 0; i < nb; i++) {
            if (dist[i][nb] == -1 || dist[i][nb + 1] == -1) {
                return -1;
            }
        }

        // dp 数组，-1 代表没有遍历到
        int[][] dp = new int[1 << nb][nb];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // 初始化起始状态
        for (int i = 0; i < nb; i++) {
            dp[1 << i][i] = dist[i][nb];
        }

        // 状态压缩动态规划
        for (int mask = 1; mask < (1 << nb); mask++) {
            for (int i = 0; i < nb; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < nb; j++) {
                        if ((mask & (1 << j)) == 0) {
                            int nextMask = mask | (1 << j);
                            if (dp[nextMask][j] == -1 ||
                                    dp[nextMask][j] > dp[mask][i] + dist[i][j]) {
                                dp[nextMask][j] = dp[mask][i] + dist[i][j];
                            }
                        }
                    }
                }
            }
        }

        // 寻找最小路径
        int ret = -1;
        int finalMask = (1 << nb) - 1;
        for (int i = 0; i < nb; i++) {
            int potential = dp[finalMask][i] + dist[i][nb + 1];
            if (ret == -1 || ret > potential) {
                ret = potential;
            }
        }

        return ret;
    }

    private int calculateDirectDistance(Point start, Point target, String[] maze) {
        int[][] distances = breadthFirstSearch(start.x, start.y, maze);
        return distances[target.x][target.y];
    }

    private int[][] breadthFirstSearch(int startX, int startY, String[] maze) {
        int n = maze.length, m = maze[0].length();
        int[][] distances = new int[n][m];
        for (int[] row : distances) {
            Arrays.fill(row, -1);
        }

        distances[startX][startY] = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = current.x + DX[k];
                int ny = current.y + DY[k];

                if (isValidMove(nx, ny, maze, distances)) {
                    queue.offer(new Point(nx, ny));
                    distances[nx][ny] = distances[current.x][current.y] + 1;
                }
            }
        }

        return distances;
    }

    private boolean isValidMove(int x, int y, String[] maze, int[][] distances) {
        return x >= 0 && x < maze.length
                && y >= 0 && y < maze[0].length()
                && maze[x].charAt(y) != WALL
                && distances[x][y] == -1;
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}