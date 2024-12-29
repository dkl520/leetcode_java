package com.leetcode2.org.大厂.barclay;
import java.util.*;
public class MainB3 {
    static class NodeB {
        int val;
        NodeB left;
        NodeB right;
        NodeB parent;
        int deep;
        public NodeB(int val, int deep) {
            this.val = val;
            this.deep = deep;
        }
    }
    public String getGraphNodeList(String graph) {
        if (graph == null || graph.isEmpty()) {
            return "";
        }
        char[] chars = graph.toCharArray();
        List<NodeB> effectiveNodes = new ArrayList<>(graph.length() / 2);
        NodeB root = new NodeB(chars[0] - '0', 0);
        if (root.val == 1) {
            effectiveNodes.add(root);
        }
        Queue<NodeB> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < chars.length; i += 2) {
            NodeB current = queue.poll();
            if (current == null) break;
            current.left = new NodeB(chars[i] - '0', i);
            current.left.parent = current;
            if (current.left.val == 1) {
                effectiveNodes.add(current.left);
            }
            queue.offer(current.left);
            if (i + 1 < chars.length) {
                current.right = new NodeB(chars[i + 1] - '0', i + 1);
                current.right.parent = current;
                if (current.right.val == 1) {
                    effectiveNodes.add(current.right);
                }
                queue.offer(current.right);
            }
        }
        StringBuilder result = new StringBuilder(effectiveNodes.size());
        for (NodeB node : effectiveNodes) {
//            int deep = findFarthestDistByDFS(node, new HashSet<>());
            int deep = findFarthestDistByBFS(node);
            result.append(deep);
        }
        return result.toString();
    }

    private int findFarthestDistByDFS(NodeB start, Set<NodeB> visited) {
        visited.add(start);
        int maxDist = 0;
        NodeB[] directions = {start.left, start.right, start.parent};
        for (NodeB next : directions) {
            if (next != null && next.val == 1 && !visited.contains(next)) {
                maxDist = Math.max(maxDist, 1 + findFarthestDistByDFS(next, visited));
            }
        }
        visited.remove(start); // 回溯时移除节点
        return maxDist;
    }

    int findFarthestDistByBFS(NodeB start) {
        Map<NodeB, Integer> dist = new HashMap<>();
        Queue<NodeB> queue = new LinkedList<>();
        int maxDist = 0;
        // 初始化起点
        queue.offer(start);
        dist.put(start, 0);
        while (!queue.isEmpty()) {
            NodeB current = queue.poll();
            int currentDist = dist.get(current);
            NodeB[] neighbors = {current.left, current.right, current.parent};
            for (NodeB neighbor : neighbors) {
                if (neighbor != null && neighbor.val == 1 && !dist.containsKey(neighbor)) {
                    int newDist = currentDist + 1;
                    dist.put(neighbor, newDist);
                    queue.offer(neighbor);
                    maxDist = Math.max(maxDist, newDist);
                }
            }
        }
        return maxDist;
    }

    public static void main(String[] args) {
        String graph = "111110000010000";
        long startTime = System.currentTimeMillis();
        MainB3 mainB2 = new MainB3();
        System.out.println(mainB2.getGraphNodeList(graph));
        long endTime = System.currentTimeMillis();
        System.out.println("Dfs运算时间22");
        System.out.println(endTime - startTime);
    }
}
