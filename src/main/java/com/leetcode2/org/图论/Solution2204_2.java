package com.leetcode2.org.图论;

import java.util.*;

public class Solution2204_2 {

    public static void main(String[] args) {
        int n1 = 7;
        int[][] edges1 = {{1, 2}, {2, 4}, {4, 3}, {3, 1}, {0, 1}, {5, 2}, {6, 5}};
        System.out.println(Arrays.toString(distanceToCycle(n1, edges1)));  // Output: [1, 0, 0, 0, 0, 1, 2]

        int n2 = 9;
        int[][] edges2 = {{0, 1}, {1, 2}, {0, 2}, {2, 6}, {6, 7}, {6, 8}, {0, 3}, {3, 4}, {3, 5}};
        System.out.println(Arrays.toString(distanceToCycle(n2, edges2)));  // Output: [0, 0, 0, 1, 2, 2, 1, 2, 2]


        int n3 = 15;
        int[][] edges3 = {
                {0, 1}, {1, 2}, {0, 2}, {2, 6}, {6, 7}, {6, 8}, {0, 3}, {3, 4}, {3, 5},
                {1, 9}, {9, 10}, {10, 11}, {11, 1}, {2, 5}, {5, 7}, {8, 4}, {4, 9}, {7, 11},
                {12, 13}, {13, 14}, {14, 12}, {0, 11}, {1, 14}, {7, 10}, {3, 12}, {5, 8},
                {9, 13}, {6, 10}, {2, 14}, {4, 7}
        };

        long startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(distanceToCycle(n3, edges3)));
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }

    /**
     * 计算每个节点到最近环节点的距离
     *
     * @param n 图中的节点数
     * @param edges 图中的边
     * @return 每个节点到最近环节点的距离数组
     */
    public static int[] distanceToCycle(int n, int[][] edges) {
        // 构建图的邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 查找图中的环节点
        Set<Integer> cycleNodes = findCycle(n, graph);
        return bfsToCalculateDistances(n, graph, cycleNodes);
    }

    /**
     * 使用DFS查找图中的环节点
     *
     * @param n 图中的节点数
     * @param graph 图的邻接表
     * @return 环节点的集合
     */
    private static Set<Integer> findCycle(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] cycle = new int[]{-1, -1};
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dfs(i, visited, parent, graph, cycle)) {
                break;
            }
        }
        Set<Integer> cycleNodes = new HashSet<>();
        cycleNodes.add(cycle[0]);
        for (int v = cycle[1]; v != cycle[0]; v = parent[v]) {
            cycleNodes.add(v);
        }
        cycleNodes.add(cycle[0]);
        return cycleNodes;
    }

    /**
     * 深度优先搜索用于检测图中的环
     *
     * @param v 当前节点
     * @param visited 访问状态数组
     * @param parent 父节点数组
     * @param graph 图的邻接表
     * @param cycle 用于存储环的起始和结束节点
     * @return 是否找到环
     */
    private static boolean dfs(int v, boolean[] visited, int[] parent, List<List<Integer>> graph, int[] cycle) {
        visited[v] = true;
        for (int neighbor : graph.get(v)) {
            if (neighbor == parent[v]) {
                continue;
            }
            if (visited[neighbor]) {
                cycle[0] = neighbor;
                cycle[1] = v;
                return true;
            }
            parent[neighbor] = v;
            if (dfs(neighbor, visited, parent, graph, cycle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用广度优先搜索计算每个节点到最近环节点的距离
     *
     * @param n 图中的节点数
     * @param graph 图的邻接表
     * @param cycleNodes 环节点的集合
     * @return 每个节点到最近环节点的距离数组
     */
    private static int[] bfsToCalculateDistances(int n, List<List<Integer>> graph, Set<Integer> cycleNodes) {
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int node : cycleNodes) {
            distances[node] = 0;
            queue.offer(node);
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentDistance = distances[current];
            for (int neighbor : graph.get(current)) {
                if (distances[neighbor] == -1) {
                    distances[neighbor] = currentDistance + 1;
                    queue.offer(neighbor);
                }
            }
        }

        return distances;
    }
}
