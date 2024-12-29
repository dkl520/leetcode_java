package com.leetcode2.org.dynamicPrograming;

import java.util.Arrays;

public class Solution673 {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
//        dp[0] = 1;
        Arrays.fill(dp, 1);
        int[] distance = new int[n];
        Arrays.fill(distance, 1);
        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] == dp[j] + 1) {
                        distance[i] += distance[j]; // 累加以 j 为结尾的子序列数量
                    } else if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1; // 更新 dp[i]
                        distance[i] = distance[j]; // 重置数量为 j 的数量
                    }
                }
            }
            max = Math.max(max, dp[i]); // 更新全局最大长度
        }

        int result = 0;

        for (int i = 1; i < n; i++) {
            if (dp[i] == max) {
                result += distance[i];
            }
        }
        return result;

    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,4,3,5,4,7,2};
        System.out.println(new Solution673().findNumberOfLIS(nums));
    }


}
