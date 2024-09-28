package com.leetcode2.org.图论;

import java.util.*;

public class Solution1967 {
    static class Graph {
        int start;
        int end;
        long weight;

        public Graph(int start, int end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public int countPaths(int n, int[][] roads) {
        // 使用邻接表表示图
        Map<Integer, List<Graph>> map = new HashMap<>();
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            map.computeIfAbsent(u, k -> new ArrayList<>()).add(new Graph(u, v, w));
            map.computeIfAbsent(v, k -> new ArrayList<>()).add(new Graph(v, u, w));
        }

        long[] dist = new long[n];
        int[] count = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        count[0] = 1;

        PriorityQueue<Graph> pq = new PriorityQueue<>(Comparator.comparingLong(g -> g.weight));
        pq.offer(new Graph(-1, 0, 0)); // 启动虚拟源节点

        while (!pq.isEmpty()) {
            Graph graph = pq.poll();
            int u = graph.end;

            // 更新邻接节点的最短路径
            if (graph.weight > dist[u]) continue;

            for (Graph edge : map.getOrDefault(u, Collections.emptyList())) {
                int v = edge.end;
                long weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = (long) (dist[u] + weight);
                    count[v] = count[u];
                    pq.offer(new Graph(u, v, dist[v]));
                } else if (dist[u] + weight == dist[v]) {
                    count[v] += count[u];
                }
            }
        }

        return count[n - 1];
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] roads = {
                {0, 6, 7},
                {0, 1, 2},
                {1, 2, 3},
                {1, 3, 3},
                {6, 3, 3},
                {3, 5, 1},
                {6, 5, 1},
                {2, 5, 1},
                {0, 4, 5},
                {4, 6, 2}
        };
        Solution1967 solution1967 = new Solution1967();
        System.out.println(solution1967.countPaths(n, roads));
    }
}
