package com.leetcode2.org.图论;

import java.util.*;

public class PrimAlgorithm {
    public List<int[]> prim(int[][] graph) {
        int n = graph.length;
        Set<Integer> visited = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        Queue<int[]> coll = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        coll.offer(new int[]{0, 0, 0});
        while (!coll.isEmpty()) {
            int[] curNode = coll.poll();
            int start = curNode[0];
            int target = curNode[1];
            int dis = curNode[2];
            if (visited.contains(target)) continue;
            visited.add(target);
            if (dis != 0) result.add(curNode);
            for (int i = 0; i < n; i++) {
                if (!visited.contains(i) && graph[target][i] != 0) {
                    coll.add(new int[]{target, i, graph[target][i]});
                }
            }
        }
        return result;
    }
}


//public static void main(String[] args) {
//    int[][] graph = {
//            {0, 4, 3, 0, 0, 0},
//            {4, 0, 1, 2, 0, 0},
//            {3, 1, 0, 5, 6, 0},
//            {0, 2, 5, 0, 7, 2},
//            {0, 0, 6, 7, 0, 4},
//            {0, 0, 0, 2, 4, 0}
//    };
//    PrimAlgorithm algorithm = new PrimAlgorithm();
//    List<int[]> result = algorithm.prim(graph);
//    for(int [] line: result){
//        System.out.println(Arrays.toString(line));
//    }
//}
