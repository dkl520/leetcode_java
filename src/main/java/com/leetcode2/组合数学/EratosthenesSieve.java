package com.leetcode2.组合数学;

import java.util.Arrays;

public class EratosthenesSieve {
    /**
     * 埃拉托色尼筛法求质数
     * 埃拉托色尼筛法的核心思想是：
     * 1. 假设所有数都是质数，初始化标记数组。
     * 2. 从小到大遍历每个数，如果当前数是质数，就标记其所有倍数为非质数。
     * 3. 重复上述过程直到遍历到 sqrt(n)。
     * @param n 范围 [2, n]
     * @return 质数标记数组
     */
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // 假设所有数都是质数
        isPrime[0] = isPrime[1] = false; // 0 和 1 不是质数

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // 标记所有 i 的倍数为非质数
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        int n = 50;
        boolean[] isPrime = sieve(n);
        for (int i = 0; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }
}