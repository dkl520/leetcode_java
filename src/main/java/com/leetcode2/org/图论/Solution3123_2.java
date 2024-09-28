package com.leetcode2.org.图论;

import java.util.*;

public class Solution3123_2 {
    /**
     * 找出图中所有关键边（移除后会导致从节点0到节点n-1的最短路径长度增加或不可达的边）
     *
     * @param n 节点数量
     * @param edges 边数组，每条边包含起点、终点和权重
     * @return 布尔数组，表示每条边是否为关键边
     */
    public boolean[] findAnswer(int n, int[][] edges) {
        // 初始化图的邻接表表示
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        // 遍历边数组，构建图的邻接表
        for (int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            int x = e[0], y = e[1], w = e[2];
            g[x].add(new int[]{y, w, i}); // x指向y的边，包括y节点、权重和边的索引
            g[y].add(new int[]{x, w, i}); // y指向x的边，同样包括x节点、权重和边的索引
        }

        // 使用Dijkstra算法计算从节点0到所有其他节点的最短路径长度
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE); // 初始化所有节点的距离为无穷大
        dis[0] = 0; // 起点0到自身的距离为0
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        // 优先队列，根据距离排序
        pq.offer(new long[]{0, 0}); // 初始时，起点0的距离为0，加入队列
        while (!pq.isEmpty()) {
            long[] dxPair = pq.poll(); // 取出当前距离最小的节点
            long dx = dxPair[0];
            int x = (int) dxPair[1];
            // 如果当前取出的节点的距离比已知的距离大，则跳过
            if (dx > dis[x]) {
                continue;
            }
            // 遍历当前节点的所有邻接节点
            for (int[] t : g[x]) {
                int y = t[0];
                int w = t[1];
                long newDis = dx + w; // 计算通过当前边到达y的新距离
                // 如果新距离小于已知的距离，则更新距离，并将新距离和节点y加入优先队列
                if (newDis < dis[y]) {
                    dis[y] = newDis;
                    pq.offer(new long[]{newDis, y});
                }
            }
        }
        // 如果终点n-1的距离仍然是无穷大，说明图不连通，直接返回全为false的数组
        if (dis[n - 1] == Long.MAX_VALUE) {
            return new boolean[edges.length];
        }
        // 从终点出发，使用BFS找到所有在最短路径上的边
        boolean[] ans = new boolean[edges.length]; // 初始化结果数组
        boolean[] vis = new boolean[n]; // 记录节点是否已被访问过
        vis[n - 1] = true; // 终点n-1已访问

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n - 1); // 终点入队
        while (!q.isEmpty()) {
            int y = q.poll(); // 取出队首节点
            // 遍历当前节点的所有入边
            for (int[] t : g[y]) {
                int x = t[0];
                int w = t[1];
                int i = t[2]; // 边的索引
                // 如果x到y的边是在最短路径上（即x到y的距离加上这条边的权重等于y到终点的最短距离）
                if (dis[x] + w == dis[y]) {
                    ans[i] = true; // 标记这条边为关键边
                    // 如果x节点未被访问过，则标记为已访问并入队
                    if (!vis[x]) {
                        vis[x] = true;
                        q.offer(x);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {3, 5, 4},
                {0, 5, 10},
                {1, 0, 2},
                {4, 0, 6},
                {2, 3, 5},
                {3, 1, 1},
                {4, 2, 2},
                {3, 0, 6},
                {5, 2, 7},
                {4, 5, 6},
                {0, 2, 9},
                {2, 1, 4}
        };

        int n = 6;
        Solution3123_2 sol = new Solution3123_2();
        System.out.println(Arrays.toString(sol.findAnswer(n, edges)));
    }
}
