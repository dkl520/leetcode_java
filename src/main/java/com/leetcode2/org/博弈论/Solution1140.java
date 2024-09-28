package com.leetcode2.org.博弈论;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution1140 {
    public int stoneGameII(int[] piles) {
        // piles数组的长度
        int len = piles.length;
        // 初始化总和变量，用于存储从当前位置到数组末尾的所有石头总数
        int sum = 0;
        // dp数组，dp[i][M]表示从第i堆石头开始，当剩余M堆石头可拿时，当前玩家能够获得的最大石头数
        int[][] dp = new int[len][len + 1];

        // 从后往前遍历piles数组，因为我们需要从后往前计算dp值
        for (int i = len - 1; i >= 0; i--) {
            // 累加从当前位置到数组末尾的石头总数
            sum += piles[i];

            // 遍历不同的M值，即从当前位置开始，剩余M堆石头可拿
            for (int M = 1; M <= len; M++) {
                // 如果当前位置加上可拿走的最大堆数（2*M）超出了数组界限，则当前玩家可以拿走剩余的所有石头
                if (i + 2 * M >= len) {
                    dp[i][M] = sum;
                } else {
                    // 否则，尝试不同的x值（即当前玩家可以选择拿走的石头堆数，范围是1到2*M），计算当前玩家能获得的最大石头数
                    for (int x = 1; x <= 2 * M; x++) {
                        // 当前玩家拿走x堆石头后，对手将面对剩余石头堆数为Math.max(M, x)（因为至少要保证对手还有M堆石头可选）
                        // sum - dp[i + x][Math.max(M, x)]表示当前玩家拿走x堆石头后，对手获得的最大石头数，那么当前玩家获得的就是剩余部分
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                    }
                }
            }
        }
        // 返回从第0堆石头开始，剩余1堆石头可拿时（即游戏开始时），当前玩家能够获得的最大石头数
        return dp[0][1];
    }

    public static void main(String[] args) {
        int[] piles = {2, 7, 9, 4, 4};
        System.out.println(new Solution1140().stoneGameII(piles));
    }
}