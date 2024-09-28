package com.leetcode2.org.动态规划;

import java.util.*;

public class FluSequence {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        int n = 5;
        int[] sick = {0, 4};
        System.out.println(countFluSequences(n, sick)); // 输出：4
    }

    public static int countFluSequences(int n, int[] sick) {
        // 标记得感冒的小朋友
        boolean[] isSick = new boolean[n];
        for (int pos : sick) {
            isSick[pos] = true;
        }

        // 计算区间内健康小朋友的数目
        int[] intervals = new int[sick.length + 1];
        int idx = 0, count = 0;

        for (int i = 0; i < n; i++) {
            if (isSick[i]) {
                intervals[idx++] = count;
                count = 0;
            } else {
                count++;
            }
        }
        intervals[idx] = count;

        // 计算每个区间内的感冒序列数目并累乘
        long result = 1;
        for (int length : intervals) {
            if (length > 0) {
                result = (result * factorial(length)) % MOD;
            }
        }

        return (int) result;
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = (result * i) % MOD;
        }
        return result;
    }
}
