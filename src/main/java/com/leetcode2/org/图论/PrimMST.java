package com.leetcode2.org.图论;

import java.util.*;

public class PrimMST {

    // 使用邻接矩阵表示图
    private static int[][] graph;
    private static int vertices;

    public PrimMST(int v) {
        vertices = v;
        graph = new int[v][v];
    }

    // 添加边
    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    // 找到最小生成树并打印
    public void primMST() {
        // 存储最小生成树的边
        int[] parent = new int[vertices];
        // 存储每个顶点到最小生成树的最小权重边
        int[] key = new int[vertices];
        // 记录顶点是否被包含在最小生成树中
        boolean[] mstSet = new boolean[vertices];

        // 初始化所有顶点的 key 值为无穷大，表示初始时没有顶点被包含在最小生成树中
        Arrays.fill(key, Integer.MAX_VALUE);
        // 从第一个顶点开始构建最小生成树
        key[0] = 0;
        parent[0] = -1;  // 第一个顶点作为根节点

        for (int count = 0; count < vertices - 1; count++) {
            // 选择 key 值最小的顶点，且该顶点未被包含在最小生成树中
            int u = minKey(key, mstSet);
            // 将选定的顶点加入到最小生成树中
            mstSet[u] = true;

            // 更新与 u 相邻的顶点的 key 值和 parent
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                    System.out.println(key[v]);
                }
            }

        }

        // 打印最小生成树的边
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    // 辅助方法：找到 key 值最小的顶点
    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        PrimMST g = new PrimMST(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.primMST();
    }
}
