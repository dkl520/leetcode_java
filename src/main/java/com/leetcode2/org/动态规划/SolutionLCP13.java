package com.leetcode2.org.动态规划;

import java.util.*;

public class SolutionLCP13 {
    // 定义无穷大值，用于初始化距离
    private static final int INF = Integer.MAX_VALUE / 2; // 避免溢出
    // 定义四个方向：右、下、左、上
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimalSteps(String[] maze) {
        int n = maze.length, m = maze[0].length(); // 获取迷宫的行数和列数
        List<int[]> buttons = new ArrayList<>(); // 存储所有按钮的位置
        List<int[]> stones = new ArrayList<>(); // 存储所有石头的位置
        int[] start = null, end = null; // 存储起点和终点的位置

        // 解析迷宫
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maze[i].charAt(j);
                if (c == 'M') buttons.add(new int[]{i, j}); // 添加按钮位置
                else if (c == 'O') stones.add(new int[]{i, j}); // 添加石头位置
                else if (c == 'S') start = new int[]{i, j}; // 设置起点
                else if (c == 'T') end = new int[]{i, j}; // 设置终点
            }
        }

        int nb = buttons.size(); // 按钮数量
        int ns = stones.size(); // 石头数量

        // 如果没有按钮，直接找从起点到终点的最短路径
        if (nb == 0) {
            int dist = bfs(maze, start)[end[0]][end[1]];
            return dist == INF ? -1 : dist;
        }

        // 如果没有石头，无法到达终点
        if (ns == 0) return -1;

        int[][] startDist = bfs(maze, start); // 计算起点到所有位置的距离
        int[][] endDist = bfs(maze, end); // 计算终点到所有位置的距离
        int[][][] dist = new int[nb][nb + 2][]; // 存储按钮之间的距离，+2是为了包含起点和终点

        // 计算每个按钮到其他所有位置的距离
        for (int i = 0; i < nb; i++) {
            dist[i] = bfs(maze, buttons.get(i));
        }

// 初始化动态规划数组，dp[mask][i] 表示已访问状态为 mask，最后一个访问的按钮为 i 的最短路径
        int[][] dp = new int[1 << nb][nb];
// 将 dp 数组中的每一行初始化为无穷大（INF），表示尚未计算的路径
        for (int[] row : dp) Arrays.fill(row, INF);

// 初始化 dp 数组，计算从起点到每个按钮的最短距离
        for (int i = 0; i < nb; i++) {
            int minDist = INF; // 初始化最小距离为无穷大
            for (int[] stone : stones) {
                // 计算从起点到每块石头的距离
                int distToStone = startDist[stone[0]][stone[1]];
                // 如果从起点可以到达石头
                if (distToStone != INF) {
                    // 计算从起点经过石头到按钮 i 的最短路径，并更新 minDist
                    minDist = Math.min(minDist, distToStone + dist[i][stone[0]][stone[1]]);
                }
            }

            // 如果找到了可行的路径，更新 dp 数组，表示从起点到按钮 i 的最短路径
            if (minDist != INF) dp[1 << i][i] = minDist;
        }
//        minDist 表示的是 从起点经过任意一个石头再到任意一个按钮的最短距离

//，dp[mask][i] 表示已访问状态为 mask，最后一个访问的按钮为 i 的最短路径

// 开始动态规划过程，遍历所有状态组合（mask），mask 表示已访问的按钮集合
        for (int mask = 1; mask < (1 << nb); mask++) {
            for (int i = 0; i < nb; i++) {
                // 如果按钮 i 尚未被访问，则跳过
                if ((mask & (1 << i)) == 0) continue;
                for (int j = 0; j < nb; j++) {
                    // 如果按钮 j 已经被访问，则跳过
                    if ((mask & (1 << j)) != 0) continue;
                    int minDist = INF; // 初始化最小距离为无穷大
                    for (int[] stone : stones) {
                        // 如果从按钮 i 和 j 都可以到达这块石头
                        if (dist[i][stone[0]][stone[1]] != INF && dist[j][stone[0]][stone[1]] != INF) {
                            // 计算从按钮 i 经石头到按钮 j 的最短路径，并更新 minDist
                            minDist = Math.min(minDist, dist[i][stone[0]][stone[1]] + dist[j][stone[0]][stone[1]]);
                        }
                    }
                    // 如果找到了可行的路径，更新 dp 数组
                    if (minDist != INF) {
                        System.out.println(mask);
                        System.out.println(mask | (1 << j));

                        dp[mask | (1 << j)][j] = Math.min(dp[mask | (1 << j)][j], dp[mask][i] + minDist);
                    }
                }
            }
        }

// 计算最终结果，遍历所有可能的最后一个访问的按钮
        int ans = INF; // 初始化最终结果为无穷大
        for (int i = 0; i < nb; i++) {
            // 如果从起点通过所有按钮并且最后到达按钮 i 的路径存在
            if (dp[(1 << nb) - 1][i] != INF && endDist[buttons.get(i)[0]][buttons.get(i)[1]] != INF) {
                // 计算从按钮 i 到终点的最短路径，并更新最终结果 ans
                ans = Math.min(ans, dp[(1 << nb) - 1][i] + endDist[buttons.get(i)[0]][buttons.get(i)[1]]);
            }
        }

// 返回最终结果，如果 ans 仍为无穷大，表示无法到达终点，返回 -1；否则返回最短步数 ans
        return ans == INF ? -1 : ans;

    }

    // 广度优先搜索函数，计算从起点到所有位置的最短距离
    private int[][] bfs(String[] maze, int[] start) {
        int n = maze.length, m = maze[0].length();
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, INF); // 初始化所有距离为无穷大
        dist[start[0]][start[1]] = 0; // 起点距离为0
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : DIRECTIONS) {
                int nx = x + dir[0], ny = y + dir[1];
                // 检查新位置是否有效且未访问过
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx].charAt(ny) != '#' && dist[nx][ny] == INF) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        String[] maze = new String[]{"S#O", "M.T", "M.."};
        SolutionLCP13 solution = new SolutionLCP13();
        solution.minimalSteps(maze);

    }
}


//状态压缩是把n!的时间复杂度的 压缩到 了2^n 次方!!!!