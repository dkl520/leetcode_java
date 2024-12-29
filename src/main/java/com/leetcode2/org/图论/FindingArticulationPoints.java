package com.leetcode2.org.图论;

import java.util.*;

public class FindingArticulationPoints {
    private int time;
    private List<List<Integer>> graph;
    private int[] dfn;      // 深度优先遍历序号
    private int[] low;      // 最低可达节点
    private Set<Integer> cutPoints;  // 存储割点的集合
    public List<Integer> findCutPoints(int n, List<List<Integer>> connections) {
        // 构建邻接表
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> conn : connections) {
            graph.get(conn.get(0)).add(conn.get(1));
            graph.get(conn.get(1)).add(conn.get(0));
        }
        // 初始化变量
        time = 0;
        dfn = new int[n];
        low = new int[n];
        cutPoints = new HashSet<>();
        Arrays.fill(dfn, -1);  // 未访问标记
        // 遍历所有节点
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                dfs(i, -1);
            }
        }
        return new ArrayList<>(cutPoints);
    }
    private void dfs(int u, int parent) {
        // 记录访问顺序和初始low值
        dfn[u] = low[u] = ++time;
        int children = 0;  // 记录子树数量
        // 遍历邻接点
        for (int v : graph.get(u)) {
            if (v == parent) continue;  // 跳过父节点
            if (dfn[v] == -1) {  // 未访问节点
                children++;
                dfs(v, u);
                // 更新low值
                low[u] = Math.min(low[u], low[v]);
                // 割点判断条件
                // 1. 根节点：至少两个子树
                if (parent == -1 && children > 1) {
                    cutPoints.add(u);
                }
                // 2. 非根节点：子节点low值达不到祖先
                if (parent != -1 && low[v] >= dfn[u]) {
                    cutPoints.add(u);
                }
            } else {  // 已访问节点（非父节点）
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }
}


//public static void main(String[] args) {
//    FindingArticulationPoints solution = new FindingArticulationPoints();
//    // 示例图：0-1-2-3，其中节点1是割点
//    List<List<Integer>> connections = Arrays.asList(
//            Arrays.asList(0, 1),
//            Arrays.asList(1, 2),
//            Arrays.asList(2, 3)
//    );
//
//    List<Integer> result = solution.findCutPoints(4, connections);
//
//    System.out.println("图中的割点:");
//    System.out.println(result);
//}