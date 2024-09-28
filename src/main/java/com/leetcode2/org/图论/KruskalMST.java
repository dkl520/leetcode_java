package com.leetcode2.org.图论;

import java.util.*;

public class KruskalMST {

    // 使用边的类表示图
    static class Edge implements
            Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    private final int vertices;
    private final List<Edge> edges;

    public KruskalMST(int v) {
        vertices = v;
        edges = new ArrayList<>();
    }

    // 添加边
    public void addEdge(int u, int v, int weight) {
        edges.add(new Edge(u, v, weight));
    }

    // 查找顶点的根节点
    private int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]); // 路径压缩
        return parent[i];
    }

    // 合并两个子集
    private void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // 找到最小生成树并打印
    public void kruskalMST() {
        // 存储最小生成树的边
        List<Edge> result = new ArrayList<>();

        // 根据权重对边进行排序
        Collections.sort(edges);

        // 创建一个保存子集的数组
        int[] parent = new int[vertices];
        int[] rank = new int[vertices];

        // 初始化每个顶点为单独的子集
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // 遍历每一条边，如果没有形成环，则加入最小生成树中
        for (Edge edge : edges) {
            int rootX = find(parent, edge.src);
            int rootY = find(parent, edge.dest);

            if (rootX != rootY) {
                result.add(edge);
                union(parent, rank, rootX, rootY);
            }
        }

        // 打印最小生成树的边
        System.out.println("Edge \tWeight");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + "\t" + edge.weight);
        }
    }

    public static void main(String[] args) {
        KruskalMST g = new KruskalMST(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.kruskalMST();
    }
}
