package com.leetcode2.org.高级搜索;

import java.util.*;

public class EscapeFire {
    // 定义方向数组，上下左右四个方向
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // 初始化火到达每个格子的时间为无穷大
        int[][] fireTime = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        }

        // 优先队列用于优化BFS过程，按照时间从小到大排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // 将所有着火的格子加入优先队列，并初始化火到达时间为0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    pq.offer(new int[]{i, j, 0});
                    fireTime[i][j] = 0;
                }
            }
        }

        // SPFA 计算火到达每个格子的最短时间
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0], y = current[1], time = current[2];

            for (int[] dir : DIRECTIONS) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // 跳过越界、墙壁或者已经找到更短时间的格子
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == 2 || fireTime[nx][ny] <= time + 1) {
                    continue;
                }

                fireTime[nx][ny] = time + 1;
                pq.offer(new int[]{nx, ny, time + 1});
            }
        }

        // 使用 BFS 计算从起点到终点的最短时间
        int[][] personTime = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(personTime[i], Integer.MAX_VALUE);
        }
        personTime[0][0] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], time = current[2];

            for (int[] dir : DIRECTIONS) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // 跳过越界、墙壁或者已经找到更短时间的格子
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == 2 || personTime[nx][ny] <= time + 1) {
                    continue;
                }

                personTime[nx][ny] = time + 1;
                queue.offer(new int[]{nx, ny, time + 1});
            }
        }

        // 计算最大可停留时间
        int maxWaitTime = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 判断当前格子火到达时间和人到达时间
                if (fireTime[i][j] > personTime[i][j]) {
                    int waitTime = fireTime[i][j] - personTime[i][j] - 1;
                    maxWaitTime = Math.max(maxWaitTime, waitTime);
                }
            }
        }

        // 特殊情况处理
        if (personTime[m - 1][n - 1] > fireTime[m - 1][n - 1]) {
            return -1;
        } else if (fireTime[m - 1][n - 1] == Integer.MAX_VALUE) {
            return 1000000000;
        }

        return maxWaitTime;
    }

    public static void main(String[] args) {
        EscapeFire ef = new EscapeFire();
        int[][] grid = {{0, 2, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 2, 1, 0}, {0, 2, 0, 0, 1, 2, 0}, {0, 0, 2, 2, 2, 0, 2}, {0, 0, 0, 0, 0, 0, 0}};
        System.out.println(ef.maximumMinutes(grid)); // 输出应为3
    }
}

