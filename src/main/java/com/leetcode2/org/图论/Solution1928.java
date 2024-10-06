package com.leetcode2.org.图论;

import com.sun.source.tree.BreakTree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.*;

public class Solution1928 {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
//        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        Map<Integer, List<int[]>>map = new HashMap<>();

        for (int[] edge : edges) {
//            map.put(edge[0], map.getOrDefault(edge[0], new ArrayList<>()).add(
//                    new int[]{edge[1], edge[2]})
//            );
//            map.putIfAbsent(edge[0], new ArrayList<>()); // 如果 map 中不存在 key edge[0]，则插入一个新的 ArrayList
//            map.get(edge[0]).add(new int[]{edge[1], edge[2]}); // 获取 edge[0] 对应的 List，并添加新的元素
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});

            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});


        }
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
        queue.offer(new int[]{0, 0, passingFees[0]});
//                            点 时间        i
        int[][] dp = new int[maxTime + 1][passingFees.length];

        for (int[] panel : dp) {
            Arrays.fill(panel, Integer.MAX_VALUE);
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            //下个点的位置和耗时 和费用
            List<int[]> nextArr = map.get(cur[0]);
//                 下个点的坐标 和时间
            for (int[] next : nextArr) {
                int nextPoint = next[0];
                int nextTime = next[1] + cur[1];
                if (nextTime > maxTime) continue;
                int nextFree = passingFees[nextPoint] + cur[2];
                if (nextFree > dp[nextTime][nextPoint]) continue;
                for (int i = nextTime; i <= maxTime; i++) {
                    if (dp[i][nextPoint] > nextFree) {
                        dp[i][nextPoint] = nextFree;
                    }
                }
                queue.offer(new int[]{nextPoint, nextTime, nextFree});
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= maxTime; i++) {
            min = Math.min(min, dp[i][passingFees.length - 1]);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        Solution1928 solution1928 = new Solution1928();
        int maxTime = 30;
        int[][] edges = {
                {0, 1, 10},
                {1, 2, 10},
                {2, 5, 10},
                {0, 3, 1},
                {3, 4, 10},
                {4, 5, 15}
        };
        int[] passingFees = {5, 1, 2, 20, 20, 3};
        System.out.println(
                solution1928.minCost(maxTime, edges, passingFees)
        );

    }

}
