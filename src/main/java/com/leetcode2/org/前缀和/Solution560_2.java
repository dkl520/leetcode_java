package com.leetcode2.org.前缀和;

import java.util.HashMap;

public class Solution560_2 {
    // 方法：找出数组中所有和为给定值k的子数组个数
    public int subarraySum(int[] nums, int k) {
        // 如果数组为空或长度为0，则不存在符合条件的子数组，返回0
        if (nums == null || nums.length == 0) return 0;
        // 使用HashMap来存储从数组开始到当前位置的前缀和以及对应的出现次数
        // 特别地，初始化时将前缀和为0的次数设为1，以处理从数组起始位置开始的子数组
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        // 当前位置的前缀和
        int currentSum = 0;
        // 符合条件的子数组个数
        int count = 0;
        // 遍历数组中的每个元素
        for (int num : nums) {
            // 更新当前位置的前缀和
            currentSum += num;
            // 检查是否存在一个之前的前缀和，使得当前前缀和减去它等于k
            // 如果存在，说明找到了一个和为k的子数组
            if (prefixSumCount.containsKey(currentSum - k)) {
                // 累加符合条件的子数组个数
                // 注意，如果有多个相同的前缀和（currentSum - k），我们需要累加它们的次数
                count += prefixSumCount.get(currentSum - k);
            }
            // 更新当前前缀和在HashMap中的出现次数
            // 如果HashMap中已存在该前缀和，则将其出现次数加1；否则，设为1
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }
        // 返回符合条件的子数组个数
        return count;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        // 创建一个Solution560_2的实例并调用subarraySum方法
        // 注意：这里应该是new Solution560_2()而不是new Solution560()，因为类名是Solution560_2
        System.out.println(new Solution560_2().subarraySum(nums, k));  // 输出: 2
    }
}