package com.leetcode2.org.图论;

import java.util.*;

class Solution2477_2 {
    /**
     * 计算最小燃料成本
     *
     * @param roads 道路的二维数组，表示城市之间的连接关系，其中roads[i] = [a, b] 表示城市a和城市b之间有一条道路
     * @param seats 每辆巴士的座位数
     * @return 最小燃料成本
     */
    public long minimumFuelCost(int[][] roads, int seats) {
        // 使用HashMap存储图的邻接表表示
        Map<Integer, List<Integer>> edgeLine = new HashMap<>();
        // 遍历所有道路，构建图的邻接表
        for (int[] road : roads) {
            edgeLine.computeIfAbsent(road[0], k -> new ArrayList<>()).add(road[1]);
            edgeLine.computeIfAbsent(road[1], k -> new ArrayList<>()).add(road[0]);
        }
        // personList数组用于记录每个节点（城市）到达当前节点时需要的人数（包括当前节点自身）
        int[] personList = new int[roads.length + 1];
        // visited数组用于记录DFS过程中访问过的节点
        boolean[] visited = new boolean[roads.length + 1];
        // 从节点0开始进行深度优先搜索
        return dfs(0, seats, edgeLine, personList, visited);
    }

    /**
     * 深度优先搜索函数
     *
     * @param node       当前访问的节点
     * @param seats      每辆巴士的座位数
     * @param edgeLine   图的邻接表表示
     * @param personList 每个节点到达当前节点时需要的人数
     * @param visited    记录DFS过程中访问过的节点
     * @return 从当前节点出发的燃料成本
     */
    private long dfs(int node, int seats, Map<Integer, List<Integer>> edgeLine, int[] personList, boolean[] visited) {
        visited[node] = true;
        int totalPeople = 0; // 当前节点及其子节点（未访问过的）到达当前节点时需要的人数总和
        long cost = 0; // 当前节点及其子节点的燃料成本
        // 遍历当前节点的所有邻接节点
        for (int neighbor : edgeLine.getOrDefault(node, Collections.emptyList())) {
            if (!visited[neighbor]) {
                // 递归访问邻接节点，并累加燃料成本和人数
                cost += dfs(neighbor, seats, edgeLine, personList, visited);
                totalPeople += personList[neighbor];
            }
        }
        // 当前节点的人数为子节点人数总和加1（当前节点自身）
        personList[node] = totalPeople + 1;
        // 如果不是根节点（节点0），则需要计算当前节点到根节点的燃料成本
        if (node != 0) {
            // 燃料成本为 (当前节点总人数 + seats - 1) / seats，向上取整到最近的巴士数量
            // 这里使用(totalPeople + seats) / seats即可实现向上取整的效果
            cost += (personList[node] + seats - 1) / seats;
        }
        return cost;
    }

    public static void main(String[] args) {
        int[][] roads = {
                {3, 1},
                {3, 2},
                {1, 0},
                {0, 4},
                {0, 5},
                {4, 6}
        };
        int seats = 2;
        // 计算并打印最小燃料成本
        System.out.println(new Solution2477_2().minimumFuelCost(roads, seats));
    }
}