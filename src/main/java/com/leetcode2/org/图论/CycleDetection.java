package com.leetcode2.org.图论;

import java.util.*;

class UndirectedGraph {
    private final int V; // 顶点数
    private final List<List<Integer>> adj; // 邻接表

    public UndirectedGraph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }
    // 添加边
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }
    // 判断是否有环
    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        // 检查所有未访问的顶点
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (hasCycleUtil(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean hasCycleUtil(int v, boolean[] visited, int parent) {
        visited[v] = true;
        // 遍历所有相邻顶点
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                if (hasCycleUtil(neighbor, visited, v)) {
                    return true;
                }
            } else if (neighbor != parent) {
                // 如果相邻顶点已访问且不是父节点，说明存在环
                return true;
            }
        }
        return false;
    }
}

public class CycleDetection {
    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
//         g.addEdge(4, 1); // 取消注释这行将创建一个环
        if (g.hasCycle()) {
            System.out.println("图中存在环");
        } else {
            System.out.println("图中不存在环");
        }
    }
}