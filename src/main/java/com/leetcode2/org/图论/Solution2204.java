package com.leetcode2.org.图论;

import java.util.*;

public class Solution2204 {
    public int[] distanceToCycle(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        Map<Integer, Set<Integer>> mapList = new HashMap<>();
        for (int[] edge : edges) {
            mapList.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            mapList.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }
        List<Integer> cycleList = new ArrayList<>();

        dfs(0, -1, mapList, new ArrayList<>(Arrays.asList(0)), visited, cycleList);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = calcDistance(i, -1, mapList, cycleList, 0);
        }

        return res;
    }


    private int calcDistance(int node, int parent, Map<Integer, Set<Integer>> mapList, List<Integer> cycleList, int steps) {
        if (cycleList.contains(node)) return steps;
        Set<Integer> neighbors = mapList.get(node);

        for (Integer neighbor : neighbors) {
            if (neighbor == parent) {
                continue;
            }
            if (cycleList.contains(neighbor)) {
                return steps + 1;
            }
            return calcDistance(neighbor, node, mapList, cycleList, steps + 1);

        }
        return -1;
    }


    private int dfs(int node, int parent, Map<Integer, Set<Integer>> mapList, List<Integer> cycleList, boolean[] visited, List<Integer> cycles) {

        visited[node] = true;

        Set<Integer> neighbors = mapList.get(node);
        for (Integer x : neighbors) {
            if (x == parent) {
                continue;
            }
            if (visited[x]) {
                int index = cycleList.indexOf(x);
                cycles.addAll(cycleList.subList(index, cycleList.size()));
                return 1;
            }
            cycleList.add(x);
            int result = dfs(x, node, mapList, cycleList, visited, cycles);
            if (result == 1) {
                return result;
            }
            cycleList.remove(x);
        }
        return -1;

    }


    public static void main(String[] args) {
        int n = 9;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {0, 2},
                {2, 6},
                {6, 7},
                {6, 8},
                {0, 3},
                {3, 4},
                {3, 5}
        };
        int n3 = 15;
        int[][] edges3 = {
                {0, 1}, {1, 2}, {0, 2}, {2, 6}, {6, 7}, {6, 8}, {0, 3}, {3, 4}, {3, 5},
                {1, 9}, {9, 10}, {10, 11}, {11, 1}, {2, 5}, {5, 7}, {8, 4}, {4, 9}, {7, 11},
                {12, 13}, {13, 14}, {14, 12}, {0, 11}, {1, 14}, {7, 10}, {3, 12}, {5, 8},
                {9, 13}, {6, 10}, {2, 14}, {4, 7}
        };


        Solution2204 sol = new Solution2204();

        long startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(sol.distanceToCycle(n3, edges3)));
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

//        System.out.println(sol.distanceToCycle(n, edges));

    }

}
