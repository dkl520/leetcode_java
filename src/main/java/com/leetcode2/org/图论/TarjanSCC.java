package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 使用Tarjan算法寻找有向图中的强连通分量(SCC)
public class TarjanSCC {
    // 节点的唯一标识符，用于记录访问顺序
    private int[] ids;
    // 节点的低值，用于判断强连通分量
    private int[] low;
    // 节点是否在栈上，用于判断回边
    private boolean[] onStack;
    // DFS过程中使用的栈
    private Stack<Integer> stack;
    // 存储找到的强连通分量的列表
    private List<List<Integer>> sccs;
    // 图的邻接表表示
    private List<List<Integer>> graph;
    // 当前节点的id，用于生成唯一的访问顺序
    private int id;

    // 构造函数，初始化并计算强连通分量
    public TarjanSCC(int n, List<List<Integer>> graph) {
        this.ids = new int[n];
        this.low = new int[n];
        this.onStack = new boolean[n];
        this.stack = new Stack<>();
        this.sccs = new ArrayList<>();
        this.graph = graph;
        this.id = 0;

        // 对每个未访问的节点进行DFS
        for (int i = 0; i < n; i++) {
            if (ids[i] == 0) { // 如果节点未被访问过
                dfs(i); // 从该节点开始进行DFS
            }
        }
    }

    // 深度优先搜索函数
    private void dfs(int at) {
        stack.push(at); // 将当前节点加入栈中
        onStack[at] = true; // 标记当前节点在栈上
        ids[at] = low[at] = ++id; // 初始化当前节点的id和低值
        // 遍历当前节点的所有邻接节点
        for (int to : graph.get(at)) {
            if (ids[to] == 0) { // 如果邻接节点未被访问过
                dfs(to); // 继续DFS
                // 更新当前节点的低值
                low[at] = Math.min(low[at], low[to]);
            } else if (onStack[to]) { // 如果邻接节点在栈上，说明有回边
                // 更新当前节点的低值
                low[at] = Math.min(low[at], ids[to]);
            }
        }
        // 如果当前节点的id等于其低值，说明找到了一个强连通分量
        if (ids[at] == low[at]) {
            List<Integer> scc = new ArrayList<>();
            // 弹出栈中所有属于该强连通分量的节点
            while (true) {
                int node = stack.pop();
                onStack[node] = false; // 标记节点不在栈上
                scc.add(node); // 将节点加入强连通分量
                // 如果弹出的节点是当前节点，则完成了一个强连通分量的遍历
                if (node == at) break;
            }
            // 将找到的强连通分量加入列表
            sccs.add(scc);
        }
    }

    // 获取所有强连通分量的列表
    public List<List<Integer>> getSCCs() {
        return sccs;
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        // 创建图的邻接表表示
        int n = 9;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(0);

        graph.get(1).add(3);
        graph.get(3).add(4);

        graph.get(4).add(5);
        graph.get(5).add(6);
        graph.get(6).add(4);
        graph.get(5).add(7);
        graph.get(7).add(8);

        // 运行 Tarjan 算法
        TarjanSCC tarjan = new TarjanSCC(n, graph);
        List<List<Integer>> sccs = tarjan.getSCCs();

        // 打印强连通分量
        for (List<Integer> scc : sccs) {
            System.out.println("SCC: " + scc);
        }
    }
}
