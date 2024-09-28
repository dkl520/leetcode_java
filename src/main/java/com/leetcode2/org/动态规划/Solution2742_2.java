package com.leetcode2.org.动态规划;

class Solution2742_2 {
    /**
     * 计算刷完所有墙的最小开销。
     *
     * @param cost 数组，其中cost[i]表示刷第i+1面墙的费用。
     * @param time 数组，其中time[i]表示刷完第i+1面墙后需要等待的冷却时间（即不能连续刷墙的时间）。
     * @return 返回刷完所有墙的最小开销。
     */
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length; // 墙的总数
        int[][] dp = new int[n + 1][n + 1]; // dp数组，dp[i][j]表示刷完前i面墙且最后一个油漆匠离开j时间单位后的最小开销

        // 初始化dp数组，dp[0][i]表示没有墙需要刷时的开销，应为极大值（因为实际情况中不可能没有墙需要刷）
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1_000_000_000; // 初始化为一个足够大的数，表示无效状态
        }
        // 遍历每一面墙
        for (int i = 1; i <= n; i++) {
            // 遍历最后一个油漆匠离开的时间单位
            for (int j = 0; j <= n; j++) {
                // 不使用付费油漆匠刷第i面墙，保持前一状态的最小开销
                dp[i][j] = dp[i - 1][j];
                // 使用付费油漆匠刷第i面墙
                if (j > 0) {
                    // 计算前一个油漆匠可以离开的最早时间（考虑到冷却时间）
                    int prevTime = Math.max(0, j - 1 - time[i - 1]);
                    // 更新dp[i][j]为两种情况中的较小值：不刷或使用付费油漆匠刷
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][prevTime] + cost[i - 1]);
                }
            }
        }
        // 返回刷完所有墙（即前n面墙）且最后一个油漆匠离开任意时间单位后的最小开销
        // 注意：题目中并未明确指出需要最后一个油漆匠何时离开，因此这里取dp[n][n]是一个合理的选择（即假设有足够的时间供最后一个油漆匠离开）
        // 在实际应用中，可能需要进一步分析dp数组以确定最优解的确切含义
        return dp[n][n];
    }


    public static void main(String[] args) {
//        int [] cost = {1,2,3,2};
//        int []time = {1,2,3,2};
//        int[] cost = {8, 7, 5, 15};
//        int[] time = {1, 1, 2, 1};
//        int[] cost = {26,53,10,24,25,20,63,51};
//        int[] time = {1,1,1,1,2,2,2,1};
        int[] cost = {42, 8, 28, 35, 21, 13, 21, 35};
        int[] time = {2, 1, 1, 1, 2, 1, 1, 2};
        Solution2742_2 solution = new Solution2742_2();
        System.out.println(solution.paintWalls(cost, time)); // 输出最小开销

    }
}