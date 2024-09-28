package com.leetcode2.org.前缀和;

public class Solution862_2 {
    public int shortestSubarray(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int n = nums.length;
        for (int j = 1; j < prefixSum.length; j++) {
            for (int i = 0; i < n && i + j < n + 1; i++) {
                int sum = prefixSum[j + i] - prefixSum[i];
                if (sum >= k) return j;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] nums = {2, -1, 2};
        int k = 3;
        System.out.println(new Solution862_2().shortestSubarray(nums, k));
    }
}
