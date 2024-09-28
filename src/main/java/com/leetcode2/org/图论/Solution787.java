package com.leetcode2.org.图论;

import java.util.*;

public class Solution787 {
    static class Edge {
        int src, dst, weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] pointPrices = new int[n];
        Arrays.fill(pointPrices, Integer.MAX_VALUE);
        pointPrices[src] = 0;
        Map<Integer, List<Edge>> mapEdges = new HashMap<>();
        for (int[] flight : flights) {
            Edge edge = new Edge(flight[0], flight[1], flight[2]);
            mapEdges.computeIfAbsent(flight[0], e -> new ArrayList<>()).add(edge);
        }
        List<Edge> startLine = mapEdges.get(src);
        if (startLine == null) return -1;
        for (int i = 0; i < startLine.size(); i++) {
            dfs(0, startLine.get(i), pointPrices, mapEdges, k);
        }
        return pointPrices[dst] == Integer.MAX_VALUE ? -1 : pointPrices[dst];
    }

    void dfs(int initWeight, Edge edge, int[] pointPrices, Map<Integer, List<Edge>> mapEdges, int k) {
        if (k < 0) {
            return;
        }
        pointPrices[edge.dst] = Math.min(pointPrices[edge.dst], edge.weight + initWeight);
        List<Edge> startLine = mapEdges.get(edge.dst);
        if (startLine == null) return;
        for (int i = 0; i < startLine.size(); i++) {
            dfs(edge.weight + initWeight, startLine.get(i), pointPrices, mapEdges, k - 1);
        }
    }

    public static void main(String[] args) {
        int[][] flights = {
                {4, 1, 1},
                {1, 2, 3},
                {0, 3, 2},
                {0, 4, 10},
                {3, 1, 1},
                {1, 4, 3}
        };
        int n = 5, src = 2, dst = 1, k = 1;
        Solution787 solution = new Solution787();


        System.out.println(
                solution.findCheapestPrice(n, flights, src, dst, k)
        );
    }


}

