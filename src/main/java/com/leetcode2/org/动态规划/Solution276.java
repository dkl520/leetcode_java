package com.leetcode2.org.动态规划;

public class Solution276 {
    public int numWays(int n, int k) {
        // 创建一个两维数组 dp，用于记录到第 i 个标柜时，其他状态的选择方案数
        // dp[i][0] 表示第 i 个标柜和前一个标柜不同色时的方案数
        // dp[i][1] 表示第 i 个标柜和前一个标柜相同色时的方案数
        int[][] dp = new int[n][2];

        // 初始化第一个标柜：可以选择 k 种颜色，但不存在相同色的情况
        dp[0][0] = k; // 第一个标柜不存在前一个标柜，所以只能选 k 种
        dp[0][1] = 0; // 没有前一个标柜，所以不能相同

        // 通过逻辑给出 dp 状态转移方程
        for (int i = 1; i < n; i++) {
            //问题的重点： 前一个颜色无论是什么颜色，既然后一个颜色跟前一个不一样。
            // 那么当前不一样的方案就是 前面的方案数 * （k-1)(前面的方案的某个颜色的选定后的剩余的其他颜色)
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1);
            // 当前标柜与前一个标柜相同色：
            // 前一个标柜必须是 dp[i-1][0] 的情况，因为不能有连续三个相同色标柜
            dp[i][1] = dp[i - 1][0];
        }

        // 返回最后一个标柜在两种状态下的方案和
        return dp[n - 1][0] + dp[n - 1][1];
    }

    public static void main(String[] args) {
        int n = 7;
        int k = 2;
        System.out.println(new Solution276().numWays(n, k));
    }
}
