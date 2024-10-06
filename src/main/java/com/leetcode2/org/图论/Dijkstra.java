package com.leetcode2.org.图论;


import java.util.*;

public class Dijkstra {
//     bfs+贪心算法
    // 图的边，包含目标节点和目标节点的权重
    static class Edge {
        int target;  // 目标节点的索引  
        int weight;  // 边的权重

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // 图的节点，用于Dijkstra算法中的优先队列
    static class Node {
        int id;      // 节点的索引  
        int distance; // 从源点到该节点的最短距离

        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }

    // 实现Dijkstra算法，返回从源点到所有其他节点的最短距离
    public  int[] dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();  // 图的节点数  
        int[] distances = new int[n];  // 存储从源点到每个节点的最短距离  
        Arrays.fill(distances, Integer.MAX_VALUE);  // 初始化为无穷大

        distances[source] = 0;  // 源点到自身的距离为0
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));  // 优先队列，按照距离排序  
        pq.add(new Node(source, 0));  // 将源节点加入优先队列

        while (!pq.isEmpty()) {  // 当优先队列不为空时继续  
            Node node = pq.poll();  // 取出当前距离最小的节点  
            int current = node.id;  // 当前节点的索引  
            int currentDistance = node.distance;  // 当前节点到源点的距离
            // 如果当前节点已经被处理过更短的距离，则跳过
            if (currentDistance > distances[current]) continue;
            // 遍历当前节点的所有邻居  
            for (Edge edge : graph.get(current)) {
                int neighbor = edge.target;  // 邻居节点的索引  
                int newDist = currentDistance + edge.weight;  // 通过当前节点到达邻居节点的新距离
                // 如果新距离小于之前记录的距离，则更新距离，并将邻居节点加入优先队列  
                if (newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;

                    pq.add(new Node(neighbor, newDist));
                }
            }
        }
        return distances;  // 返回从源点到所有其他节点的最短距离数组  
    }


    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 添加边
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(4, 5));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(4, 2));
        graph.get(2).add(new Edge(3, 4));
        graph.get(3).add(new Edge(0, 7));
        graph.get(3).add(new Edge(2, 6));
        graph.get(4).add(new Edge(1, 3));
        graph.get(4).add(new Edge(2, 9));
        graph.get(4).add(new Edge(3, 2));
        int source = 0;
        Dijkstra solution = new Dijkstra();
        int[] distances = solution.dijkstra(graph, source);
        // 输出从源节点到每个节点的最短距离
        System.out.println("从节点 " + source + " 到其他节点的最短距离:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("到节点 " + i + " 的距离: " + distances[i]);
        }
    }
}
