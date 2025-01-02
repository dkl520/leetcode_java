package com.leetcode2.组合数学;

import java.util.ArrayList;
import java.util.List;

public class EulerSieve {
    /**
     * 欧拉筛法求质数
     * 欧拉筛法的核心思想是：
     * 1. 每个合数只会被其最小质因子筛掉一次。
     * 2. 从小到大遍历每个数，如果当前数是质数，就将其加入质数列表。
     * 3. 对于质数列表中的每个质数 prime，如果 i * prime 超过 n，则退出循环。
     * 4. 否则，将 i * prime 标记为合数。
     * 5. 如果 i 能被 prime 整除，则退出循环，保证每个合数只被最小质因子筛掉一次。
     * @param n 范围 [2, n]
     * @return 质数列表
     */
    public static List<Integer> sieve(int n) {
        boolean[] isPrime = new boolean[n + 1]; // 标记数组，初始值为 false
        List<Integer> primes = new ArrayList<>(); // 存储质数的列表

        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primes.add(i); // i 是质数
            }
            for (int prime : primes) {
                if (i * prime > n) break; // 超出范围，退出循环
                isPrime[i * prime] = true; // 标记为合数
                if (i % prime == 0) break; // 保证每个合数只被最小质因子筛掉一次
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        int n = 50;
        List<Integer> primes = sieve(n);
        System.out.println(primes); // 输出质数列表
    }
}