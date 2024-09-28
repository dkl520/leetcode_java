package com.leetcode2.org.图论;

import java.util.*;


// 连通无向图 通过并查集判断是否有环

public class GraphCycleDetectionUnionFind {
    private int V, E; // 节点和边的数量
    private Edge[] edges; // 存储所有边

    // 边的内部类
    class Edge {
        int src, dest; // 边的起点和终点
    }

    // 初始化图
    public GraphCycleDetectionUnionFind(int v, int e) {
        V = v; // 设置节点数量
        E = e; // 设置边的数量
        edges = new Edge[E]; // 初始化边数组
        for (int i = 0; i < e; ++i) {
            edges[i] = new Edge(); // 创建每条边
        }
    }

    // 查找集合中的根节点
    int find(int parent[], int i) {
        if (parent[i] == -1) {
            return i; // 如果i是根节点，则返回i
        }
        return find(parent, parent[i]); // 递归查找根节点
    }

    // 合并两个集合
    void union(int parent[], int x, int y) {
        int xset = find(parent, x); // 找到x的根节点
        int yset = find(parent, y); // 找到y的根节点
        parent[xset] = yset; // 将x的根节点指向y的根节点，实现合并
    }

    // 判断图中是否有环
    public boolean isCyclic() {
        int[] parent = new int[V]; // 创建并初始化并查集
        Arrays.fill(parent, -1); // 初始时所有节点都是根节点，-1表示

        for (int i = 0; i < E; ++i) {
            int x = find(parent, edges[i].src); // 找到当前边起点的根节点
            int y = find(parent, edges[i].dest); // 找到当前边终点的根节点

            if (x == y) {
                return true; // 如果起点和终点在同一集合中，说明存在环
            }
            union(parent, x, y); // 否则合并这两个节点的集合
        }
        return false; // 遍历所有边后未发现环，则返回false
    }

    public static void main(String[] args) {
        int V = 7, E = 6; // 定义图的节点和边的数量
        GraphCycleDetectionUnionFind graph = new GraphCycleDetectionUnionFind(V, E); // 创建图实例
        int[][] edges = {
                {1, 2},
                {2, 4},
                {4, 3},
                {3, 1},
                {0, 1},
                {5, 2},
                {6, 5}
        };
        for (int i = 0; i < edges.length ; i++) {
            int [] edge= edges[i];
            graph.edges[i].src= edge[0];
            graph.edges[i].dest= edge[1];
        }
        // 添加边
//        graph.edges[0].src = 0;
//        graph.edges[0].dest = 1;
//
//        graph.edges[1].src = 1;
//        graph.edges[1].dest = 2;
//
//        graph.edges[2].src = 0;
//        graph.edges[2].dest = 2;

        // 检测是否存在环
        if (graph.isCyclic()) {
            System.out.println("图中存在环");
        } else {
            System.out.println("图中不存在环");
        }
    }
}
