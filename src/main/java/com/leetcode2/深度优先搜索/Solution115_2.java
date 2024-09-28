package com.leetcode2.深度优先搜索;

public class Solution115_2 {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                boolean bol = s.charAt(i - 1) == t.charAt(j - 1);
                if (!bol) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j] + 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][m];
    }


    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        Solution115_2 Solution115 = new Solution115_2();
        System.out.println(Solution115.numDistinct(s, t));
    }
}
