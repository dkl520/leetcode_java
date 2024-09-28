package com.leetcode2.org.博弈论;

public class Solution1140_2 {
        private int[] piles;
        private int[][] dp;
        private int n;

        public int stoneGameII(int[] piles) {
            this.piles = piles;
            this.n = piles.length;
            this.dp = new int[n][n + 1];
            // 计算后缀和
            int[] suffixSum = new int[n + 1];
            for (int i = n - 1; i >= 0; i--) {
                suffixSum[i] = suffixSum[i + 1] + piles[i];
            }
            return dfs(0, 1, suffixSum);
        }

        private int dfs(int i, int M, int[] suffixSum) {
            // 如果已经没有石头可以拿，返回0
            if (i >= n) return 0;

            // 如果剩下的石头可以一次性全部拿走，那就全部拿走
            if (i + 2 * M >= n) return suffixSum[i];

            // 如果已经计算过这种情况，直接返回结果
            if (dp[i][M] != 0) return dp[i][M];

            int min = Integer.MAX_VALUE;
            // 尝试拿走不同数量的石头堆
            for (int X = 1; X <= 2 * M; X++) {
                min = Math.min(min, dfs(i + X, Math.max(M, X), suffixSum));
            }

            // 当前玩家能获得的最大石头数 = 总石头数 - 对手能获得的最小石头数
            dp[i][M] = suffixSum[i] - min;
            return dp[i][M];
        }
    }

//这个解决方案的主要思路如下：
//
//我们使用一个二维数组 dp 来存储中间结果，避免重复计算。dp[i][M] 表示从第 i 堆开始，当前 M 值下能获得的最大石头数。
//我们首先计算后缀和 suffixSum，这样可以快速得到从某一位置到末尾的所有石头数量。
//主要的逻辑在 dfs 方法中：
//如果已经没有石头可以拿，返回0。
//如果剩下的石头可以一次性全部拿走，那就全部拿走。
//如果已经计算过这种情况（即 dp[i][M] 不为0），直接返回结果。

//否则，我们尝试拿走 1 到 2M 堆石头，看哪种方案能让对手拿到的石头最少。

//当前玩家能获得的最大石头数 = 总石头数 - 对手能获得的最小石头数。
//最后，我们返回从第一堆石头开始，初始 M 为1时能获得的最大石头数。
//这个解决方案的时间复杂度是 O(n^2)，空间复杂度也是 O(n^2)，其中 n 是石头堆的数量。
//
//希望这个解释对你有帮助。如果你需要我解释代码中的任何特定部分，请随时告诉我。


