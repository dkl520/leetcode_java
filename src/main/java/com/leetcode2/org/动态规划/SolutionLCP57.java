package com.leetcode2.org.动态规划;

import java.util.*;

public class SolutionLCP57 {
    public int getMaximumNumber(int[][] moles) {
        int n = moles.length;
        int[] dp = new int[n + 1];
        int count = 0;
        Arrays.sort(moles, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (dp[j] == 0) continue;
                if (moles[j][0] == moles[i][0]) continue;
                int[] preNut = moles[j];
                boolean isReach = canReach(preNut, moles[i]);
                if (isReach) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    count = Math.max(count, dp[i]);
                }
            }
            if (dp[i] == 0) {
                dp[i] = Math.max(dp[i], canReach(new int[]{0, 1, 1}, moles[i]) ? 1 : 0);
                count = Math.max(count, dp[i]);
            }
        }

        return count;
    }

    boolean canReach(int[] curNut, int[] nextNut) {
        return Math.abs(curNut[1] - nextNut[1]) + Math.abs(curNut[2] - nextNut[2]) <= (nextNut[0] - curNut[0]);
    }

    public static void main(String[] args) {

//        int[][] moles = {
//                {1, 1, 0},
//                {2, 0, 1},
//                {4, 2, 2}
//        };
//
//        int[][] moles = {
//                {2, 0, 2},
//                {5, 2, 0},
//                {4, 1, 0},
//                {1, 2, 1},
//                {3, 0, 2}
//        };
        int[][] moles = {
                {0, 1, 0}, {0, 0, 1}, {0, 2, 1}, {0, 1, 2}, {0, 0, 2},
                {1, 2, 2}, {1, 0, 0}, {1, 0, 2},
                {2, 0, 2}, {2, 2, 2}, {2, 0, 1}, {2, 0, 0}, {2, 2, 0},
                {3, 1, 2}, {3, 0, 0}, {3, 2, 0}, {3, 0, 2}, {3, 2, 2}, {3, 1, 0},
                {4, 0, 1}, {4, 1, 2}, {4, 1, 1}, {4, 0, 2}, {4, 1, 0},
                {5, 0, 1}, {5, 0, 0}, {5, 2, 0}, {5, 0, 2},
                {6, 1, 2}, {6, 0, 0}, {6, 0, 2}, {6, 1, 0}, {6, 2, 1},
                {7, 0, 0}, {7, 2, 0}, {7, 1, 1}, {7, 1, 2}, {7, 2, 1},
                {8, 2, 2}, {8, 0, 1}, {8, 2, 1}, {8, 1, 2}, {8, 1, 1}, {8, 2, 0},
                {9, 1, 1}, {9, 0, 2}, {9, 2, 2}, {9, 1, 0}, {9, 2, 1}, {9, 0, 0}, {9, 2, 0},
                {10, 1, 1}, {10, 0, 2}, {10, 1, 0}, {10, 2, 2}, {10, 2, 1}, {10, 1, 2}, {10, 0, 0}
        };

        SolutionLCP57 solution = new SolutionLCP57();
        System.out.println(solution.getMaximumNumber(moles));
    }
}
