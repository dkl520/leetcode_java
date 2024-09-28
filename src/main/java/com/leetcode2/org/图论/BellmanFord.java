package com.leetcode2.org.图论;

import java.util.Arrays;

public class BellmanFord {

    // 用于表示图中边的类
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Bellman-Ford算法的实现方法
    static void bellmanFord(Edge[] edges, int V, int E, int src) {
        // 步骤1：初始化从src到所有其他顶点的距离为无穷大
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // 步骤2：放松所有边|V| - 1次
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // 步骤3：检查负权重环
        // 上一步保证如果图中不包含负权重环，则得到的距离是最短的
        // 如果我们还能得到更短的路径，则说明存在一个负权重环
        for (int j = 0; j < E; ++j) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("图中包含负权重环");
                return;
            }
        }

        printArr(dist, V);
    }

    // 打印距离数组的辅助函数
    static void printArr(int[] dist, int V) {
        System.out.println("顶点\t距离从源点");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }

    // 主方法用于测试上述函数
    public static void main(String[] args) {
        int V = 5; // 图中的顶点数量
        int E = 8; // 图中的边数量

        Edge[] edges = new Edge[E];

        // 添加边
        edges[0] = new Edge(0, 1, -1);
        edges[1] = new Edge(0, 2, 4);
        edges[2] = new Edge(1, 2, 3);
        edges[3] = new Edge(1, 3, 2);
        edges[4] = new Edge(1, 4, 2);
        edges[5] = new Edge(3, 2, 5);
        edges[6] = new Edge(3, 1, 1);
        edges[7] = new Edge(4, 3, -3);

        bellmanFord(edges, V, E, 0);
    }
}
