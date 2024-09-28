package com.leetcode2.org.树状数组;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution2940_6 {

    static class BinaryIndexedTree {
        private final int inf = 1 << 30;
        private int n; // 树状数组的大小
        private int[] c; // 树状数组，用于存储信息

        public BinaryIndexedTree(int n) {
            this.n = n;
            c = new int[n + 1];
            Arrays.fill(c, inf);
        }

        public void update(int x, int v) {
            while (x <= n) {
                c[x] = Math.min(c[x], v);
                x += x & -x;
            }
        }

        public int query(int x) {
            int mi = inf;
            while (x > 0) {
                mi = Math.min(mi, c[x]);
                x -= x & -x;
            }
            return mi == inf ? -1 : mi;
        }
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        int[] result = new int[m];
        // 确保查询的左边界不大于右边界
        for (int i = 0; i < m; ++i) {
            if (queries[i][0] > queries[i][1]) {
                // 交换左右边界
                int temp = queries[i][0];
                queries[i][0] = queries[i][1];
                queries[i][1] = temp;
            }
        }
        // 获取排序后的高度并映射到排名
//        int[] sortedHeights = Arrays.copyOf(heights, n);
//        Arrays.sort(sortedHeights, (a, b) -> b - a);
//        int n = heights.length;
        int[] sortedHeights = Arrays.stream(heights)
                .boxed() // 将 int 转换为 Integer 对象
                .sorted(Collections.reverseOrder()) // 使用倒序排序
                .mapToInt(Integer::intValue) // 将 Integer 再转换为 int
                .toArray(); // 转换回 int 数组
        Map<Integer, Integer> mapRank = new HashMap<>();

        for (int i = 0, rank = 1; i < n; i++) {
            if (!mapRank.containsKey(sortedHeights[i])) {
                mapRank.put(sortedHeights[i], rank++);
            }
        }

        // 按查询的右边界排序
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[j][1] - queries[i][1]);

        // 使用树状数组
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int j = n - 1;
        for (int i : idx) {
            int l = queries[i][0], r = queries[i][1];
            int rankL = mapRank.get(heights[l]);

            // 将未处理的建筑更新到树状数组中
            while (j > r) {
                tree.update(mapRank.get(heights[j]), j);
                j--;
            }

            if (l == r || heights[l] < heights[r]) {
                result[i] = r;
            } else {
                result[i] = tree.query(rankL - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] heights = {1, 2, 1, 2, 1, 2};
//        int[][] queries = {
//                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
//                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
//                {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5},
//                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5},
//                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5},
//                {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}
//        };

//        int[] heights = {6, 4, 8, 5, 2, 7};
//        int[][] queries = {
//                {0, 1},
//                {0, 3},
//                {2, 4},
//                {3, 4},
//                {2, 2}
//        };
        int[] heights = {5, 3, 8, 2, 6, 1, 4, 6};

        int[][] queries = {
                {0, 7},
                {3, 5},
                {5, 2},
                {3, 0},
                {1, 6}
        };

        System.out.println(Arrays.toString(new Solution2940_6().leftmostBuildingQueries(heights, queries)));
    }
}
