package com.leetcode2.org.图论;

import java.util.*;
import java.util.stream.IntStream;

public class Kruskal {
    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            rank = new int[n];
            parent = IntStream.range(0, n).toArray();
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] == rank[rootY]) {
                    parent[rootX] = rootY;
                    rank[rootY]++;
                }
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootX] = rootY;
                }

            }
        }

    }

    static class Edge {
        int start, end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    int[][] generateMinSpanningTree(int[][] graph) {
        int n = graph.length;
        int m = graph[0].length;
        int[][] miniSpanningTree = new int[n][m];
        List<Edge> list = new ArrayList<Edge>();
        Set<Integer> setPoint = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < m && j >= i; j++) {
//            for (int j = 0; j < m; j++) {
                if (graph[i][j] != 0) {
                    list.add(new Edge(i, j, graph[i][j]));
                    setPoint.add(i);
                    setPoint.add(j);
                }
            }
        }
        list.sort(Comparator.comparingInt(edge -> edge.weight));
        UnionFind unionFind = new UnionFind(setPoint.size());

        for (Edge edge : list) {
            int x = edge.start;
            int y = edge.end;
            int rootX = unionFind.find(edge.start);
            int rootY = unionFind.find(edge.end);
            if (rootX != rootY) {
                unionFind.union(x, y);
                miniSpanningTree[x][y] = edge.weight;
                miniSpanningTree[y][x] = edge.weight;
            }
            System.out.println("" + x + y);
        }
        return miniSpanningTree;
    }


    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };
        Kruskal kruskal = new Kruskal();

        int[][] result = kruskal.generateMinSpanningTree(graph);
        System.out.println(Arrays.deepToString(result));

    }
}
