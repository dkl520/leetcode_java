package com.leetcode2.org.状态压缩;

//import java.util.Queue;

import java.util.*;


public  class Solution847 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int targetMask = (1 << n) - 1;
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            q.offer(new int[]{i, 1 << i, 0});
            String str = Integer.toString(i) + Integer.toString(1 << i);
            visited.add(str);
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int i = point[0];
            int mask = point[1];
            int dist = point[2];
            if (mask == targetMask) {
                return dist;
            }
            for (int neighbor : graph[i]) {
                int newMask = mask | (1 << neighbor);
                String key = Integer.toString(neighbor) + Integer.toString(newMask);
                if (!visited.contains(key)) {
                    q.offer(new int[]{neighbor, newMask, dist + 1});
                    visited.add(key);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {1},
                {0, 2, 6},
                {1, 3},
                {2},
                {5},
                {4, 6},
                {1, 5, 7},
                {6}
        };
        System.out.println(new Solution847().shortestPathLength(graph));

    }
}