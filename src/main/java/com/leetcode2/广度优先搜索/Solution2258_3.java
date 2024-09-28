package com.leetcode2.广度优先搜索;

import java.util.*;

public class Solution2258_3 {
    // 定义一个很大的数，用作无穷大，表示火焰无法到达的时间
    static final int INF = 1000000000;
    // 定义四个方向的移动，分别表示上、下、左、右
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 初始化火焰到达每个格子的时间，初始时设为INF
        int[][] fireTime = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(fireTime[i], INF);
        }
        /* 通过广度优先搜索（BFS）求出每个格子着火的时间 */
        bfs(grid, fireTime);
        /* 找到从起点到终点的最短时间 */
        int arriveTime = getArriveTime(grid, fireTime, 0);
        /* 如果安全屋不可达，返回-1 */
        if (arriveTime < 0) {
            return -1;
        }
        /* 如果火焰不会到达安全屋，返回无穷大 */
        if (fireTime[m - 1][n - 1] == INF) {
            return INF;
        }
        // 计算从起点到达终点的安全时间差
        int ans = fireTime[m - 1][n - 1] - arriveTime;
        // 检查在安全时间内到达终点是否可行
        return getArriveTime(grid, fireTime, ans) >= 0 ? ans : (ans - 1);
    }

    // BFS算法计算每个格子着火的时间
    public void bfs(int[][] grid, int[][] fireTime) {
        int m = grid.length;
        int n = grid[0].length;
        // 队列用于存放火源位置
        Queue<int[]> queue = new ArrayDeque<int[]>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { // 如果是火源
                    queue.offer(new int[]{i, j}); // 将火源位置加入队列
                    fireTime[i][j] = 0; // 火源位置着火时间为0
                }
            }
        }

        int time = 1; // 初始化时间为1
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] arr = queue.poll(); // 取出队列中的元素
                int cx = arr[0], cy = arr[1];
                for (int j = 0; j < 4; j++) { // 遍历四个方向
                    int nx = cx + dirs[j][0];
                    int ny = cy + dirs[j][1];
                    if (nx >= 0 && ny >= 0 && nx < m && ny < n) { // 判断是否越界
                        if (grid[nx][ny] == 2 || fireTime[nx][ny] != INF) {
                            continue; // 如果是墙或已着火的格子，跳过
                        }
                        queue.offer(new int[]{nx, ny}); // 将新的着火点加入队列
                        fireTime[nx][ny] = time; // 记录着火时间
                    }
                }
            }
            time++; // 增加时间
        }
    }

    // 计算从起点到终点的最短到达时间
    public int getArriveTime(int[][] grid, int[][] fireTime, int stayTime) {
        int m = fireTime.length;
        int n = fireTime[0].length;
        boolean[][] visit = new boolean[m][n]; // 记录访问状态
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{0, 0, stayTime}); // 将起点加入队列
        visit[0][0] = true; // 标记起点已访问

        while (!queue.isEmpty()) {
            int[] arr = queue.poll(); // 取出队列中的元素
            int cx = arr[0], cy = arr[1], time = arr[2];
            for (int j = 0; j < 4; j++) { // 遍历四个方向
                int nx = cx + dirs[j][0];
                int ny = cy + dirs[j][1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) { // 判断是否越界
                    if (grid[nx][ny] == 2 || visit[nx][ny]) {
                        continue; // 如果是墙或已访问的格子，跳过
                    }
                    if (nx == m - 1 && ny == n - 1) { // 如果到达终点
                        return time + 1; // 返回到达时间
                    }
                    if (fireTime[nx][ny] > time + 1) { // 判断是否安全
                        visit[nx][ny] = true; // 标记已访问
                        queue.offer(new int[]{nx, ny, time + 1}); // 将新位置加入队列
                    }
                }
            }
        }
        return -1; // 如果无法到达终点，返回-1
    }
}
