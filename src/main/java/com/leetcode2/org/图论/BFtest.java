package com.leetcode2.org.图论;

import java.util.Arrays;

public class BFtest {
    static int[] bellmanFord(int[][] graph, int start) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph.length; j++) {
                if (graph[i][j] != 0 && dist[i] + graph[i][j] < dist[j]) {
                    dist[j] = dist[i] + graph[i][j];
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) {

        int[][] graph = {
                {0, 4, 0, 0, 0, 0},  // Vertex 0 connected to Vertex 1 with weight 4
                {4, 0, 8, 0, 0, 0},  // Vertex 1 connected to Vertex 0 with weight 4, Vertex 2 with weight 8
                {0, 8, 0, 7, 0, 4},  // Vertex 2 connected to Vertex 1 with weight 8, Vertex 3 with weight 7, Vertex 5 with weight 4
                {0, 0, 7, 0, 9, 14}, // Vertex 3 connected to Vertex 2 with weight 7, Vertex 4 with weight 9, Vertex 5 with weight 14
                {0, 0, 0, 9, 0, 10}, // Vertex 4 connected to Vertex 3 with weight 9, Vertex 5 with weight 10
                {0, 0, 4, 14, 10, 0}  // Vertex 5 connected to Vertex 2 with weight 4, Vertex 3 with weight 14, Vertex 4 with weight 10
        };

//        List<List<Integer>> graph = Arrays.asList(
//                Arrays.asList(1),
//                Arrays.asList(2),
//                Arrays.asList( 3, 5),
//                Arrays.asList(2, 4, 5),
//                Arrays.asList(3, 5),
//                Arrays.asList(2, 3, 4)
//        );
        int[] distances = bellmanFord(graph, 0);
        System.out.println(Arrays.toString(distances));


    }
}
