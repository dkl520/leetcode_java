package com.leetcode2.org.博弈论;

import java.util.Arrays;

public class Solution1406 {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length; // 获取石头堆的数量
        int[] dp = new int[n + 1]; // 创建动态规划数组，长度为n+1
        Arrays.fill(dp, Integer.MIN_VALUE); // 将dp数组初始化为最小整数值
        int sum = 0; // 初始化sum为0，用于存储从当前位置到末尾的石头总和
        dp[n] = 0; // 设置边界条件，当没有石头时，得分为0
        for (int i = n - 1; i >= 0; i--) { // 从后向前遍历每个石头堆
            sum += stoneValue[i]; // 累加当前石头的价值到sum
            if (i == n - 1) { // 如果是最后一堆石头
                dp[i] = sum; // dp[i]直接等于sum（因为只能拿这一堆）
            } else {
                for (int k = 1; k <= 3 && i + k <= n; k++) { // 尝试拿1、2或3堆石头
                    // 计算当前玩家能获得的最大分数：当前sum减去对手能获得的最大分数
                    dp[i] = Math.max(dp[i], sum - dp[i + k]);
                    System.out.println(dp[i]); // 打印当前dp[i]的值（用于调试）
                }
            }
        }
        // 根据dp[0]（即先手玩家能获得的最大分数）来判断胜负
        if (sum - dp[0] < dp[0]) { // 如果后手玩家的分数小于先手玩家
            return "Alice"; // Alice（先手）获胜
        } else if (sum - dp[0] == dp[0]) { // 如果两个玩家的分数相等
            return "Tie"; // 平局
        } else { // 如果后手玩家的分数大于先手玩家
            return "Bob"; // Bob（后手）获胜
        }
    }

    public static void main(String[] args) {
        // int[] values = {-1, -2, -3}; // 测试用例1（被注释掉了）
        int[] values = {1, 2, 3, 6}; // 当前使用的测试用例
        Solution1406 solution1406 = new Solution1406(); // 创建Solution1406的实例
        System.out.println(solution1406.stoneGameIII(values)); // 打印游戏结果
    }
}