package com.leetcode2.组合数学;


import java.util.Arrays;

public class LCM {
    // 最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 最小公倍数
    public static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

//   欧拉筛法求质数
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

    // 多整数最小公倍数
    public static int lcmMultiple(int[] nums) {
        return Arrays.stream(nums).reduce(1, (a, b) -> lcm(a, b));
    }

    public static void main(String[] args) {
        int[] nums = {12, 15, 20};
        System.out.println("LCM of array: " + lcmMultiple(nums)); // 输出: 60
    }
}
