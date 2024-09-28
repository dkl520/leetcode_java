package com.leetcode2.org.状态压缩;

import java.util.Arrays;

public class Solution698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        int target = sum / k;
        int length = (1 << nums.length);
        Arrays.sort(nums);
        if (nums[nums.length - 1] > target) return false; // Add this line
        int[] arr = new int[length];
        Arrays.fill(arr, -1);
        arr[0] = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] == -1) continue;

            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    continue;
                }
                if (arr[i] + nums[j] <= target) { // Changed this line
                    int nextMask = i | (1 << j);
                    arr[nextMask] = (arr[i] + nums[j]) % target;
                }
            }
        }

        return arr[length - 1] == 0;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(new Solution698().canPartitionKSubsets(nums, k));
    }
}