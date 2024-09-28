package com.leetcode2.org.动态规划;

import java.util.HashMap;
import java.util.Map;

public class Solution2312 {
    public  long sellingWood(int m, int n, int[][] prices) {
        // 用于存储每个 (hi, wi) 对应的 price
        Map<String, Integer> priceMap = new HashMap<>();
        for (int[] price : prices) {
            String key = price[0] + "," + price[1];
            priceMap.put(key, price[2]);
        }

        // dp[i][j] 表示高为 i 宽为 j 的木块能卖出的最多钱数
        long[][] dp = new long[m + 1][n + 1];

        // 计算 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 尝试不切割直接卖出当前木块
                String key = i + "," + j;
                if (priceMap.containsKey(key)) {
                    dp[i][j] = priceMap.get(key);
                }
                // 尝试沿高度方向切割
                for (int k = 1; k <= i / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                // 尝试沿宽度方向切割
                for (int k = 1; k <= j / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }

        // 返回大小为 m x n 的木块能卖出的最多钱数
        return dp[m][n];
    }

    public static void main(String[] args) {
        int m1 = 3, n1 = 5;
        int[][] prices1 = {{1, 4, 2}, {2, 2, 7}, {2, 1, 3}};
        System.out.println(new Solution2312().sellingWood(m1, n1, prices1)); // 输出：19

        int m2 = 4, n2 = 6;
        int[][] prices2 = {{3, 2, 10}, {1, 4, 2}, {4, 1, 3}};
        System.out.println(new Solution2312().sellingWood(m2, n2, prices2)); // 输出：32
    }
}
