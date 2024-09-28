package com.leetcode2.org.树状数组;

import java.util.*;

public class Solution2940_2 {
    static class BinaryIndexedTree {
        // 使用一个非常大的数作为初始值，表示尚未更新
        private final int inf = 1 << 30;
        private int n; // 树状数组的大小
        private int[] c; // 树状数组，用于存储信息
        public BinaryIndexedTree(int n) {
            this.n = n;
            c = new int[n + 1]; // 数组大小设为n+1，因为树状数组通常从1开始索引
            Arrays.fill(c, inf); // 初始化为inf
        }
        // 更新树状数组中x位置的值为v（取v和原值的较小值）
        public void update(int x, int v) {
            while (x <= n) {
                c[x] = Math.min(c[x], v); // 更新当前节点及其祖先节点的值
                x += x & -x; // 跳到下一个需要更新的节点
            }
        }
        // 查询树状数组中1到x的最小值
        public int query(int x) {
            int mi = inf; // 初始化为inf，表示尚未找到最小值
            while (x > 0) {
                mi = Math.min(mi, c[x]); // 更新当前找到的最小值
                x -= x & -x; // 跳到上一个节点继续查询
            }
            return mi == inf ? -1 : mi; // 如果mi仍然是inf，则表示查询范围内无有效值，返回-1
        }
    }
    // 查询在给定高度范围内的最左侧建筑索引
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length; // 建筑数量
        int m = queries.length; // 查询数量
        // 确保查询的左边界不大于右边界
        for (int i = 0; i < m; ++i) {
            if (queries[i][0] > queries[i][1]) {
                // 交换左右边界
                int temp = queries[i][0];
                queries[i][0] = queries[i][1];
                queries[i][1] = temp;
            }
        }
        // 按照查询的右边界降序排序，以便从右向左处理建筑
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[j][1] - queries[i][1]);
        // 复制并排序建筑高度，以便快速查找高度对应的索引
        int[] s = heights.clone();
        Arrays.sort(s);
        // 存储每个查询的结果
        int[] ans = new int[m];
        // 从右向左遍历建筑，并使用树状数组维护信息
        int j = n - 1; // 当前处理的建筑索引
        BinaryIndexedTree tree = new BinaryIndexedTree(n); // 创建树状数组
        // 遍历排序后的查询索引
        for (int i : idx) {
            int l = queries[i][0], r = queries[i][1]; // 当前查询的左右边界
            // 将当前查询右边界右侧的建筑信息加入树状数组
            while (j > r) {
                // 查找当前建筑高度在排序数组s中的位置，并计算对应的树状数组索引
                int k = n - Arrays.binarySearch(s, heights[j]) + 1;
                tree.update(k, j); // 更新树状数组
                --j; // 处理下一个建筑
            }
            // 根据左右边界的高度关系判断最左侧建筑的索引
            if (l == r || heights[l] < heights[r]) {
                // 如果左边界等于右边界或左边界高度小于右边界，则最左侧建筑就是右边界的建筑
                ans[i] = r;
            } else {
                // 否则，在树状数组中查询左边界高度对应的最小索引（即最左侧的建筑索引）
                int k = n - Arrays.binarySearch(s, heights[l]); // 查找左边界高度在s中的位置
                ans[i] = tree.query(k); // 树状数组查询
            }
        }
        return ans; // 返回查询结果
    }
    public static void main(String[] args) {
        int[] heights = {1, 2, 1, 2, 1, 2};
        int[][] queries = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
                {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5},
                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5},
                {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}
        };

//        int[] heights = {6, 4, 8, 5, 2, 7};
//        int[][] queries = {
//                {0, 1},
//                {0, 3},
//                {2, 4},
//                {3, 4},
//                {2, 2}
//        };

        System.out.println(Arrays.toString(new Solution2940_2().leftmostBuildingQueries(heights, queries)));
    }
}