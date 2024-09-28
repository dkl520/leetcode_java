package com.leetcode2.数组;

public class Solution3101_2 {
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length; // 获取数组的长度
        int[] dp = new int[n]; // 创建一个dp数组来存储以每个位置为结尾的交替子数组的个数
        dp[0] = 1; // 初始化第一个元素的dp值为1，因为单个元素自身构成一个交替子数组
        // 遍历数组，从第二个元素开始
        for (int i = 1; i < n; i++) {
            // 如果当前元素与前一个元素不相等，说明可以形成新的交替子数组，个数为前一个位置的dp值加1（包含当前元素与前一个元素构成的子数组）
            // 如果相等，则只能以当前元素自身构成一个交替子数组，个数为1
            dp[i] = (nums[i - 1] == nums[i]) ? 1 : dp[i - 1] + 1;
        }
        long sum = 0; // 初始化总和为0
        // 遍历dp数组，将所有位置的dp值累加起来，即为所有交替子数组的总数
        for (int i = 0; i < n; i++) {
            sum += dp[i];
        }
        return sum; // 返回所有交替子数组的总数
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 1};
        long ans = new Solution3101_2().countAlternatingSubarrays(nums);
        System.out.println(ans); // 输出应为5
    }
}
