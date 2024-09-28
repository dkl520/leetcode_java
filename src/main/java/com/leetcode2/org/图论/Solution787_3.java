package com.leetcode2.org.图论;

import java.util.*;

public class Solution787_3 {
    static class Edge {
        int src, dst, weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 使用一个二维数组来存储从源点到每个节点在不同次数中转时的最短路径
        int[][] dp = new int[k + 2][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[0][src] = 0;
//        dp[i][j] 表示在最多经过 i 次中转的情况下，从源点到节点 j 的最短路径长度。
        // 将每个航班的信息存储到列表中
        List<Edge> edges = new ArrayList<>();
        for (int[] flight : flights) {
            edges.add(new Edge(flight[0], flight[1], flight[2]));
        }

        // 使用Bellman-Ford算法的思想进行动态规划
        for (int i = 1; i <= k + 1; i++) {
            dp[i][src] = 0;
            for (Edge edge : edges) {
                if (dp[i - 1][edge.src] != Integer.MAX_VALUE) {
                    dp[i][edge.dst] = Math.min(dp[i][edge.dst], dp[i - 1][edge.src] + edge.weight);
                }
            }
        }

//        int minPrice = Integer.MAX_VALUE;
//        for (int i = 1; i <= k + 1; i++) {
//            minPrice = Math.min(minPrice, dp[i][dst]);
//        }
        return dp[k + 1][dst] == Integer.MAX_VALUE ? -1 : dp[k + 1][dst];
//        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }

    public static void main(String[] args) {
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {1, 3, 600},
                {2, 0, 100},
                {2, 3, 200}
        };
        int n = 4, src = 0, dst = 3, k = 1;
        Solution787_3 solution = new Solution787_3();

        System.out.println(solution.findCheapestPrice(n, flights, src, dst, k));
    }
}
