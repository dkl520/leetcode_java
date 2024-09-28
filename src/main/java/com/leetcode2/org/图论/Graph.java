package com.leetcode2.org.图论;

import java.util.*;

// 图类，用于存储图并执行查找桥的操作
public class Graph {
    private int V; // 图的顶点数
    private List<List<Integer>> adj; // 邻接表，存储图的边
    private int[] low, disc, parent; // 分别用于存储每个顶点的low值、发现时间和父节点
    private boolean[] visited; // 标记顶点是否被访问过
    private List<int[]> bridges; // 存储桥（边）的列表
    private int time; // DFS的时间戳

    // 构造函数，初始化图的顶点数和相关数据结构
    Graph(int v) {
        V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        low = new int[V];
        disc = new int[V];
        parent = new int[V];
        visited = new boolean[V];
        bridges = new ArrayList<>();
    }

    // 添加无向边
    void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    // 查找并存储图中的所有桥
    void findBridges() {
        // 初始化数据结构
        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);
        time = 0;
        // 对每个未访问的顶点调用DFS
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    // DFS函数，用于遍历图并标记桥
    private void dfs(int u) {
        visited[u] = true; // 标记当前顶点为已访问
        disc[u] = low[u] = ++time; // 设置发现时间和low值

        for (int v : adj.get(u)) { // 遍历当前顶点的所有邻接点
            if (!visited[v]) { // 如果邻接点未被访问
                parent[v] = u; // 设置父节点
                dfs(v); // 递归访问邻接点

                // 更新当前顶点的low值
                low[u] = Math.min(low[u], low[v]);

                // 如果邻接点的low值大于当前顶点的发现时间，则当前边是桥
                if (low[v] > disc[u]) {
                    bridges.add(new int[]{u, v}); // 将桥添加到列表中
                }
            } else if (v != parent[u]) { // 如果邻接点已被访问且不是当前顶点的父节点（考虑反向边）
                // 更新当前顶点的low值
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // 获取图中所有桥的列表
    List<int[]> getBridges() {
        return bridges;
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 3);

        g.findBridges();
        // 输出找到的桥，可以根据需要添加输出代码
        for (int[] bridge : g.getBridges()) {
            System.out.println("(" + bridge[0] + ", " + bridge[1] + ")");
        }
    }
}