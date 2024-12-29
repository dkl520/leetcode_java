package com.leetcode2.org.扫描线;

import java.util.*;

public class Solution1851_4 {
    public int[] minInterval(int[][] intervals, int[] queries) {

        TreeMap<Integer, List<int[]>> dic = new TreeMap<>();
        for (int[] interval : intervals) {
            dic.computeIfAbsent(interval[0], newList -> new ArrayList<>())
                    .add(new int[]{0, interval[1] - interval[0] + 1});

            dic.computeIfAbsent(interval[1],
                            newList -> new ArrayList<>())
                    .add(new int[]{2, interval[1] - interval[0] + 1});
        }

        for (int i = 0; i < queries.length; i++) {
            dic.computeIfAbsent(queries[i],
                            newList -> new ArrayList<>())
                    .add(new int[]{1, i});
        }
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compare);
        dic.values().forEach(list -> {
            list.sort(Comparator.comparingInt(a -> a[0]));
            for (int[] event : list) {
                if (event[0] == 0) {
                    pq.offer(event[1]);
                } else if (event[0] == 1) {
                    ans[event[1]] = pq.isEmpty() ? -1 : pq.peek();

                } else {
                    pq.remove(event[1]);
                }
            }
        });

        return ans;
    }

    public static void main(String[] args) {
        Solution1851_4 solution = new Solution1851_4();
//        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
//        int[] queries = {2, 3, 4, 5};
        int[][] intervals = {
                {9, 9},
                {6, 7},
                {5, 6},
                {2, 5},
                {3, 3}
        };

        int[] queries = {6, 1, 1, 1, 9};

        System.out.println(Arrays.toString(solution.minInterval(intervals, queries))); // 输出结果
    }
}
