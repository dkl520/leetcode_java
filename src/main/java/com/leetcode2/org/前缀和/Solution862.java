package com.leetcode2.org.前缀和;

import java.util.*;

public class Solution862 {
    public int shortestSubarray(int[] nums, int k) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            sum += nums[i];
            if (sum >= k) {
                result = 1;
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= k) {
                    result = Math.min(result, j - i + 1);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        int[] nums = {2, -1, 2};
        int k = 3;
        System.out.println(new Solution862().shortestSubarray(nums, k));
    }

}
