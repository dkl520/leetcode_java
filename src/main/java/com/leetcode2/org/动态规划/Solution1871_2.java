package com.leetcode2.org.动态规划;

public class Solution1871_2 {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') return false; // 目标不可达
        boolean[] dp = new boolean[n];
        dp[0] = true;

        // 滑动窗口内有效范围
        int farthest = 0;
        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue; // 如果当前索引不可达，跳过
            // 计算跳跃范围
            int start = Math.max(i + minJump, farthest + 1);
            int end = Math.min(i + maxJump, n - 1);
            for (int j = start; j <= end; j++) {
                if (s.charAt(j) == '0') {
                    dp[j] = true;
                }
            }
            // 更新滑动窗口
            farthest = Math.max(farthest, end);
            if (dp[n - 1]) return true; // 目标已达，提前返回
        }

        return dp[n - 1];
    }

}
