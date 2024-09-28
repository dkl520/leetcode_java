package com.leetcode2.org.数组;

import java.util.Arrays;
import java.util.Map;

public class Solution3176 {

    public int maximumLength(int[] nums, int k) {
        // 定义最大长度结果变量 ans，初始化为 0
        int ans = 0;
        // 获取数组 nums 的长度 len
        int len = nums.length;
        // 定义一个二维数组 dp，用于存储以第 i 个元素结尾，且相邻数字不同的最大子序列长度
        // dp[i][l] 表示前 i 个数字中，最多允许 l 对相邻数字不同的子序列的最大长度
        int[][] dp = new int[len][51];
        // 初始化 dp 数组，将所有值设置为 -1，表示尚未计算
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 遍历每个元素 i，尝试以该元素结尾的子序列
        for (int i = 0; i < len; i++) {
            // 初始化 dp[i][0] 为 1，表示没有相邻不同的情况下，子序列最小长度为 1
            dp[i][0] = 1;
            // 遍历允许的最多相邻不同对数 l，从 0 到 k
            for (int l = 0; l <= k; l++) {
                // 内层循环遍历每个元素 j，尝试将 nums[j] 和 nums[i] 进行比较
                for (int j = 0; j < i; j++) {
                    // 如果 nums[i] 和 nums[j] 不相同，则增加 1 对不同
                    int add = nums[i] != nums[j] ? 1 : 0;
                    // 检查 l - add 是否有效且 dp[j][l - add] 是否已经计算过
                    if (l - add >= 0 && dp[j][l - add] != -1) {
                        // 更新 dp[i][l]，选择将 j 处的最大子序列长度加上当前元素 i 的情况
                        dp[i][l] = Math.max(dp[i][l], dp[j][l - add] + 1);
                    }
                }
                // 更新结果 ans，取所有 dp[i][l] 的最大值
                ans = Math.max(ans, dp[i][l]);
            }
        }
        // 返回计算得到的最大长度 ans
        return ans;
    }
}
