package com.leetcode2.org.图论;

import java.util.*;

public class Solution1168 {

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] pipe : pipes) {
            graph.computeIfAbsent(pipe[0], k -> new ArrayList<>()).add(new int[]{pipe[1], pipe[2]});
            graph.computeIfAbsent(pipe[1], k -> new ArrayList<>()).add(new int[]{pipe[0], pipe[2]});
        }
        Queue<int[]> zeroLit = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < wells.length; i++) {
            int well = wells[i];
            zeroLit.offer(new int[]{i + 1, well});
        }
        int[] dist = new int[n + 1];
        int[] visited = new int[n + 1];
        visited[0] = 1;
        System.arraycopy(wells, 0, dist, 1, wells.length);
        dist[0] = 0;
        while (!zeroLit.isEmpty()) {
            int[] well = zeroLit.poll();
            visited[well[0]] = 1;
            if (graph.get(well[0]) == null) {
                continue;
            }
            for (int[] next : graph.get(well[0])) {
                if (visited[next[0]] == 1) continue;
                if (next[1] < dist[next[0]]) {
                    dist[next[0]] = next[1];
                    zeroLit.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }
        return Arrays.stream(dist).sum();
    }

    public static void main(String[] args) {
        int n = 5; // 节点数量
        int[] wells = {46012, 72474, 64965, 751, 33304}; // 水井的成本
        int[][] pipes = {
                {2, 1, 6719},
                {3, 2, 75312},
                {5, 3, 44918}
        }; // 管道连接及其成本
        Solution1168 solution1168 = new Solution1168();
        solution1168.minCostToSupplyWater(n, wells, pipes);


    }


}
