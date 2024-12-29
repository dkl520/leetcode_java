package com.leetcode2.org.树状数组;

import java.util.*;

public class Solution2736 {

    static class BIT {
        int m;
        int n;
        int[][] tree;

        BIT(int m, int n) {
            this.n = n;
            this.m = m;
            tree = new int[m + 1][n + 1];
        }

        int lowBit(int x) {
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

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        Set<Integer> rankNum1 = new HashSet<>();
        Set<Integer> rankNum2 = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] > 0) rankNum1.add(nums1[i]);
            if (nums2[i] > 0) rankNum2.add(nums2[i]);
        }
        for (int[] its : queries) {
            if (its[0] > 0) rankNum1.add(its[0]);
            if (its[1] > 0) rankNum2.add(its[1]);
        }
        // 3. 使用ArrayList排序替代TreeSet的自动排序
        List<Integer> sortedRank1 = new ArrayList<>(rankNum1);
        List<Integer> sortedRank2 = new ArrayList<>(rankNum2);
        rankNum2.clear();
        rankNum1.clear();
        rankNum1 = null;
        rankNum2 = null;
        sortedRank1.sort(Collections.reverseOrder());
        sortedRank2.sort(Collections.reverseOrder());
        // 4. 直接使用数组索引作为rank,避免额外的Map开销
        Map<Integer, Integer> mapRank1 = new HashMap<>(sortedRank1.size());
        Map<Integer, Integer> mapRank2 = new HashMap<>(sortedRank2.size());
        for (int i = 0; i < sortedRank1.size(); i++) {
            mapRank1.put(sortedRank1.get(i), i + 1);
        }
        for (int i = 0; i < sortedRank2.size(); i++) {
            mapRank2.put(sortedRank2.get(i), i + 1);
        }
        sortedRank1.clear();
        sortedRank2.clear();
        sortedRank1 = null;
        sortedRank2 = null;

        BIT bit = new BIT(mapRank1.size(), mapRank2.size());

        for (int i = 0; i < n; i++) {
            int rank1 = mapRank1.get(nums1[i]);
            int rank2 = mapRank2.get(nums2[i]);
            bit.update(rank1, rank2, nums1[i] + nums2[i]);
        }
        int[] answer = new int[queries.length];
        int indexq = 0;
        for (int[] query : queries) {
            int rank1 = mapRank1.get(query[0]);
            int rank2 = mapRank2.get(query[1]);
            int amount = bit.query(rank1, rank2);
            answer[indexq++] = amount == 0 ? -1 : amount;
        }
        // 6. 清除最后的Map
        mapRank1.clear();
        mapRank2.clear();
        mapRank1 = null;
        mapRank2 = null;

        return answer;
    }

    public static void main(String[] args) {
        // 定义 nums1 和 nums2 数组
        int[] nums1 = {4, 3, 1, 2};
        int[] nums2 = {2, 4, 9, 5};
        // 定义 queries 二维数组
        int[][] queries = {
                {4, 1},
                {1, 3},
                {2, 5}
        };
        Solution2736 solution2736 = new Solution2736();
        System.out.println(Arrays.toString(solution2736.maximumSumQueries(nums1, nums2, queries)));
    }


}
