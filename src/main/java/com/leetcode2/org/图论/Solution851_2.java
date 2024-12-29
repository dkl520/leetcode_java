package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Solution851_2 {
        public int[] loudAndRich(int[][] richer, int[] quiet) {
            int n = quiet.length;
            // Create adjacency list to represent the graph
            List<List<Integer>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            // Build the graph (directed graph where an edge means the first person is richer)
            for (int[] edge : richer) {
                graph.get(edge[1]).add(edge[0]);
            }
            // Result array to store the quietest person
            int[] result = new int[n];
            Arrays.fill(result, -1);  // Initialize with -1 to mark unvisited
            // DFS to find the quietest person for each node
            for (int i = 0; i < n; i++) {
                dfs(i, graph, quiet, result);
            }
            return result;
        }

        private int dfs(int node, List<List<Integer>> graph, int[] quiet, int[] result) {
            // If we've already computed the result for this node, return it
            if (result[node] != -1) {
                return result[node];
            }
            // Initialize with the current node as the quietest
            result[node] = node;
            // Check all richer people
            for (int richerPerson : graph.get(node)) {
                int candidate = dfs(richerPerson, graph, quiet, result);
                // Update to the quieter person
                if (quiet[candidate] < quiet[result[node]]) {
                    result[node] = candidate;
                }
            }
            return result[node];
        }

        public static void main(String[] args) {
            Solution851_2 solution = new Solution851_2();
            int[][] richer = {
                    {1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}
            };
            int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
            System.out.println(Arrays.toString(
                    solution.loudAndRich(richer, quiet)
            ));
        }
    }