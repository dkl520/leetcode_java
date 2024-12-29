package com.leetcode2.org.图论;

import java.util.*;

public class FindSCCMatrix {
    private int[] dfn;         // 节点的访问时间戳
    private int[] low;         // 能追溯到的最早节点的时间戳
    private boolean[] inStack; // 节点是否在栈中
    private Stack<Integer> stack;
    private List<List<Integer>> components;
    private int time;

    public List<List<Integer>> findSCC(int[][] graph) {
        int n = graph.length;
        dfn = new int[n];
        low = new int[n];
        inStack = new boolean[n];
        stack = new Stack<>();
        components = new ArrayList<>();
        time = 0;
        Arrays.fill(dfn, -1);

        // 遍历每个节点，确保所有连通块都被处理
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                tarjan(i, graph);
            }
        }
        return components;
    }

    private void tarjan(int u, int[][] graph) {
        dfn[u] = low[u] = ++time;
        stack.push(u);
        inStack[u] = true;

        // 遍历当前节点的邻接节点
        for (int v = 0; v < graph.length; v++) {
            // 如果存在边且目标节点未被访问
            if (graph[u][v] == 1 && dfn[v] == -1) {
                tarjan(v, graph);
                low[u] = Math.min(low[u], low[v]);
            } else if (graph[u][v] == 1 && inStack[v]) {
                // 如果目标节点在栈中
                low[u] = Math.min(low[u], dfn[v]);
            }
        }

        // 找到强连通分量的根节点
        if (low[u] == dfn[u]) {
            List<Integer> component = new ArrayList<>();
            int v;
            do {
                v = stack.pop();
                inStack[v] = false;
                component.add(v);
            } while (v != u);
            components.add(component);
        }
    }

    public static void main(String[] args) {
        FindSCCMatrix solution = new FindSCCMatrix();

        // 使用邻接矩阵表示图
        // 1表示存在边，0表示不存在边
        int[][] graph = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0}, // 0 -> 1
                {0, 0, 0, 1, 1, 0}, // 1 -> 2
                {0, 1, 0, 0, 0, 0}, // 2 -> 3, 4
                {0, 0, 0, 0, 0, 1}, // 3 -> 1
                {0, 0, 0, 0, 0, 0}, // 4 -> 5
        };

        // 调用findSCC方法
        List<List<Integer>> result = solution.findSCC(graph);

        // 打印强连通分量
        System.out.println("强连通分量:");
        result.forEach(System.out::println);
    }
}