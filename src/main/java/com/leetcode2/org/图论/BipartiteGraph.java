package com.leetcode2.org.图论;

import java.util.*;

public class BipartiteGraph {
    /**
     * 判断给定的图是否是二分图。
     *
     * @param graph 图的邻接表表示，graph[i] 包含所有与节点 i 相邻的节点。
     * @return 如果图是二分图，则返回 true；否则返回 false。
     */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length; // 图中节点的数量
        int[] colors = new int[n]; // 节点颜色数组，0: 未染色, 1: 红色, -1: 蓝色
        // 遍历图中的每个节点
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) { // 如果节点未被染色
                // 使用 BFS 进行图的遍历和染色
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i); // 将当前节点加入队列
                colors[i] = 1; // 将当前节点染成红色
                while (!queue.isEmpty()) {
                    int node = queue.poll(); // 从队列中取出一个节点
                    // 遍历当前节点的所有邻居
                    for (int neighbor : graph[node]) {
                        if (colors[neighbor] == 0) { // 如果邻居节点未被染色
                            colors[neighbor] = -colors[node]; // 将邻居染成与当前节点相反的颜色
                            queue.offer(neighbor); // 将邻居节点加入队列以便后续遍历
                        } else if (colors[neighbor] == colors[node]) { // 如果邻居节点已被染成与当前节点相同的颜色
                            return false; // 图中存在相邻且同色的节点，不是二分图
                        }
                    }
                }
            }
        }
        return true; // 图中所有节点均被成功染色，且相邻节点颜色均不同，是二分图
    }

    public static void main(String[] args) {
        // 示例图1，是二分图
        int[][] graph1 = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };

        // 示例图2，不是二分图，因为节点0和节点3相邻且同色（如果节点0是红色，则节点3也是红色）
        int[][] graph2 = {
                {1, 2, 3},
                {0, 2},
                {0, 1, 3},
                {0, 2}
        };

        // 测试结果
        System.out.println(isBipartite(graph1)); // 输出 true
        System.out.println(isBipartite(graph2)); // 输出 false
    }
}