package com.leetcode2.org.图论;

import java.util.*;

public class Solution913_2 {
    // 定义常量 N 表示最大节点数（假设图中最多有 50 个节点）
    private static final int N = 51;

    // 三维数组 f 用于记录状态 (i, j, t) 的结果
    // i 表示老鼠所在节点，j 表示猫所在节点，t 表示轮到谁行动（0 表示老鼠，1 表示猫）
    private int[][][] f = new int[N][N][2];

    // 三维数组 deg 用于记录状态 (i, j, t) 下剩余未被确定的邻接节点数量
    private int[][][] deg = new int[N][N][2];

    // 主函数，输入图的邻接表表示，返回游戏结果
    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        // 初始化 deg 数组，记录每个状态 (i, j, t) 下的邻接节点数量
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                deg[i][j][0] = graph[i].length; // 老鼠在节点 i，轮到老鼠行动
                deg[i][j][1] = graph[j].length; // 猫在节点 j，轮到猫行动
            }
        }

        // 减去猫在老鼠起点（节点0）的邻接边数量，因为老鼠不能返回节点0
        for (int i = 0; i < n; i++) {
            for (int v : graph[0]) {
                deg[i][v][1]--; // 猫不能通过老鼠的起点
            }
        }

        // 使用队列来执行广度优先搜索
        Queue<int[]> q = new LinkedList<>();

        // 初始化老鼠获胜的情况：老鼠到达洞穴（节点0），猫在其他节点
        for (int j = 1; j < n; j++) {
            f[0][j][0] = f[0][j][1] = 1; // 老鼠获胜
            q.offer(new int[]{0, j, 0});
            q.offer(new int[]{0, j, 1});
        }

        // 初始化猫获胜的情况：猫和老鼠在同一个节点
        for (int i = 1; i < n; i++) {
            f[i][i][0] = f[i][i][1] = 2; // 猫获胜
            q.offer(new int[]{i, i, 1});
            q.offer(new int[]{i, i, 0});
        }

        // 执行广度优先搜索
        while (!q.isEmpty()) {
            int[] u = q.poll();
            int i = u[0], j = u[1], t = u[2];

            // 终止条件（可以根据需要调整）
            if (i == 1 && j == 2 && t == 0) {
                break;
            }

            if (t == 0) { // 当前轮到老鼠行动
                for (int v : graph[j]) {
                    if (v == 0) {
                        continue;
                    }

                    if (f[i][v][1] != 0) {
                        continue;
                    }

                    if (f[i][j][t] == 2) { // 猫赢
                        f[i][v][1] = 2; // 更新状态为猫赢
                        q.offer(new int[]{i, v, 1});
                    } else { // 减少猫的可选边数
                        deg[i][v][1]--;
                        if (deg[i][v][1] == 0) { // 猫无路可走
                            f[i][v][1] = 1; // 老鼠赢
                            q.offer(new int[]{i, v, 1});
                        }
                    }
                }
            } else { // 当前轮到猫行动
                for (int v : graph[i]) {
                    if (f[v][j][0] != 0) {
                        continue;
                    }

                    if (f[i][j][t] == 1) { // 老鼠赢
                        f[v][j][0] = 1; // 更新状态为老鼠赢
                        q.offer(new int[]{v, j, 0});
                    } else { // 减少老鼠的可选边数
                        deg[v][j][0]--;
                        if (deg[v][j][0] == 0) { // 老鼠无路可走
                            f[v][j][0] = 2; // 猫赢
                            q.offer(new int[]{v, j, 0});
                        }
                    }
                }
            }
        }

        // 返回老鼠从节点 1 到节点 2 的结果
        return f[1][2][0];
    }
}
