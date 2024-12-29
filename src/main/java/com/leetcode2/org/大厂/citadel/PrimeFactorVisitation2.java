package com.leetcode2.org.大厂.citadel;

import java.util.*;

public class PrimeFactorVisitation2 {
    // 缓存小素数，避免重复计算
    private static final int[] SMALL_PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

    public int[] getPrimeFactorVisitation(int[] states, int[] numbers) {
        int n = states.length;
        // 使用位图记录需要翻转的位置，避免使用HashMap
        BitSet flipPositions = new BitSet(n + 1);

        for (int number : numbers) {
            // 获取质因数
            Set<Integer> primeFactors = getPrimeFactorsOptimized(number);

            // 更新需要翻转的位置
            for (int prime : primeFactors) {
                for (int pos = prime; pos <= n; pos += prime) {
                    flipPositions.flip(pos);
                }
            }
        }

        // 执行状态翻转
        for (int i = 1; i <= n; i++) {
            if (flipPositions.get(i)) {
                states[i - 1] ^= 1; // 使用异或操作来翻转状态
            }
        }

        return states;
    }

    private Set<Integer> getPrimeFactorsOptimized(int n) {
        Set<Integer> factors = new HashSet<>();

        // 使用预计算的小素数快速分解
        for (int prime : SMALL_PRIMES) {
            if (prime * prime > n) break;

            if (n % prime == 0) {
                factors.add(prime);
                while (n % prime == 0) {
                    n /= prime;
                }
            }
        }

        // 处理剩余的大素数
        if (n > 1) {
            if (n < SMALL_PRIMES[SMALL_PRIMES.length - 1]) {
                factors.add(n);
            } else {
                // 只需检查到sqrt(n)
                int sqrtN = (int) Math.sqrt(n);
                for (int i = SMALL_PRIMES[SMALL_PRIMES.length - 1] + 2; i <= sqrtN; i += 2) {
                    if (n % i == 0) {
                        factors.add(i);
                        while (n % i == 0) {
                            n /= i;
                        }
                    }
                }
                if (n > 1) factors.add(n);
            }
        }

        return factors;
    }

    // 测试代码
    public static void main(String[] args) {
        PrimeFactorVisitation2 solution = new PrimeFactorVisitation2();
        int[] states = {1, 1, 0, 0, 1, 1, 0, 1, 1, 1};
        int[] numbers = {3, 4, 15};

        System.out.println("Original states: " + Arrays.toString(states));
        int[] result = solution.getPrimeFactorVisitation(states, numbers);
        System.out.println("Final states: " + Arrays.toString(result));
    }
}