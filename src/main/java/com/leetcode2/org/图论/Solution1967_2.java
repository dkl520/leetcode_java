package com.leetcode2.org.图论;

import java.util.*;

public class Solution1967_2 {
    // 定义一个大素数用于取模操作，防止整数溢出
    private static final int MOD = 1_000_000_007;

    /**
     * 计算从节点0到节点n-1的最短路径数量。
     *
     * @param n 节点数量
     * @param roads 边的列表，每条边由[u, v, time]表示，表示从节点u到节点v需要time的时间
     * @return 从节点0到节点n-1的最短路径数量  
     */
    public int countPaths(int n, int[][] roads) {
        // 构建邻接表
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            graph.get(u).add(new int[]{v, time}); // u到v的边
            graph.get(v).add(new int[]{u, time}); // 同时添加v到u的边，因为是无向图
        }

        // 使用 Dijkstra 算法找最短路径及路径数量
        long[] dist = new long[n]; // 存储从起点到各节点的最短路径长度
        int[] ways = new int[n]; // 存储从起点到各节点的最短路径数量
        Arrays.fill(dist, Long.MAX_VALUE); // 初始化为最大值
        dist[0] = 0; // 起点到自身的距离为0
        ways[0] = 1; // 起点到自身的路径数量为1

        // 使用优先队列（最小堆）优化查找过程
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0}); // 将起点加入队列，距离为0

        while (!pq.isEmpty()) {
            long[] cur = pq.poll(); // 取出当前距离最小的节点
            int node = (int) cur[0];
            long distance = cur[1];

            // 如果当前节点已经被处理过更短的距离，则跳过
            if (distance > dist[node]) continue;

            for (int[] neighbor : graph.get(node)) {
                int next = neighbor[0]; // 下一个节点
                long newDist = distance + neighbor[1]; // 经过当前节点到下一个节点的新距离

                // 如果找到更短的路径，则更新距离和路径数量
                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    ways[next] = ways[node]; // 更新路径数量
                    pq.offer(new long[]{next, newDist}); // 将新节点加入队列
                }
                // 如果找到相同长度的路径，则累加路径数量
                else if (newDist == dist[next]) {
                    ways[next] = (ways[next] + ways[node]) % MOD;
                }
            }
        }

        // 返回从起点到终点n-1的最短路径数量
        return ways[n - 1];
    }
}