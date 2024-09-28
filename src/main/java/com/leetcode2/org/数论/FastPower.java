package com.leetcode2.org.数论;

public class FastPower {

    /**
     * 计算 (base^exponent) % mod 的值
     *
     * @param base 底数
     * @param exponent 指数
     * @param mod 模数
     * @return (base^exponent) % mod 的结果
     */

    public static long modExp(long base, long exponent, long mod) {
        long result = 1;  // 初始化结果为1
        base = base % mod;  // 处理base比mod大的情况
        while (exponent > 0) {
            // 如果指数是奇数，将当前的base乘入结果
            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }
            // 指数减半
            exponent = exponent >> 1;
            // base平方
            base = (base * base) % mod;
        }

        return result;
    }

    public static void main(String[] args) {
        long base = 2;
        long exponent = 10;
        long mod = 1000000007;

        System.out.println("Result: " + modExp(base, exponent, mod));  // 输出结果
    }
}
