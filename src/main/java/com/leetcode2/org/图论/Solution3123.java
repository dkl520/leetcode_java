package com.leetcode2.org.图论;

import java.util.*;

public class Solution3123 {
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public boolean[] findAnswer(int n, int[][] edges) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        boolean[] answer = new boolean[edges.length];
        Set<Edge>[] lines = new Set[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            lines[i] = new HashSet<>();
//            lines[i] = new ArrayList<>();
        }
        // 构建图
        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[0], edge[1], edge[2]));
            graph[edge[1]].add(new Edge(edge[1], edge[0], edge[2]));
        }
        dijkstra(graph, lines, 0, n);
        Set<Edge> resultEdges = lines[n - 1];
        for (int i = 0; i < edges.length; i++) {
            int[] edgeArr = edges[i];
            boolean curBol = resultEdges.stream().anyMatch(edge ->
                    (edge.src == edgeArr[0] && edge.dest == edgeArr[1])
                    || (edge.src == edgeArr[1] && edge.dest == edgeArr[0])
            );
            answer[i] = curBol;
        }
        return answer;
    }

    public long[] dijkstra(List<Edge>[] graph,  Set<Edge>[] lines, int source, int n) {

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        // 将源节点及其到自身的距离（0）加入优先队列
        pq.offer(new long[]{source, 0});
        boolean[] visited = new boolean[n];
        // 初始化距离数组，所有节点的距离初始化为正无穷大
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        // 源节点到自身的距离是0
        dist[source] = 0;
        while (!pq.isEmpty()) {
            // 从优先队列中取出当前距离最小的节点
            long[] curr = pq.poll();
            int u = (int) curr[0]; // 当前节点的索引
            visited[u] = true;
            long d = curr[1]; // 当前节点到源节点的最短路径长度
            // 如果当前节点的距离已经大于已知的最短距离，则跳过此节点
            if (d > dist[u]) continue;
            // 遍历当前节点的所有邻接边
            for (Edge edge : graph[u]) {
                int v = edge.dest; // 邻接节点的索引
                if (visited[v]) {
                    continue;
                }
                long newDist = d + edge.weight; // 计算经过当前节点到达邻接节点的新的最短路径长度
                // 如果新的最短路径长度小于已知的最短路径长度，则更新距离
                if (newDist == dist[v]) {
                    Set<Edge> line = new HashSet<>(lines[u]);
                    line.add(edge);
                    lines[v].addAll(line);
                }
                if (newDist < dist[v]) {
                    Set<Edge> line = new HashSet<>(lines[u]);
                    line.add(edge);
                    lines[v] = line;

                    dist[v] = newDist;
                    pq.offer(new long[]{v, newDist});
                }

            }
        }
        return dist;
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
        Solution3123 sol = new Solution3123();
        System.out.println(Arrays.toString(sol.findAnswer(n, edges)));
    }
}
