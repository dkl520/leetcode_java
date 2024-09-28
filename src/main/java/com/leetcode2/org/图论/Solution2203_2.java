package com.leetcode2.org.图论;

import java.util.*;

public class Solution2203_2 {
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // 计算从两个起点到终点的最小路径权重
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        @SuppressWarnings("unchecked")
        List<Edge>[] graph = (List<Edge>[]) new  ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // 构建图
        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[0], edge[1], edge[2]));
        }

        // 计算从 src1 到各个节点的最短路径
        long[] distFromSrc1 = dijkstra(graph, n, src1);
        // 计算从 src2 到各个节点的最短路径
        long[] distFromSrc2 = dijkstra(graph, n, src2);
        // 计算从 dest 到各个节点的最短路径（反向图）
        long[] distToDest = dijkstra(reverseGraph(graph, n), n, dest);

        long minDist = Long.MAX_VALUE;

//        代码逻辑是寻找一个点到其他三个点的距离之和最小 。该点可以是三个点中的一个。。。
        for (int i = 0; i < n; i++) {
            if (distFromSrc1[i] != Long.MAX_VALUE && distFromSrc2[i] != Long.MAX_VALUE && distToDest[i] != Long.MAX_VALUE) {
                minDist = Math.min(minDist, distFromSrc1[i] + distFromSrc2[i] + distToDest[i]);
            }
        }
        return minDist == Long.MAX_VALUE ? -1 : minDist;
    }

    // 实现 Dijkstra 算法来计算最短路径
    // 使用Dijkstra算法计算从源节点src到图中所有其他节点的最短路径
    private long[] dijkstra(List<Edge>[] graph, int n, int src) {
        // 创建一个优先队列，用于存储待处理的节点及其当前最短路径长度
        // 使用lambda表达式作为Comparator来根据路径长度进行排序
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        // 将源节点及其到自身的距离（0）加入优先队列
        pq.offer(new long[]{src, 0});

        // 初始化距离数组，所有节点的距离初始化为正无穷大
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        // 源节点到自身的距离是0
        dist[src] = 0;

        // 当优先队列不为空时，继续执行
        while (!pq.isEmpty()) {
            // 从优先队列中取出当前距离最小的节点
            long[] curr = pq.poll();
            int u = (int) curr[0]; // 当前节点的索引
            long d = curr[1]; // 当前节点到源节点的最短路径长度
            // 如果当前节点的距离已经大于已知的最短距离，则跳过此节点
            if (d > dist[u]) continue;
            // 遍历当前节点的所有邻接边
            for (Edge edge : graph[u]) {
                int v = edge.dest; // 邻接节点的索引
                long newDist = d + edge.weight; // 计算经过当前节点到达邻接节点的新的最短路径长度
                // 如果新的最短路径长度小于已知的最短路径长度，则更新距离
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    // 将邻接节点及其新的最短路径长度加入优先队列
                    pq.offer(new long[]{v, newDist});
                }
            }
        }

        // 返回距离数组，其中dist[i]表示从源节点到节点i的最短路径长度
        return dist;
    }

    // 创建反向图，用于计算从目标节点到其他节点的最短路径
    private List<Edge>[] reverseGraph(List<Edge>[] graph, int n) {
        @SuppressWarnings("unchecked")
        List<Edge>[] reversedGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            reversedGraph[i] = new ArrayList<>();
        }
        for (int u = 0; u < n; u++) {
            for (Edge edge : graph[u]) {
                reversedGraph[edge.dest].add(new Edge(edge.dest, edge.src, edge.weight));
            }
        }
        return reversedGraph;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 2, 2},
                {0, 5, 6},
                {1, 0, 3},
                {1, 4, 5},
                {2, 1, 1},
                {2, 3, 3},
                {2, 3, 4},
                {3, 4, 2},
                {4, 5, 1}
        };
        int n = 6;
        int src1 = 0, src2 = 1, dest = 5;
        Solution2203_2 sol = new Solution2203_2();
        System.out.println(sol.minimumWeight(n, edges, src1, src2, dest));
    }
}
