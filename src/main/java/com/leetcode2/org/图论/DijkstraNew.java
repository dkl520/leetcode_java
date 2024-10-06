package com.leetcode2.org.图论;

import java.util.*;

// Dijkstra算法实现类
// 本质：BFS（广度优先搜索）+ 贪心策略
// 核心思想：优先考虑最小的路径，因为与起始点相邻的最短路径已经是最优解
// 随着BFS扩散，逐步找到到达所有点的最优解
public class DijkstraNew {

    // 边类，表示图中的一条边
    static class Edge {
        String target;  // 目标节点
        int weight;     // 边的权重（距离）

        public Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // 节点类，用于优先队列中的节点表示
    static class NodePoint {
        String source;  // 节点名称
        int dis;        // 从起始点到该节点的距离

        public NodePoint(String source, int dis) {
            this.source = source;
            this.dis = dis;
        }
    }

    // Dijkstra搜索方法
    // 参数：
    // listMap - 图的邻接表表示，Key是节点，Value是从该节点出发的所有边
    // source - 起始节点
    // 返回值：包含从起始节点到所有其他节点的最短距离
        Map<String, Integer> dijkstraSearch(Map<String, List<Edge>> listMap, String source) {
        // 存储从起始点到每个节点的最短距离
        Map<String, Integer> mapDist = new HashMap<>();
        // 存储从起始点到每个节点的路径
        Map<String, Deque<String>> lineDist = new HashMap<>();

        // 初始化距离和路径
        for (String key : listMap.keySet()) {
            mapDist.put(key, Integer.MAX_VALUE);  // 初始化所有距离为无穷大
            lineDist.put(key, new ArrayDeque<>()); // 初始化空路径
        }
        mapDist.put(source, 0);  // 起始点到自身的距离为0
        lineDist.put(source, new ArrayDeque<>(List.of(source))); // 起始点路径只包含自己

        // 优先队列，按照距离从小到大排序
        PriorityQueue<NodePoint> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dis));
        pq.add(new NodePoint(source, 0));

        // 主循环
        while (!pq.isEmpty()) {
            NodePoint neighbor = pq.poll(); // 取出当前距离最小的节点
            String cur = neighbor.source;
            // 遍历当前节点的所有邻边
            for (Edge edge : listMap.get(cur)) {
                String next = edge.target;
                int nextDis = edge.weight + neighbor.dis; // 计算通过当前节点到达下一节点的总距离

                // 如果找到了更短的路径，更新距离和路径
                if (mapDist.get(next) > nextDis) {
                    mapDist.put(next, nextDis);
                    // 更新路径
                    Deque<String> stack = new ArrayDeque<>(lineDist.get(cur));
                    stack.addLast(next);
                    lineDist.put(next, stack);
                    // 将新的节点加入优先队列
                    pq.offer(new NodePoint(next, nextDis));
                }
            }
        }
        return mapDist;
    }







    public static void main(String[] args) {
        // 创建测试用例并运行
        TestCase testCase = new TestCase();
        testCase.runTest();
    }

    // 测试用例类，封装测试相关的代码
    static class TestCase {
        private final Map<String, List<Edge>> graph;
        private final String startNode = "A";

        // 构造函数中初始化图
        public TestCase() {
            this.graph = buildGraph();
        }

        // 运行测试
        public void runTest() {
            DijkstraNew dijkstra = new DijkstraNew();
            Map<String, Integer> result = dijkstra.dijkstraSearch(graph, startNode);

            // 格式化输出结果
            System.out.println("从节点 " + startNode + " 到各节点的最短距离：");
            result.forEach((node, distance) ->
                    System.out.printf("到节点 %s 的距离: %d%n", node, distance));
        }

        // 构建测试图
        private Map<String, List<Edge>> buildGraph() {
            Map<String, List<Edge>> graph = new HashMap<>();

            // 定义所有节点
            String[] nodes = {"A", "B", "C", "D", "E", "F", "G"};

            // 初始化图的节点
            Arrays.stream(nodes).forEach(node ->
                    graph.put(node, new ArrayList<>()));

            // 定义边的数据结构：起点、终点、权重
            Object[][] edges = {
                    {"A", "B", 2}, {"A", "C", 3},
                    {"B", "D", 4}, {"B", "E", 1},
                    {"C", "F", 2},
                    {"F", "G", 6},
                    {"D", "G", 7},
                    {"E", "G", 5}
            };

            // 添加边
            for (Object[] edge : edges) {
                String from = (String) edge[0];
                String to = (String) edge[1];
                int weight = (int) edge[2];
                graph.get(from).add(new Edge(to, weight));
            }

            return graph;
        }
    }
}