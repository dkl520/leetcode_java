package com.leetcode2.org.扫描线;

import java.util.*;

public class Solution1851_5 {
    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int size) {
            n = size;
            // 4n 是为了确保有足够空间存储线段树
            tree = new int[4 * n];
            // 初始化线段树，填充最大值表示还没有区间覆盖
            Arrays.fill(tree, Integer.MAX_VALUE);
        }

        // 更新区间[start, end]的最小区间长度值
        public void update(int node, int left, int right, int start, int end, int val) {
            if (left > end || right < start) return;

            if (left >= start && right <= end) {
                tree[node] = Math.min(tree[node], val);
                return;
            }

            int mid = (left + right) / 2;
            update(node * 2 + 1, left, mid, start, end, val);
            update(node * 2 + 2, mid + 1, right, start, end, val);
        }

        // 查询位置idx的最小区间长度
        public int query(int node, int left, int right, int idx) {
            if (left == right) return tree[node];

            int mid = (left + right) / 2;
            int res = tree[node];

            if (idx <= mid) {
                res = Math.min(res, query(node * 2 + 1, left, mid, idx));
            } else {
                res = Math.min(res, query(node * 2 + 2, mid + 1, right, idx));
            }

            return res;
        }
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        // 收集所有需要离散化的点
        Set<Integer> points = new TreeSet<>();
        for (int[] interval : intervals) {
            points.add(interval[0]);
            points.add(interval[1]);
        }
        for (int query : queries) {
            points.add(query);
        }

        // 离散化
        Map<Integer, Integer> compress = new HashMap<>();
        int idx = 0;
        for (int point : points) {
            compress.put(point, idx++);
        }

        // 构建线段树
        SegmentTree st = new SegmentTree(points.size());

        // 处理所有区间
        for (int[] interval : intervals) {
            int start = compress.get(interval[0]);
            int end = compress.get(interval[1]);
            int len = interval[1] - interval[0] + 1;
            st.update(0, 0, points.size() - 1, start, end, len);
        }

        // 处理查询
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int queryIdx = compress.get(queries[i]);
            int ans = st.query(0, 0, points.size() - 1, queryIdx);
            result[i] = ans == Integer.MAX_VALUE ? -1 : ans;
        }

        return result;
    }
}