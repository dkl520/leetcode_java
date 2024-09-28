package com.leetcode2.org.数组;

public class Solution3038 {

    public int maxOperations(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int target = nums[0] + nums[1];
        int result = 1;
        for (int i = 2; i < nums.length; i += 2) {
            if (i + 1 >= nums.length) break;
            if (nums[i] + nums[i + 1] != target) break;
            result += 1;
        }
        return result;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 1, 4, 5};
        System.out.println(new Solution3038().maxOperations(nums));
    }
}
