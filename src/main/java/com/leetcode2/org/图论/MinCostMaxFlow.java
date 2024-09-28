package com.leetcode2.org.图论;

import java.util.*;

class Edge {
    int from, to, capacity, cost, flow; // 边的起点、终点、容量、费用和流量
    Edge reverseEdge; // 反向边

    public Edge(int from, int to, int capacity, int cost) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.cost = cost;
        this.flow = 0; // 初始流量为0
    }
}

public class MinCostMaxFlow {
    private int V; // 顶点数量
    private List<Edge>[] graph; // 邻接表存储图
    private int[] dist; // 存储最短路径距离
    private Edge[] parent; // 存储路径中的父边
    private boolean[] inQueue; // 标记节点是否在队列中

    public MinCostMaxFlow(int V) {
        this.V = V;
        graph = new List[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        dist = new int[V];
        parent = new Edge[V];
        inQueue = new boolean[V];
    }

    // 添加边
    public void addEdge(int from, int to, int capacity, int cost) {
        Edge forwardEdge = new Edge(from, to, capacity, cost);
        Edge reverseEdge = new Edge(to, from, 0, -cost);
        forwardEdge.reverseEdge = reverseEdge;
        reverseEdge.reverseEdge = forwardEdge;
        graph[from].add(forwardEdge);
        graph[to].add(reverseEdge);
    }

    // 使用 SPFA 算法寻找增广路径
    private boolean spfa(int source, int sink) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, null);
        Arrays.fill(inQueue, false);
        dist[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        inQueue[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            // 遍历邻接边
            for (Edge edge : graph[u]) {
                if (edge.capacity > edge.flow && dist[edge.to] > dist[u] + edge.cost) {
                    dist[edge.to] = dist[u] + edge.cost; // 更新距离
                    parent[edge.to] = edge; // 记录父边

                    if (!inQueue[edge.to]) {
                        queue.add(edge.to);
                        inQueue[edge.to] = true;
                    }
                }
            }
        }

        return dist[sink] != Integer.MAX_VALUE; // 如果到达汇点，则返回 true
    }

    // 最小费用最大流算法
    public int[] minCostMaxFlow(int source, int sink) {
        int maxFlow = 0, minCost = 0;

        while (spfa(source, sink)) { // 找到增广路径
            int pathFlow = Integer.MAX_VALUE;

            // 找到路径中的最小流量
            for (Edge edge = parent[sink]; edge != null; edge = parent[edge.from]) {
                pathFlow = Math.min(pathFlow, edge.capacity - edge.flow);
            }

            // 更新流量和费用
            for (Edge edge = parent[sink]; edge != null; edge = parent[edge.from]) {
                edge.flow += pathFlow;
                edge.reverseEdge.flow -= pathFlow;
                minCost += pathFlow * edge.cost; // 累加费用
            }

            maxFlow += pathFlow; // 增加总流量
        }

        return new int[]{maxFlow, minCost}; // 返回最大流和最小费用
    }

    public static void main(String[] args) {
        MinCostMaxFlow mcmf = new MinCostMaxFlow(6);
        mcmf.addEdge(0, 1, 10, 2);
        mcmf.addEdge(0, 2, 5, 6);
        mcmf.addEdge(1, 2, 15, 2);
        mcmf.addEdge(1, 3, 10, 1);
        mcmf.addEdge(2, 4, 10, 2);
        mcmf.addEdge(3, 4, 10, 3);
        mcmf.addEdge(3, 5, 10, 2);
        mcmf.addEdge(4, 5, 10, 1);

        int[] result = mcmf.minCostMaxFlow(0, 5);
        System.out.println("最大流: " + result[0]);
        System.out.println("最小费用: " + result[1]);
    }
}
