package com.leetcode2.org.树状数组;

public class Solution2940_5 {
    int[] zd; // 用于存储线段树节点的最大值，即每个节点表示的子区间内建筑的最大高度

    // 主方法，处理所有查询
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length; // 建筑的数量
        zd = new int[n * 4]; // 线段树节点数组，大小通常为4倍于原始数组大小以容纳所有节点
        build(1, n, 1, heights); // 构建线段树

        int m = queries.length; // 查询的数量
        int[] ans = new int[m]; // 存储每个查询的答案
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            // 确保查询的左边界不大于右边界
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // 如果左边界等于右边界或左边界建筑高度小于右边界建筑高度，则直接返回右边界索引
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
                continue;
            }

            // 查询最左侧的高度不低于heights[a]的建筑的索引，由于线段树索引从1开始，且查询的是pos之后的位置，所以传入b+1，最后结果需要减1
            ans[i] = query(b + 1, heights[a], 1, n, 1) - 1;
        }
        return ans; // 返回所有查询的答案
    }

    // 构建线段树
    public void build(int l, int r, int rt, int[] heights) {
        if (l == r) {
            zd[rt] = heights[l - 1]; // 线段树节点存储的是建筑高度，注意索引偏移
            return;
        }

        int mid = (l + r) >> 1;
        build(l, mid, rt << 1, heights); // 递归构建左子树
        build(mid + 1, r, rt << 1 | 1, heights); // 递归构建右子树
        zd[rt] = Math.max(zd[rt << 1], zd[rt << 1 | 1]); // 更新当前节点的最大高度
    }

    // 查询操作
    public int query(int pos, int val, int l, int r, int rt) {
        // 如果当前节点的最大高度小于等于查询值，则无需继续搜索该子树
        if (val >= zd[rt]) {
            return 0;
        }

        if (l == r) {
            // 找到符合条件的建筑索引
            return l;
        }

        int mid = (l + r) >> 1;
        // 先在左子树中查询
        if (pos <= mid) {
            int res = query(pos, val, l, mid, rt << 1);
            if (res != 0) {
                return res; // 如果在左子树中找到符合条件的建筑，则返回其索引
            }
        }
        // 如果左子树中没有找到，则在右子树中继续查询
        return query(pos, val, mid + 1, r, rt << 1 | 1);
    }
}