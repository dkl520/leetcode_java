package com.leetcode2.org.dynamicPrograming;

// Leetcode300类用于解决LeetCode平台上的第300题——最长递增子序列
public class Leetcode300 {

    // lengthOfLIS方法接收一个整数数组nums作为输入，并返回最长递增子序列的长度
    public int lengthOfLIS(int[] nums) {
        // 如果数组为空，则最长递增子序列的长度为0
        if (nums.length == 0) {
            return 0;
        }
        // 创建一个与输入数组相同长度的数组dp，用于存储以每个元素为结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];

        // 以第一个元素为结尾的最长递增子序列长度为1
        dp[0] = 1;
        // 初始化最长递增子序列的长度为1
        int maxans = 1;
        // 从数组的第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 以当前元素为结尾的最长递增子序列长度至少为1（因为它自身可以构成一个递增子序列）
            dp[i] = 1;
            // 遍历当前元素之前的所有元素
            for (int j = 0; j < i; j++) {
                // 如果当前元素大于之前的某个元素，说明可以将当前元素添加到以该元素为结尾的递增子序列中
                // 从而得到一个更长的递增子序列
                if (nums[i] > nums[j]) {
                    // 更新以当前元素为结尾的最长递增子序列的长度
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新全局的最长递增子序列的长度
            maxans = Math.max(maxans, dp[i]);
        }

        // 返回最长递增子序列的长度
        return maxans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        Leetcode300 leetcode300 = new Leetcode300();
        System.out.println(leetcode300.lengthOfLIS(nums));


    }
}