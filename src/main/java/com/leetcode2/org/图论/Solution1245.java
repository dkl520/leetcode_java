package com.leetcode2.org.图论;
import java.util.*;
public class Solution1245 {
    int maxDiameter = 0;
    public int treeDiameter(int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < edges.length + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(graph, 0, -1);
        return maxDiameter;
    }
    int dfs(List<List<Integer>> graph, int start, int parent) {
        int firstMax = 0;
        int secondMax = 0;
        for (int neighbor : graph.get(start)) {
            if (neighbor != parent) {
                int curDiameter = dfs(graph, neighbor, start);
                if (firstMax < curDiameter) {
                    secondMax = firstMax;
                    firstMax = curDiameter;
                } else if (secondMax < curDiameter) {
                    secondMax = curDiameter;
                }
            }
        }
        maxDiameter = Math.max(maxDiameter, secondMax + firstMax);
        return firstMax + 1;
    }
}
