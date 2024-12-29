package com.leetcode2.org.图论;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
public class Dijkstra {
    public static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] distance = new int[n];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.offer(new int[]{start, 0});
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int vertex = node[0];
            int distanceVertexToOriginal = node[1];
            for (int i = 0; i < n; i++) {
                if (graph[vertex][i] != 0 && distanceVertexToOriginal + graph[vertex][i] < distance[i]) {
                    distance[i] = distanceVertexToOriginal + graph[vertex][i];
                    queue.offer(new int[]{i, distance[i]});
                }
            }
        }
        return distance;
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
        int[] distances = dijkstra(graph, 0);
        System.out.println(Arrays.toString(distances));
    }
}
