package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution2742_3 {
    // 墙的数量
    int n;
    // 每面墙的粉刷成本
    int[] cost;
    // 每面墙需要的粉刷时间
    int[] time;
    // 动态规划数组，dp[i][j]表示在第i面墙，已经花费了j个时间单位时的最小成本
    int[][] dp;

    /**
     * 计算粉刷所有墙的最小成本
     * @param cost 每面墙的粉刷成本
     * @param time 每面墙需要的粉刷时间
     * @return 粉刷所有墙的最小成本
     */
    public int paintWalls(int[] cost, int[] time) {
        this.cost = cost;
        this.time = time;
        this.n = cost.length; // 墙的数量
        dp = new int[n + 1][n * 2 + 1]; // 初始化dp数组，+1处理边界情况，+n保证时间充足
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1); // 初始化为-1，表示未计算
        }
        return dfs(0, 0); // 从第0面墙开始，已花费0个时间单位
    }

    /**
     * 深度优先搜索，辅助函数
     * @param timeCnt 已经花费的时间单位
     * @param index 当前正在考虑的墙的索引
     * @return 在当前状态下，粉刷剩余墙的最小成本
     */
    public int dfs(int timeCnt, int index) {
        // 限制 timeCnt 的最大值
        timeCnt = Math.min(timeCnt, 2 * n);

        // 如果dp[index][timeCnt]已经被计算过，则直接返回结果
        if (dp[index][timeCnt] != -1) {
            return dp[index][timeCnt];
        }

        // 剪枝：如果时间已经足够粉刷所有墙，则不需要再花费成本
        if (timeCnt >= n) {
            return 0;
        }

        // 如果已经考虑了所有墙，但时间还未用完，则无法满足要求，返回一个较大的值表示不可行
        if (index == n) {
            return Integer.MAX_VALUE / 2; // 返回一个远大于可能成本的值
        }

        // 考虑两种情况：粉刷当前墙或不粉刷当前墙
        int notPaint = dfs(timeCnt, index + 1);
        int paint = dfs(Math.min(timeCnt + 1 + time[index], 2 * n), index + 1) + cost[index];
        int ans = Math.min(notPaint, paint);

        dp[index][timeCnt] = ans; // 存储结果到dp数组
        return ans;
    }

    public static void main(String[] args) {
        int[] cost = {42, 8, 28, 35, 21, 13, 21, 35};
        int[] time = {2, 1, 1, 1, 2, 1, 1, 2};
        Solution2742_3 solution = new Solution2742_3();
        System.out.println(solution.paintWalls(cost, time)); // 输出最小开销
    }
}