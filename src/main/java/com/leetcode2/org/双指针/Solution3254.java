package com.leetcode2.org.双指针;

import java.util.Arrays;

public class Solution3254 {
    /**
     * 该方法接受一个整数数组和一个数字 k，返回一个数组，
     * 其中每个元素表示长度为 k 的连续子数组的最后一个数字。
     * 如果在某个位置不存在这样的子数组，值将保持为 -1。
     *
     * @param nums 输入的整数数组
     * @param k 所需的连续子数组的长度
     * @return 一个数组，每个元素对应长度为 k 的连续子数组的最后一个数字，或 -1 表示不存在
     */
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        // 结果数组的长度为 (n - k + 1)，用于存储所有可能的子数组结果
        int[] ans = new int[n - k + 1];
        // 将结果数组初始化为 -1，表示默认情况下没有符合条件的子数组
        Arrays.fill(ans, -1);

        int cnt = 0; // 用于跟踪当前连续子数组的长度

        for (int i = 0; i < n; i++) {
            // 检查当前数字是新子数组的开始还是延续当前子数组
            if (i == 0 || nums[i] - nums[i - 1] != 1) {
                cnt = 1; // 当序列中断或在第一个元素时，重置计数器
            } else {
                cnt = cnt + 1; // 当序列连续时，增加计数器
            }

            // 如果当前连续子数组的长度达到或超过 k
            if (cnt >= k) {
                // 将位置 i - k + 1 处的值设置为当前数字（子数组的结尾）
                ans[i - k + 1] = nums[i];
            }
        }
        return ans;
    }
}
