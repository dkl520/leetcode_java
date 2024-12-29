package com.leetcode2.org.扫描线;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
//线段树
public class Solution1851_2 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // 将查询和它们的索引存储在一个数组中
        int[][] q = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        // 按查询值排序
        Arrays.sort(q, Comparator.comparingInt(a -> a[0]));
        // 按区间起点排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // 结果数组
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        // 优先队列，按区间长度排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1] - a[0] + 1));
        int i = 0;
        for (int[] query : q) {
            // 将所有起点小于等于当前查询的区间加入优先队列
            while (i < intervals.length && intervals[i][0] <= query[0]) {
                pq.offer(intervals[i]);
                i++;
            }
            // 移除所有终点小于当前查询的区间
            while (!pq.isEmpty() && pq.peek()[1] < query[0]) {
                pq.poll();
            }
            // 如果优先队列不为空，取出长度最小的区间
            if (!pq.isEmpty()) {
                res[query[1]] = pq.peek()[1] - pq.peek()[0] + 1;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Solution1851_2 solution = new Solution1851_2();
        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = {2, 3, 4, 5};
        System.out.println(Arrays.toString(solution.minInterval(intervals, queries))); // 输出结果
    }
}