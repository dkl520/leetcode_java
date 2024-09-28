package com.leetcode2.org.双指针;

public class Solution219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1,2,3};
        int k = 2;
        System.out.println(new Solution219().containsNearbyDuplicate(nums, k));
    }
}
