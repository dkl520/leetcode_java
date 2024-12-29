package com.leetcode2.org.扫描线;

import java.util.Arrays;
import java.util.Comparator;
//离线算法+并查集
public class Solution1851 {

    public int[] minInterval(int[][] intervals, int[] queries) {
        // 按照区间长度由小到大排序，这样每次回答的时候用的就是长度最小的区间
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1] - o[0]));

        int m = queries.length; // 获取查询数组的长度
        int[][] qs = new int[m][2]; // 创建二维数组存储查询位置和索引
        for (int i = 0; i < m; i++) {
            qs[i] = new int[]{queries[i], i}; // 填充二维数组
        }
        // 离线：按查询位置排序
        Arrays.sort(qs, Comparator.comparingInt(o -> o[0]));

        DSU dsu = new DSU(m + 1); // 初始化并查集
        int[] ans = new int[m]; // 初始化结果数组
        Arrays.fill(ans, -1); // 填充默认值
        for (int[] interval : intervals) { // 遍历每个区间
            int l = interval[0], r = interval[1]; // 获取区间左右端点

            // 二分找大于等于区间左端点的最小询问
            int left = 0;
            int right = m;
            while (left < right) { // 二分查找
                int mid = left + (right - left) / 2; // 计算中间位置
                if (qs[mid][0] >= l) { // 如果中间位置的查询大于等于区间左端点
                    right = mid; // 调整右边界
                } else {
                    left = mid + 1; // 调整左边界
                }
            }

            for (int i = dsu.find(left); i < m && qs[i][0] <= r; i = dsu.find(i + 1)) { // 遍历符合条件的查询
                ans[qs[i][1]] = r - l + 1; // 更新结果数组
                dsu.fa[i] = i + 1; // 更新并查集
            }
        }
        return ans; // 返回结果数组
    }
    //并查集？
    private static class DSU {
        int[] fa; // 父节点数组

        public DSU(int n) {
            fa = new int[n]; // 初始化父节点数组
            for (int i = 0; i < n; i++) {
                fa[i] = i; // 初始化父节点
            }
        }

        int find(int x) { // 查找方法，带路径压缩
            if (x != fa[x]) { // 如果x不是自己的父节点
                fa[x] = find(fa[x]); // 路径压缩
            }
            return fa[x]; // 返回根节点
        }
    }
}