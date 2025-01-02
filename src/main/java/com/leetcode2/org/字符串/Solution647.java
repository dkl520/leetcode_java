package com.leetcode2.org.字符串;

public class Solution647 {


    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            dp[j][i] = s.charAt(i) == s.charAt(j);
            if (dp[j][i]) {
                count++;
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "aaaaa";
        Solution647 solution647 = new Solution647();
        solution647.countSubstrings(s);


    }

}
