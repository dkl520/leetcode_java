package com.leetcode2.org.图论;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution1135 {
    static record Edge(int start, int end, int weight) {
    }

    static class UnionFind {
        int[] parent;
        int num;

        public UnionFind(int n) {
            this.num = n;
            parent = new int[n + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public void union(int i, int j) {
            parent[find(i)] = parent[find(j)];
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

    public int minimumCost(int n, int[][] connections) {
        Queue<Edge> queue = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (int[] connect : connections) {
            queue.add(new Edge(connect[0], connect[1], connect[2]));
        }
        int cost = 0;
        UnionFind uf = new UnionFind(n);
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (uf.find(edge.start) != uf.find(edge.end)) {
                cost += edge.weight;
                uf.union(edge.start, edge.end);
            }
        }
        int origin = uf.parent[1];
        for (int i = 1; i <=n ; i++) {
            if(origin != uf.find(i)) {
                return -1;
            }
        }

        return cost;
    }
}
