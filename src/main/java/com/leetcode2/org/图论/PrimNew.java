package com.leetcode2.org.图论;

import java.util.*;

public class PrimNew {
    int[][] generateMinSpanningTree(int[][] graph) {
        int n = graph.length;
        boolean[] containedTree = new boolean[n]; // 标记哪些点已经加入MST
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // 优先队列按边权排序
        queue.add(new int[]{0, 0}); // 从顶点 0 开始
        int[][] result = new int[n][n]; // 存储最小生成树的结果
        int[] parents = new int[n]; // 存储每个顶点的父节点
        Arrays.fill(parents, -1); // 初始化父节点为 -1
        parents[0] = 0; // 第一个节点的父节点是自己

        int edgesCount = 0; // 用于记录MST中边的数量

        while (!queue.isEmpty() && edgesCount < n - 1) {
            int[] cur = queue.poll(); // 取出权重最小的边
            int target = cur[0]; // 当前的顶点
            int weight = cur[1]; // 当前顶点的边权

            if (containedTree[target]) continue; // 如果顶点已经在MST中，跳过

            containedTree[target] = true; // 标记顶点已加入MST

            // 如果父节点存在，说明这条边有效，加入结果
            if (parents[target] != -1) {
                result[parents[target]][target] = weight;
                result[target][parents[target]] = weight;
                edgesCount++; // 每加入一条边就增加计数
            }

            // 遍历所有相邻节点
            for (int j = 0; j < n; j++) {
                if (graph[target][j] != 0 && !containedTree[j]) {
                    // 将未加入MST的相邻节点加入优先队列
                    queue.offer(new int[]{j, graph[target][j]});
                    parents[j] = target; // 更新相邻节点的父节点
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };
        PrimNew primNew = new PrimNew();
        int[][] result = primNew.generateMinSpanningTree(graph);
        System.out.println(Arrays.deepToString(result));
    }
}
