package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgeFinding {
    private int time;
    private List<List<Integer>> graph;
    private int[] dfn;      // 深度优先遍历序号
    private int[] low;      // 最低可达节点
    private List<int[]> bridges;  // 存储桥的列表
    public List<int[]> findBridges(int n, List<List<Integer>> connections) {
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
        bridges = new ArrayList<>();
        Arrays.fill(dfn, -1);  // 未访问标记
        // 遍历所有节点
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                dfs(i, -1);
            }
        }
        return bridges;
    }
    private void dfs(int u, int parent) {
        // 记录访问顺序和初始low值
        dfn[u] = low[u] = ++time;
        // 遍历邻接点
        for (int v : graph.get(u)) {
            if (v == parent) continue;  // 跳过父节点
            if (dfn[v] == -1) {  // 未访问节点
                dfs(v, u);
                // 更新low值
                low[u] = Math.min(low[u], low[v]);
                // 判断是否是桥
                if (low[v] > dfn[u]) {
                    bridges.add(new int[]{u, v});
                }
            } else {  // 已访问节点（非父节点）
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }
}


//public static void main(String[] args) {
//    BridgeFinding solution = new BridgeFinding();
//    // 示例图：0-1-2-3，其中边(1,2)是桥
//    List<List<Integer>> connections = Arrays.asList(
//            Arrays.asList(0, 1),
//            Arrays.asList(1, 2),
//            Arrays.asList(2, 3)
//    );
//    List<int[]> result = solution.findBridges(4, connections);
//    System.out.println("图中的桥:");
//    for (int[] bridge : result) {
//        System.out.println(Arrays.toString(bridge));
//    }
//}