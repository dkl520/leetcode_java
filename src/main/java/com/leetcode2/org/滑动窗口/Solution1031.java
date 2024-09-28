package com.leetcode2.org.滑动窗口;

import java.util.*;

public class Solution1031 {
    // 用于存储数组的长度
    int n;

    /**
     * 辅助函数，用于计算给定长度为 a 和 b 的两个不重叠子数组的最大和。
     * 其中 a 和 b 是两个不重叠子数组的长度。
     *
     * @param nums 原始数组
     * @param a 第一个子数组的长度
     * @param b 第二个子数组的长度
     * @return 两个不重叠子数组的最大和
     */
    public int f(int[] nums, int a, int b) {
        int res = 0;  // 用于记录两个不重叠子数组的最大和
        int[] s = new int[n + 1];  // 前缀和数组，长度比 nums 多 1，方便从 1 开始计算

        // 计算前缀和数组，s[i] 表示前 i 个元素的累积和
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];  // 前缀和 s[i] = s[i-1] + nums[i-1]
        }

        int maxa = 0;  // 用于记录在子数组 b 前面的 a 长度的子数组的最大值

        // 从 a + b 位置开始遍历数组，保证两个子数组不重叠
        for (int i = a + b; i <= n; i++) {
            // 计算以当前 i 为结束的子数组前面的 a 长度子数组的最大和
            // maxa 表示在位置 i 之前的最大 a 长度的子数组的和
            maxa = Math.max(maxa, s[i - b] - s[i - b - a]);
            // 计算以当前 i 结束的 b 长度子数组的和，并更新 res 为最大值
            res = Math.max(res, maxa + (s[i] - s[i - b]));
        }

        return res;  // 返回两个不重叠子数组的最大和
    }

    /**
     * 主函数，用于计算两个不重叠子数组的最大和。
     * 会分别计算长度为 a 和 b 的子数组最大和，然后取最大值。
     *
     * @param nums 输入的数组
     * @param a 第一个子数组的长度
     * @param b 第二个子数组的长度
     * @return 两个不重叠子数组的最大和
     */
    public int maxSumTwoNoOverlap(int[] nums, int a, int b) {
        n = nums.length;  // 设置数组的长度
        // 分别计算 f(nums, a, b) 和 f(nums, b, a)，取两者中的最大值
        return Math.max(f(nums, a, b), f(nums, b, a));
    }
}
