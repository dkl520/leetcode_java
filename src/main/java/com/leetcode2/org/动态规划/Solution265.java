package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution265 {
    public int minCostII(int[][] costs) {
        int m = costs.length;
        int n = costs[0].length;
        int[][] dp = new int[m + 1][n];
        Arrays.fill(dp[1], Integer.MAX_VALUE);
        for (int i = 1; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int result = Integer.MAX_VALUE;
        int[] indexs = new int[2];

        for (int i = 1; i <= m; i++) {
            int[] newminList = new int[2];
            int[] newindexs = new int[2];
            Arrays.fill(newminList, Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                if (j != indexs[0]) {
                    dp[i][j] = dp[i - 1][indexs[0]] + costs[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][indexs[1]] + costs[i - 1][j];
                }
                if (dp[i][j] < newminList[1]) {
                    if (dp[i][j] < newminList[0]) {
                        newminList[1]= newminList[0];
                        newminList[0] = dp[i][j];
                        newindexs[1] = newindexs[0];
                        newindexs[0] = j;
                    }else{
                        newminList[1] = dp[i][j];
                        newindexs[1] = j;
                    }
                }
                if (i == m) {
                    result = Math.min(result, dp[i][j]);
                }
            }
            indexs = newindexs;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution265 solution265 = new Solution265();
        int[][] costs = {
                {15,17,15,20,7,16,6,10,4,20,7,3,4},
                {11,3,9,13,7,12,6,7,5,1,7,18,9}
        };

        System.out.println(solution265.minCostII(costs));

    }
}
