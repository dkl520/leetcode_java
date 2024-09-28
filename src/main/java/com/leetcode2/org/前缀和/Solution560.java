package com.leetcode2.org.前缀和;

public class Solution560 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) {
            return nums[0] == k ? 1 : 0;
        }
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefixSum[j] - prefixSum[i] == k) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(new Solution560().subarraySum(nums, k));
    }
}
