package com.leetcode2.org.图论;

import java.util.*;

class PushRelabel {
    private int V; // 顶点数量
    private int[][] capacity; // 容量矩阵
    private int[] height; // 高度数组
    private int[] excess; // 多余流数组
    private List<Integer>[] graph; // 邻接表

    public PushRelabel(int V) {
        this.V = V;
        capacity = new int[V][V];
        height = new int[V];
        excess = new int[V];
        graph = new List[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // 添加边
    public void addEdge(int from, int to, int cap) {
        capacity[from][to] = cap;
        graph[from].add(to);
        graph[to].add(from);
    }

    // 推送操作
    private void push(int u, int v) {
        int send = Math.min(excess[u], capacity[u][v] - excess[v]);
        excess[u] -= send;
        excess[v] += send;
        capacity[u][v] -= send;
        capacity[v][u] += send;
    }

    // 重标记操作
    private void relabel(int u) {
        int minHeight = Integer.MAX_VALUE;
        for (int v : graph[u]) {
            if (capacity[u][v] > 0) {
                minHeight = Math.min(minHeight, height[v]);
            }
        }
        height[u] = minHeight + 1;
    }

    // 初始化预流
    private void initializePreflow(int source) {
        height[source] = V;
        for (int v : graph[source]) {
            excess[v] = capacity[source][v];
            excess[source] -= capacity[source][v];
            capacity[v][source] = capacity[source][v];
            capacity[source][v] = 0;
        }
    }

    // 主算法
    public int maxFlow(int source, int sink) {
        initializePreflow(source);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (i != source && i != sink && excess[i] > 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            boolean pushed = false;
            for (int v : graph[u]) {
                if (capacity[u][v] > 0 && height[u] > height[v]) {
                    push(u, v);
                    if (excess[u] == 0) {
                        pushed = true;
                        break;
                    }
                }
            }
            if (!pushed) {
                relabel(u);
                queue.add(u);
            }
        }

        return excess[sink];
    }

    public static void main(String[] args) {
        PushRelabel pr = new PushRelabel(6);
        pr.addEdge(0, 1, 16);
        pr.addEdge(0, 2, 13);
        pr.addEdge(1, 2, 10);
        pr.addEdge(1, 3, 12);
        pr.addEdge(2, 1, 4);
        pr.addEdge(2, 4, 14);
        pr.addEdge(3, 2, 9);
        pr.addEdge(3, 5, 20);
        pr.addEdge(4, 3, 7);
        pr.addEdge(4, 5, 4);

        System.out.println("The maximum possible flow is " + pr.maxFlow(0, 5));
    }
}
