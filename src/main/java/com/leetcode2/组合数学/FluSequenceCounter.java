package com.leetcode2.组合数学;

import java.util.Arrays;

public class FluSequenceCounter {
    // 模数，用于取模运算以避免数值溢出
    private static final int MOD = 1_000_000_007;
    // 最大N值，用于预计算阶乘和逆阶乘
    private static final int MAX_N = 100001;
    // 用于存储阶乘的数组
    private long[] factorial;
    // 用于存储逆阶乘的数组
    private long[] invFactorial;
    // 构造函数，初始化时预计算阶乘和逆阶乘
    public FluSequenceCounter() {
        precomputeFactorials();
    }
    // 计算符合条件的序列数量
    public int numberOfSequence(int n, int[] sick) {
        int m = sick.length;
        long result = 1;
        int prev = -1;
        int totalGap = 0;
        // 遍历每个生病的人的位置
        for (int i = 0; i < m; i++) {
            int gap = sick[i] - prev - 1;
            if (gap > 0) {
                result = (result * comb(totalGap + gap, gap)) % MOD;
                if (i > 0) {
                    result = (result * pow(2, gap - 1)) % MOD;
                }
                totalGap += gap;
            }
            prev = sick[i];
        }

        // 计算最后一个生病的人之后的空隙
        int lastGap = n - prev - 1;
        if (lastGap > 0) {
            result = (result * comb(totalGap + lastGap, lastGap)) % MOD;
            totalGap += lastGap;
        }

        return (int) result;
    }

    // 预计算阶乘和逆阶乘
    // 预处理阶乘和逆元数组
    private void precomputeFactorials() {
        factorial = new long[MAX_N];
        invFactorial = new long[MAX_N];
        factorial[0] = invFactorial[0] = 1; // 0的阶乘为1，其逆元也为1
        for (int i = 1; i < MAX_N; i++) {
            factorial[i] = (factorial[i-1] * i) % MOD; // 计算阶乘
        }
        invFactorial[MAX_N - 1] = pow(factorial[MAX_N - 1], MOD - 2); // 使用费马小定理计算最后一个阶乘的逆元
        for (int i = MAX_N - 2; i > 0; i--) {
            if (invFactorial != null) {
                invFactorial[i] = (invFactorial[i+1] * (i+1)) % MOD; // 逆推计算其他阶乘的逆元
            }
        }
    }
    // 计算组合数 C(n, k)
    // 计算组合数C(n, k) = n! / (k!(n-k)!)
    private long comb(int n, int k) {
        if (k > n) return 0; // 如果k大于n，则组合数为0
        return (((factorial[n] * invFactorial[k]) % MOD) * invFactorial[n-k]) % MOD; // 使用阶乘和逆元快速计算组合数
    }

    // 快速幂算法，计算 base^exp % MOD
    private long pow(long base, int exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] sick = new int[]{1};
        FluSequenceCounter counter = new FluSequenceCounter();
        System.out.println(counter.numberOfSequence(n, sick));

    }
}
