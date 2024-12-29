package com.leetcode2.org.动态规划;

public class Solution1871 {
    public boolean canReach(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        int n = s.length();
        dp[0] = true;
        boolean[] visited = new boolean[n];

        dfs(0, s.toCharArray(), dp, minJump, maxJump);
        return dp[n - 1];
    }

    void dfs(int start, char[] list, boolean[] dp, int minJump, int maxJump) {
        if (dp[dp.length - 1]) return;
        for (int i = Math.min(start + maxJump, list.length - 1); i >= start + minJump; i--) {
            if (dp[dp.length - 1]) return;
            if (!dp[i] && list[i] == '0') {
                dp[i] = true;
                if (dp[dp.length - 1]) {
                    System.out.println("dfs 最终时间");
                    return;
                }
                dfs(i, list, dp, minJump, maxJump);
            }
        }

    }


}
