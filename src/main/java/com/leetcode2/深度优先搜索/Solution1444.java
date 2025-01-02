package com.leetcode2.深度优先搜索;

import java.util.*;

public class Solution1444 {
    Map<String, Long> memo = new HashMap<>();
    public static final int MOD = 1000000007;

    public int ways(String[] pizza, int k) {
        int m = pizza.length;
        int n = pizza[0].length();
        return (int) dfs(pizza, k, new int[]{0, 0}, m, n);
    }

    // 检查区域内是否有苹果
    private boolean hasApple(String[] pizza, int startRow, int startCol, int endRow, int endCol) {
        for (int i = startRow; i < endRow; i++) {
            String row = pizza[i];
            for (int j = startCol; j < endCol; j++) {
                if (row.charAt(j) == 'A') {
                    return true;
                }
            }
        }
        return false;
    }

    long dfs(String[] pizza, int k, int[] start, int m, int n) {
        String str = Arrays.toString(start) + k;
        if (memo.containsKey(str)) {
            return memo.get(str);
        }
        // 如果当前这块没有苹果，直接返回0
        if (!hasApple(pizza, start[0], start[1], m, n)) {
            return 0;
        }
        // 如果只需要切一块（k=1），且当前块有苹果，就是一种有效方案
        if (k == 1) {
            return 1;
        }
        long sum = 0;
        // 水平切割
        for (int i = start[0]; i < m - 1; i++) {
            // 检查上半部分是否有苹果
            if (hasApple(pizza, start[0], start[1], i + 1, n)) {
                sum = (sum + dfs(pizza, k - 1, new int[]{i + 1, start[1]}, m, n)) % MOD;
            }
        }
        // 垂直切割
        for (int j = start[1]; j < n - 1; j++) {
            // 检查左半部分是否有苹果
            if (hasApple(pizza, start[0], start[1], m, j + 1)) {
                sum = (sum + dfs(pizza, k - 1, new int[]{start[0], j + 1}, m, n)) % MOD;
            }
        }

        memo.put(str, sum);
        return sum;
    }
}