package com.leetcode2.org.图论;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2050 {
    static class Node {
        int time;
        List<Integer> next;
        int index;

        public Node(int time, int index) {
            this.time = time;
            this.index = index;
            this.next = new ArrayList<>();
        }
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        if (time.length == 0) return 0;
        if (time.length == 1) return time[0];
        List<Node> record = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            if (i == 0) {
                record.add(new Node(0, i));
            } else {
                record.add(new Node(time[i - 1], i));
            }
        }
        int[] indegree = new int[n + 1];
        for (int[] edge : relations) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
            record.get(edge[0]).next.add(edge[1]);
        }
        indegree[0] = -1;
        Queue<Node> starts = new PriorityQueue<>((a, b) -> a.time - b.time);
        for (int i = 0; i <= n; i++) {
            if (indegree[i] == 0) {
                starts.add(record.get(i));
            }
        }
        int duringTime = 0;
        while (!starts.isEmpty()) {
            Node curNode = starts.poll();
            duringTime += curNode.time;
            starts.forEach((node) -> node.time -= curNode.time);
            for(int otherNode : curNode.next) {
                indegree[otherNode]--;
                if (indegree[otherNode] == 0) {
                    starts.add(record.get(otherNode));
                }
            }
        }
        return duringTime;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] relations = {
                {2, 7}, {2, 6}, {3, 6}, {4, 6}, {7, 6},
                {2, 1}, {3, 1}, {4, 1}, {6, 1}, {7, 1},
                {3, 8}, {5, 8}, {7, 8},
                {1, 9}, {2, 9}, {6, 9}, {7, 9}
        };
        int[] time = {9, 5, 9, 5, 8, 7, 7, 8, 4};
        System.out.println(new Solution2050().minimumTime(n, relations, time));


    }


}
