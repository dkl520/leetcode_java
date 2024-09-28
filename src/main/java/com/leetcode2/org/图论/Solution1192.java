package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1192 {
    // 图的邻接表表示
    private List<List<Integer>> graph;
    // 存储关键连接的列表
    private List<List<Integer>> result;
    // 记录每个节点的发现时间
    private int[] discoveryTime;
    // 记录每个节点通过DFS能回溯到的最早节点的时间戳
    private int[] low;
    // DFS的时间戳
    private int time;
    // 记录节点是否被访问过
    private boolean[] visited;

    /**
     * 找到图中的关键连接
     *
     * @param n           节点数
     * @param connections 边的列表，每条边由两个节点组成
     * @return 关键连接的列表
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 初始化图、结果列表、时间戳数组、访问标记数组等
        graph = new ArrayList<>();
        result = new ArrayList<>();
        discoveryTime = new int[n];
        low = new int[n];
        visited = new boolean[n];
        time = 0;
        // 初始化图的邻接表表示
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 根据输入的边构建图的邻接表
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int b = connection.get(1); // 注意：原代码中变量名为b，这里可能是个笔误，通常我们会用v
            graph.get(u).add(b);
            graph.get(b).add(u); // 因为是无向图，所以要双向添加
        }
        // 从第一个节点开始进行DFS
        dfs(0, -1); // 从节点0开始，父节点设为-1（表示没有父节点）
        return result;
    }

    /**
     * 深度优先搜索
     *
     * @param u      当前节点
     * @param parent 父节点
     */
    private void dfs(int u, int parent) {
        visited[u] = true; // 标记当前节点为已访问
        discoveryTime[u] = low[u] = ++time; // 初始化发现时间和最低时间戳
        // 遍历当前节点的所有邻接节点
        for (int v : graph.get(u)) {
            if (v == parent) {
                continue; // 如果是父节点，则跳过
            }
            if (!visited[v]) {
                // 如果邻接节点未被访问，则递归访问
                dfs(v, u);
                // 更新当前节点的最低时间戳
                low[u] = Math.min(low[u], low[v]);
                // 判断当前边是否是关键连接
                if (low[v] > discoveryTime[u]) {
                    result.add(Arrays.asList(u, v)); // 如果是，则添加到结果列表中
                }
            } else {
                // 如果邻接节点已被访问过（即存在回边），则更新当前节点的最低时间戳
                low[u] = Math.min(low[u], discoveryTime[v]);
            }
        }
    }
    public static void main(String[] args) {
        Solution1192 cc = new Solution1192();
        int n1 = 4;
        List<List<Integer>> connections1 = new ArrayList<>();
        connections1.add(Arrays.asList(0, 1));
        connections1.add(Arrays.asList(1, 2));
        connections1.add(Arrays.asList(2, 0));
        connections1.add(Arrays.asList(1, 3));
        System.out.println(cc.criticalConnections(n1, connections1));
        int n2 = 2;
        List<List<Integer>> connections2 = new ArrayList<>();
        connections2.add(Arrays.asList(0, 1));
        System.out.println(cc.criticalConnections(n2, connections2));
    }
}


