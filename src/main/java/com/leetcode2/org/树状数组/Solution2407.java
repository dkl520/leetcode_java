package com.leetcode2.org.树状数组;

import java.util.Arrays;

public class Solution2407 {
    // 定义常量N，用于限制数组大小，取值为100010（比题目可能的最大值稍大，确保安全范围内操作）
    private static final int N = 100010; // 1e5 + 10
    // 数组a用于存储当前数字作为结尾时的最长递增子序列长度
    private int[] a = new int[N];
    // 数组h作为树状数组的辅助数组，用于区间查询
    private int[] h = new int[N];

    // lowbit函数：获取x的最低有效位（Least Significant Bit）
    // 用于树状数组中快速移动节点
    private int lowbit(int x) {
        return x & (-x);
    }

    // 树状数组的更新操作：在位置x上更新树状数组
    private void update(int x) {
        int lx = x; // 保留当前数字的位置
        while (x < N) { // 更新树状数组，直到超出范围
            h[x] = Math.max(h[x], a[lx]); // 更新树状数组中x位置的最大值
            x += lowbit(x); // 移动到下一个相关节点
        }
    }

    // 树状数组的区间查询操作：查询区间[x, y]内的最大值
    private int query(int x, int y) {
        int ans = 0; // 存储查询区间的最大值
        while (y >= x) { // 当右边界y未小于左边界x时
            ans = Math.max(a[y], ans); // 首先比较直接存储的a[y]值
            y--; // 减少右边界
            // 当y可以跳过lowbit(y)范围时，快速查询树状数组中的值
            for (; y - lowbit(y) >= x; y -= lowbit(y)) {
                ans = Math.max(h[y], ans); // 查询树状数组的值并更新最大值
            }
        }
        return ans; // 返回区间最大值
    }

    // 主函数：求解数组nums中，最大递增子序列的长度，且相邻数字的差不超过k
    public int lengthOfLIS(int[] nums, int k) {
        // 重置辅助数组，避免被之前的测试数据污染
        Arrays.fill(h, 0);
        Arrays.fill(a, 0);

        // 遍历nums中的每个数字v，更新其对应的最长递增子序列长度
        for (int v : nums) {
            // 查询范围 [v-k, v-1] 内的最长递增子序列长度，并更新a[v]
            // Math.max(1, v - k) 是为了确保查询下界不小于1
//            if  v=5 k==3  [  2,3,4,  ]
//               【2，4】
            a[v] = Math.max(a[v],
                    query(Math.max(1, v - k), v - 1)
                            + 1
            );
            // 更新树状数组，以便后续查询
            update(v);
        }

        // 查询整个数组范围内的最大值，即为答案
        return query(1, N - 1);
    }
}


//算法关键步骤
//
//对每个数字 v，查询它前面 v-k 到 v-1 范围内的最长递增子序列长度
//将当前数字 v 作为新的子序列结尾，更新其最长递增子序列长度
//更新树状数组，为后续查询做准备
//
//        时间和空间复杂度
//
//时间复杂度：O(n log n)
//空间复杂度：O(N)，其中 N 为常量（100010）
//
//算法优势
//
//        高效的区间查询和更新
//通过树状数组实现快速的最长递增子序列计算
//        空间复杂度相对传统动态规划方法更低
//
//示例解释
//对于数组 [4,2,1,4,3,4,5,8,15]，k = 3，算法将找出最长满足条件的递增子序列。
//这是一个非常精妙的算法实现，结合了树状数组的高效性和动态规划的思想，巧妙地解决了带有差值限制的最长递增子序列问题。


//这是一个使用树状数组（Binary Indexed Tree）解决最长递增子序列（Longest Increasing Subsequence, LIS）变体问题的复杂算法实现。
//      让我详细解析这段代码的设计思路和解决的问题：
//问题背景
//该代码解决的是一个特殊的最长递增子序列问题：在给定一个整数数组和一个最大差值限制 k，求满足以下条件的最长子序列长度：
//
//子序列是严格递增的
//任意相邻两个元素之间的差值不超过 k
//
//算法核心
//树状数组（Binary Indexed Tree）
//树状数组是一种高效的数据结构，用于快速进行区间查询和单点更新操作，时间复杂度为 O(log n)。




