package com.leetcode2.org.图论;

import java.util.*;
import java.io.*;
//最大流算法
public class FordFulkerson {
    private int V; // 顶点数量
    private int[][] capacity; // 容量矩阵
    private int[] parent; // 路径中的父节点
    private boolean[] visited; // 标记节点是否被访问

    public FordFulkerson(int V) {
        this.V = V;
        capacity = new int[V][V];
        parent = new int[V];
        visited = new boolean[V];
    }

    // 添加边
    public void addEdge(int u, int v, int cap) {
        capacity[u][v] = cap;
    }

    // DFS 找到增广路径
    private boolean dfs(int s, int t) {
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && capacity[u][v] > 0) {
                    stack.push(v);
                    visited[v] = true;
                    parent[v] = u;

                    if (v == t) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Ford-Fulkerson 主算法
    public int maxFlow(int s, int t) {
        int maxFlow = 0;

        while (dfs(s, t)) {
            // 找到当前增广路径中的最小容量
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }

            // 更新容量
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }

            // 增加总流量
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        FordFulkerson ff = new FordFulkerson(6);
        ff.addEdge(0, 1, 16);
        ff.addEdge(0, 2, 13);
        ff.addEdge(1, 2, 10);
        ff.addEdge(1, 3, 12);
        ff.addEdge(2, 1, 4);
        ff.addEdge(2, 4, 14);
        ff.addEdge(3, 2, 9);
        ff.addEdge(3, 5, 20);
        ff.addEdge(4, 3, 7);
        ff.addEdge(4, 5, 4);

        System.out.println("The maximum possible flow is " + ff.maxFlow(0, 5));
    }
}
