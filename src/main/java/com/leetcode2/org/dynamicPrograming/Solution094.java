package com.leetcode2.org.dynamicPrograming;

import java.util.Arrays;

public class Solution094 {
    public int minCut(String s) {
        if (s.length() == 1) return 0;
        if (s.length() == 2) return s.charAt(0) == s.charAt(1) ? 0 : 1;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            dp[j][i] = s.charAt(i) == s.charAt(j);

        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        int[] minCuts = new int[n];
        Arrays.fill(minCuts, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            if (dp[0][i]) {
                minCuts[i] = 0;
                continue;
            }
            for (int j = i ; j >= 1; j--) {
                if (dp[j][i]) {
                    minCuts[i] = Math.min(minCuts[i], minCuts[j - 1] + 1);
                }
            }
        }


        return minCuts[n - 1];
    }

    public static void main(String[] args) {
        String s = "cdd";
        Solution094 solution094 = new Solution094();
        solution094.minCut(s);

    }


}
