package com.leetcode2.org.大厂.barclay;

import java.util.*;
import java.util.stream.Collectors;

public class MainB2 {

    int findFarthestDistByBFS(NodeB start) {
        Map<NodeB, Integer> dist = new HashMap<>();
        Queue<NodeB> queue = new LinkedList<>();
        queue.add(start);
        dist.put(start, 0);
        int maxDist = 0;
        while (!queue.isEmpty()) {
            NodeB node = queue.poll();
            if (node.left != null && node.left.val == 1 && !dist.containsKey(node.left)) {
                dist.put(node.left, dist.get(node) + 1);
                queue.add(node.left);
                maxDist = Math.max(maxDist, dist.get(node.left));
            }
            if (node.right != null && node.right.val == 1 && !dist.containsKey(node.right)) {
                dist.put(node.right, dist.get(node) + 1);
                queue.add(node.right);
                maxDist = Math.max(maxDist, dist.get(node.right));
            }
            if (node.parent != null && node.parent.val == 1 && !dist.containsKey(node.parent)) {
                dist.put(node.parent, dist.get(node) + 1);
                queue.add(node.parent);
                maxDist = Math.max(maxDist, dist.get(node.parent));
            }
        }
        return maxDist;
    }

    int findFarthestDistByDFS(NodeB start, Set<NodeB> visited) {
        int maxDist = 0;
//        visited.add(start);
        if (start.left != null && start.left.val == 1 && !visited.contains(start.left)) {
            visited.add(start.left);
            maxDist = Math.max(maxDist, findFarthestDistByDFS(start.left, visited) + 1);
        }
        if (start.right != null && start.right.val == 1 && !visited.contains(start.right)) {
            visited.add(start.right);
            maxDist = Math.max(maxDist, findFarthestDistByDFS(start.right, visited) + 1);
        }
        if (start.parent != null && start.parent.val == 1 && !visited.contains(start.parent)) {
            visited.add(start.parent);
            maxDist = Math.max(maxDist, findFarthestDistByDFS(start.parent, visited) + 1);
        }
        return maxDist;
    }

    class NodeB {
        int val;
        NodeB left;
        NodeB right;
        NodeB parent;

        public NodeB(int val) {
            this.val = val;
        }
    }

    String getGraphNodeList(String graph) {
        Queue<NodeB> queue = new ArrayDeque<>();
        NodeB root = new NodeB(Character.getNumericValue(graph.charAt(0)));
        queue.add(root);
        int countI = 1;
        List<NodeB> effectiveNodesList = new ArrayList<>();
        effectiveNodesList.add(root);
        while (!queue.isEmpty() && countI < graph.length()) {
            NodeB node = queue.poll();
            node.left = new NodeB(Character.getNumericValue(graph.charAt(countI)));
            if (node.left.val == 1) {
                effectiveNodesList.add(node.left);
            }
            node.left.parent = node;
            countI++;
            if(countI >=graph.length())break;
            node.right = new NodeB(Character.getNumericValue(graph.charAt(countI)));
            if (node.right.val == 1) {
                effectiveNodesList.add(node.right);
            }
            node.right.parent = node;
            countI++;
            queue.add(node.left);
            queue.add(node.right);
        }
        List<Integer> result = new ArrayList<>();
        for (NodeB node : effectiveNodesList) {
            result.add(findFarthestDistByDFS(node, new HashSet<>(List.of(node))));
//            result.add(findFarthestDistByBFS(node));
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining());

    }


    public static void main(String[] args) {
        String graph = "111110000010000";
        long startTime = System.currentTimeMillis();
        MainB2 mainB2 = new MainB2();
        System.out.println(mainB2.getGraphNodeList(graph));
        long endTime = System.currentTimeMillis();
        System.out.println("dfs运算时间");
        System.out.println(endTime-startTime);

//        System.out.println("Bfs运算时间");
//        System.out.println(endTime-startTime);

    }


}
