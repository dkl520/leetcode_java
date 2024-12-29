package com.leetcode2.org.大厂.citadel;

import java.util.Arrays;

import java.util.*;

public class MathWithLegoBlocks {

    public int minEqualSum(int[] rowA, int[] rowB) {
        int sumA = Arrays.stream(rowA).sum(); // 计算 rowA 的初始和
        int sumB = Arrays.stream(rowB).sum(); // 计算 rowB 的初始和
        List<Integer> zerosA = new ArrayList<>();
        List<Integer> zerosB = new ArrayList<>();
        // 将两个数组中所有的 0 位置存入对应的列表
        for (int num : rowA) {
            if (num == 0) zerosA.add(1); // 用 1 替换 0
        }

        for (int num : rowB) {
            if (num == 0) zerosB.add(1); // 用 1 替换 0
        }
        // 计算初始的差值
        int diff = Math.abs(sumA - sumB);
        int[] dp = new int[diff + 1]; // dp[i] 表示是否可以得到 i 的差值
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 初始状态，差为 0 时的和为 0
        // 遍历 rowA 和 rowB 的所有 0 替换情况，进行背包填充
        for (int zero : zerosA) {
            for (int j = diff; j >= zero; j--) {
                if (dp[j - zero] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - zero] + zero);
                }
            }
        }

        for (int zero : zerosB) {
            for (int j = diff; j >= zero; j--) {
                if (dp[j - zero] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - zero] + zero);
                }
            }
        }
        return dp[diff] == Integer.MAX_VALUE ? -1 : dp[diff] + Math.max(sumA, sumB);
    }
    public static void main(String[] args) {
        MathWithLegoBlocks solver = new MathWithLegoBlocks();

        int[] rowA = {1, 0, 2};
        int[] rowB = {1, 3, 0, 0};

        int result = solver.minEqualSum(rowA, rowB);
        System.out.println("Minimum possible equal sum: " + result);
    }
}
