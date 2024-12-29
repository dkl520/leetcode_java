package com.leetcode2.org.dynamicPrograming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1547 {
    public int minCost(int n, int[] cuts) {
        // 在cuts数组两端加入0和n
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int cut : cuts) {
            list.add(cut);
        }
        list.add(n);

        // 排序确保切割点有序
        Collections.sort(list);
        int m = list.size();
        // dp[i][j]表示切割区间[i,j]的最小成本
        int[][] dp = new int[m][m];
        // len表示当前考虑的区间长度
        for (int len = 2; len < m; len++) {
            // i是区间起点，j是区间终点
            for (int i = 0; i + len < m; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                // k是区间内的切割点
                for (int k = i + 1; k < j; k++) {
                    // 当前区间的长度是list.get(j) - list.get(i)
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k][j] + list.get(j) - list.get(i));
                }
            }
        }

        return dp[0][m-1];
    }

    public static void main(String[] args) {

    }



}
