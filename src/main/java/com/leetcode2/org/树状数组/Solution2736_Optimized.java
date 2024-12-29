package com.leetcode2.org.树状数组;

import java.util.*;


public class Solution2736_Optimized {
    static class BIT {
        private final int m;
        private final int n;
        private final int[][] tree;

        BIT(int m, int n) {
            this.m = m;
            this.n = n;
            this.tree = new int[m + 1][n + 1];
        }

        private static int lowBit(int x) {
            return x & (-x);
        }

        int query(int x, int y) {
            int max = 0;
            for (int i = x; i > 0; i -= lowBit(i)) {
                for (int j = y; j > 0; j -= lowBit(j)) {
                    max = Math.max(tree[i][j], max);
                }
            }
            return max;
        }

        void update(int x, int y, int amount) {
            for (int i = x; i <= m; i += lowBit(i)) {
                for (int j = y; j <= n; j += lowBit(j)) {
                    tree[i][j] = Math.max(amount, tree[i][j]);
                }
            }
        }
    }

    private List<int[]> mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ?
                Integer.compare(a[0], b[0]) : Integer.compare(b[1], a[1]));

        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty() || !isFullyCovered(interval, result.get(result.size() - 1))) {
                result.add(interval);
            }
        }
        return result;
    }

    private static boolean isFullyCovered(int[] a, int[] b) {
        return a[0] < b[0] && a[1] < b[1];
    }

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums1[i], nums2[i]};
        }

        // 收集并排序唯一值
        TreeSet<Integer> values1 = new TreeSet<>(Collections.reverseOrder());
        TreeSet<Integer> values2 = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            values1.add(nums1[i]);
            values2.add(nums2[i]);
        }
        for (int[] query : queries) {
            values1.add(query[0]);
            values2.add(query[1]);
        }

        // 转换为int数组
        int[] rankArray1 = new int[values1.size()];
        int[] rankArray2 = new int[values2.size()];
        int index1 = 0;
        int index2 = 0;
        for (Integer value : values1) {
            rankArray1[index1++] = value;
        }
        for (Integer value : values2) {
            rankArray2[index2++] = value;
        }

        // 使用数组替代HashMap进行排名查找
        int maxVal1 = values1.first() + 1;
        int maxVal2 = values2.first() + 1;
        int[] rankMap1 = new int[maxVal1];
        int[] rankMap2 = new int[maxVal2];

        for (int i = 0; i < rankArray1.length; i++) {
            rankMap1[rankArray1[i]] = i + 1;
        }
        for (int i = 0; i < rankArray2.length; i++) {
            rankMap2[rankArray2[i]] = i + 1;
        }

        // 构建树状数组
        BIT bit = new BIT(rankArray1.length, rankArray2.length);
        List<int[]> filteredPairs = mergeIntervals(pairs);

        for (int[] pair : filteredPairs) {
            bit.update(rankMap1[pair[0]], rankMap2[pair[1]], pair[0] + pair[1]);
        }

        // 处理查询
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int rank1 = rankMap1[queries[i][0]];
            int rank2 = rankMap2[queries[i][1]];
            int result = bit.query(rank1, rank2);
            answer[i] = result == 0 ? -1 : result;
        }

        return answer;
    }
}