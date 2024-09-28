package com.leetcode2.org.图论;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution3123_3 {
    static int[] e, ne, h, w; // e: 边的终点, ne: 下一条边的索引, h: 每个节点的第一条边的索引, w: 边的权重
    static int idx; // 当前边的索引

    // 添加一条边 a -> b, 权重为 c
    static void add(int a, int b, int c) {
        e[idx] = b; // 记录边的终点
        w[idx] = c; // 记录边的权重
        ne[idx] = h[a]; // 记录当前节点 a 的上一条边的索引
        h[a] = idx++; // 更新节点 a 的第一条边为当前边，并增加索引
    }

    static int[] dis; // 记录到每个节点的最短距离
    static int[] vis; // 记录每个节点是否在队列中
    static boolean[] ans; // 记录每条边是否在最短路径中

    public boolean[] findAnswer(int n, int[][] edges) {
        int i, j;
        int m = edges.length; // 边的数量
        e = new int[m * 2 + 5];
        ne = new int[m * 2 + 5];
        h = new int[m * 2 + 5];
        w = new int[m * 2 + 5];
        dis = new int[n];
        vis = new int[n];
        ans = new boolean[m];
        Arrays.fill(h, -1); // 初始化每个节点的第一条边的索引为 -1
        idx = 0;
        for (i = 0; i < m; i++) {
            add(edges[i][0], edges[i][1], edges[i][2]);
            add(edges[i][1], edges[i][0], edges[i][2]);
            // 每条无向边被添加两次，一次正向，一次反向
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0); // 从节点 0 开始
        vis[0] = 1; // 标记节点 0 在队列中
        Arrays.fill(dis, Integer.MAX_VALUE); // 初始化每个节点的距离为无穷大
        dis[0] = 0; // 起点的距离为 0
        while (!q.isEmpty()) {
            int u = q.remove();
            vis[u] = 0; // 标记节点 u 不在队列中
            for (i = h[u]; i != -1; i = ne[i]) {
                j = e[i];
                int ww = w[i];
                if (dis[j] > dis[u] + ww) {
                    dis[j] = dis[u] + ww; // 更新节点 j 的最短距离
                    if (vis[j] == 0) {
                        vis[j] = 1; // 如果节点 j 不在队列中，将其加入队列
                        q.add(j);
                    }
                }
            }
        }
        int maxt = dis[n - 1]; // 记录到终点的最短距离
        q.add(n - 1); // 从终点开始反向搜索
        Arrays.fill(vis, 0); // 重置标记数组
        vis[n - 1] = 1; // 标记终点在队列中
        while (!q.isEmpty()) {
            int u = q.remove();
            vis[u] = 0; // 标记节点 u 不在队列中
            for (i = h[u]; i != -1; i = ne[i]) {
                j = e[i];
                int ww = w[i];
                if (dis[u] == dis[j] + ww) {
                    int id = i / 2; // 每条无向边添加了两次，因此边的索引为 i / 2
                    ans[id] = true; // 记录这条边在最短路径中
                    if (vis[j] == 0) {
                        vis[j] = 1; // 如果节点 j 不在队列中，将其加入队列
                        q.add(j);
                    }
                }
            }
        }
        return ans;
    }
}
