package com.leetcode2.org.动态规划;

import java.util.*;

public class Solution2954 {
    public int countSickSequences(int n, int[] sick) {
        int MOD = 1000000007;
        int m = sick.length;
        // 记录未感冒小朋友的数量
        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        // 计算未感冒小朋友的数量
        int left = 0;
        for (int i = 0; i < m; i++) {
            // 计算在sick[i]和sick[i+1]之间的未感冒小朋友
            int right = (i == m - 1) ? n : sick[i + 1];
            int count = right - sick[i] - 1; // 计算未感冒的小朋友数量

            if (left < sick[i]) {
                // 计算左边的未感冒小朋友
                dp[sick[i]] = (dp[sick[i]] + factorial(count)) % MOD;
            }

            left = sick[i] + 1;
        }

        // 计算最后的结果
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = (result * dp[i]) % MOD;
        }

        return result;
    }

    private int factorial(int num) {
        if (num == 0 || num == 1) return 1;
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result = (result * i) % 1000000007;
        }
        return result;
    }
}