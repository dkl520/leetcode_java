package com.leetcode2.org.前缀和;

import java.util.Arrays;

import java.util.Arrays;

public class MinSubArrayLen {
    // 查找最小子数组长度，使其和为目标值
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        // 计算前缀和数组
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int minLength = Integer.MAX_VALUE;
        // 尝试对每个位置i，通过二分查找找到满足条件的j（这里逻辑有误）
        for (int i = 0; i < n; i++) {
            int toFind = target + prefixSum[i]; // 这里尝试找到和为prefixSum[i] + target的位置
            int j = Arrays.binarySearch(prefixSum, toFind);
            // 处理二分查找未找到确切值的情况
            if (j < 0) {
                j = -j - 1;
            }
            // 这里的逻辑也有问题，因为j可能超出数组界限或不是有效解
            if (j <= n && j > i) { // 确保j在有效范围内且不是i本身
                minLength = Math.min(minLength, j - i);
            }
        }
        // 如果没有找到有效解，返回0（这里可能需要根据实际需求调整，例如返回-1或抛出异常）
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    // 主函数，用于测试
    public static void main(String[] args) {
        // ...（保持不变）
    }
}