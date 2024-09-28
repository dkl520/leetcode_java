package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution473_2 {

    /**
     * 判断能否用所有火柴拼成一个正方形
     *
     * @param matchsticks 火柴长度数组
     * @return 如果能拼成一个正方形则返回 true，否则返回 false
     */
    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;
        // 如果火柴数量少于 4 根，不可能拼成正方形
        if (n < 4) return false;

        // 计算火柴总长度
        int sum = Arrays.stream(matchsticks).sum();
        // 如果总长度不能被4整除，则不可能拼成正方形
        if (sum % 4 != 0) return false;

        // 每条边的目标长度
        int sideLength = sum / 4;
        // 对火柴长度从大到小排序
        Arrays.sort(matchsticks);
        for (int i = 0; i < n / 2; i++) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[n - 1 - i];
            matchsticks[n - 1 - i] = temp;
        }

        // 状态压缩动态规划数组
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 遍历所有状态
        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == -1) continue;

            for (int i = 0; i < n; i++) {
                // 如果火柴已经使用，跳过
                if ((mask & (1 << i)) != 0) continue;

                int nextMask = mask | (1 << i);
                // 如果当前火柴长度加上之前的长度不超过边长，更新状态
                if (dp[mask] + matchsticks[i] <= sideLength) {
                    dp[nextMask] = (dp[mask] + matchsticks[i]) % sideLength;
                }
            }
        }

        // 检查最后的状态是否符合要求
        return dp[(1 << n) - 1] == 0;
    }

    public static void main(String[] args) {
        Solution473_2 solution473 = new Solution473_2();

        // 示例 1
        int[] matchsticks1 = {1, 1, 2, 2, 2};
        System.out.println(solution473.makesquare(matchsticks1)); // 输出: true

        // 示例 2
        int[] matchsticks2 = {3, 3, 3, 3, 4};
        System.out.println(solution473.makesquare(matchsticks2)); // 输出: false
    }
}
