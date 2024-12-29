package com.leetcode2.广度优先搜索;

import java.util.*;

public class Solution743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int[] vistited = new int[n+1];
        Arrays.fill(vistited, Integer.MAX_VALUE);
        vistited[k] = 0;
        vistited[0] = 0;
        deque.add(k);
        for (int[] time : times) {
            map.computeIfAbsent(time[0], x -> new ArrayList<>()).add(time);
        }

        while (!deque.isEmpty()) {

            int cur = deque.poll();
            List<int[]> curList = map.get(cur);
            if (curList == null) {
                continue;
            }
            for (int[] curNode : curList) {
                if (curNode == null) continue;

                if (vistited[curNode[1]] <= curNode[2] + vistited[curNode[0]]) {
                    continue;
                }
                vistited[curNode[1]] = curNode[2] + vistited[curNode[0]];
                deque.add(curNode[1]);
            }
        }
        return Arrays.stream(vistited).max().orElse(-1) == Integer.MAX_VALUE ? -1 : Arrays.stream(vistited).max().orElse(-1) ;

    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        Solution743 solution743 = new Solution743();
        solution743.networkDelayTime(times, n, k);
    }
}
