package com.leetcode2.org.动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2376_2 {
    public int countSpecialNumbers(int n) {
        List<Integer> a = new ArrayList<>();
        while (n > 0) {
            a.add(n % 10); // 将每一位数字存入列表
            n /= 10;
        }

        // 创建dp数组, 用于记忆化搜索
        int[][] f = new int[10][1 << 10];
        for (int i = 0; i < 10; i++) {
            Arrays.fill(f[i], -1);
        }

        // 定义递归函数
        return dfs(a.size() - 1, 0, 1, 1, a, f);
    }

    // dfs 方法:
    private int dfs(int u, int st, int lim, int f0, List<Integer> a, int[][] f) {
        if (u < 0) return f0 == 0 ? 1 : 0;  // 如果遍历完数字，且不是前导零时返回1

        if (lim == 0 && f0 == 0 && f[u][st] >= 0) return f[u][st];  // 记忆化搜索

        int ret = 0;
        int v = lim == 1 ? a.get(u) : 9;  // 当前可选的最大数字

        for (int i = 0; i <= v; ++i) {
            if (f0 == 1 && i == 0) {
                // 如果当前还是前导零，继续递归
                ret += dfs(u - 1, st, lim == 1 && i == v ? 1 : 0, 1, a, f);
            } else if ((st >> i & 1) == 0) {
                // 如果i未在状态st中出现过
                ret += dfs(u - 1, st | (1 << i), lim == 1 && i == v ? 1 : 0, 0, a, f);
            }
        }

        if (lim == 0 && f0 == 0) f[u][st] = ret;  // 存储记忆化结果

        return ret;
    }
}
