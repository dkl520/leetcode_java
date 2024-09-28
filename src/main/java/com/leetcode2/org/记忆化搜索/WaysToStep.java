package com.leetcode2.org.记忆化搜索;

public class WaysToStep {
        public int waysToStep(int n) {
            // Base cases
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (n == 2) return 2;

            long mod = 1000000007;
            long[] dp = new long[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;

            // Fill dp array using bottom-up approach
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % mod;
            }

            return (int) dp[n];
        }



    public static void main(String[] args) {
        WaysToStep w = new WaysToStep();
        System.out.println(w.waysToStep(3));

    }
}
