package com.leetcode2.org.动态规划;

import java.util.*;

import java.util.Arrays;

public class Solution1326_3 {

    /**
     * 给定n个水龙头（索引从0到n-1）和每个水龙头的覆盖范围（ranges数组），
     * 其中ranges[i]表示第i个水龙头可以覆盖的左右范围（包括自身）。
     * 求最少需要打开多少个水龙头，才能使得从0到n-1的所有位置都能被水覆盖到。
     * 如果无法覆盖所有位置，则返回-1。
     *
     * @param n       水龙头的总数
     * @param ranges  每个水龙头的覆盖范围数组
     * @return        最少需要打开的水龙头数量，或-1（如果无法覆盖所有位置）
     */
    public int minTaps(int n, int[] ranges) {
        // dp[i]表示覆盖到位置i所需的最少水龙头数量
        // 初始化为n+2，表示一个不可能达到的值，因为水龙头数量不可能超过n
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 2);
        // 位置0不需要水龙头就能覆盖，所以dp[0]为0
        dp[0] = 0;

        // 遍历每个水龙头
        for (int i = 0; i < ranges.length; i++) {
            // 计算当前水龙头能覆盖的左右边界（包含自身）
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            // 遍历当前水龙头能覆盖的所有位置
            for (int j = left + 1; j <= right; j++) {
                // 如果当前位置j已经有一个更小的覆盖方案（即需要更少的水龙头数量），则跳过
                if (dp[j] < dp[left] + 1) continue;
                // 更新位置j的覆盖方案为：通过当前水龙头i覆盖到left位置所需的水龙头数量+1
                dp[j] = dp[left] + 1;
            }
        }

        // 如果dp[n]的值仍然是初始值n+2，说明无法覆盖到位置n，返回-1
        // 否则，返回dp[n]，即覆盖到位置n所需的最少水龙头数量
        return dp[n] < n + 2 ? dp[n] : -1;
    }

}