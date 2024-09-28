package com.leetcode2.org.滚动哈希;

import java.math.BigInteger;

public class Solution2156_2 {
    /**
     * 根据给定的字符串s，幂power，模数modulo，子串长度k和目标哈希值hashValue，查找并返回匹配的子串。
     * 如果gcd(power, modulo) > 1，则使用subStrHashWithGCD方法，否则使用subStrHashWithoutGCD方法。
     *
     * @param s         原始字符串
     * @param power     幂
     * @param modulo    模数
     * @param k         子串长度
     * @param hashValue 目标哈希值
     * @return 匹配的子串，如果没有找到则返回空字符串
     */
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        return gcd(power, modulo) > 1 ? subStrHashWithGCD(s, power, modulo, k, hashValue)  //走  逆元
                : subStrHashWithoutGCD(s, power, modulo, k, hashValue);  //逆元不存在。
    }

    /**
     * 当gcd(power, modulo) > 1时使用，处理可能存在的模逆元不存在的情况。
     *
     * @param s         原始字符串
     * @param power     幂
     * @param modulo    模数
     * @param k         子串长度
     * @param hashValue 目标哈希值
     * @return 匹配的子串
     */
    private String subStrHashWithGCD(String s, int power, int modulo, int k, int hashValue) {
        // 计算t = power^(k-1) % modulo
        long t = BigInteger.valueOf(power).modPow(BigInteger.valueOf(k - 1), BigInteger.valueOf(modulo)).longValue();
        long val = 0;
        int ans = 0; // 存放找到的子串起始索引

        // 初始化val为最后一个k长度子串的哈希值
        for (int i = 0; i < k; i++) {
            val = (val + (s.charAt(s.length() - 1 - i) - 'a' + 1) *
                    BigInteger.valueOf(power).modPow(BigInteger.valueOf(k - 1 - i), BigInteger.valueOf(modulo)).longValue() % modulo) % modulo;
        }

        // 检查初始的k长度子串是否匹配
        if (val == hashValue) {
            ans = s.length() - k;
        }

        // 从后向前遍历，更新val并检查每个k长度子串
        for (int i = s.length() - 1; i >= k; i--) {
            val = (val - (s.charAt(i) - 'a' + 1) * t % modulo + modulo) % modulo;
            val = val * power % modulo;
            val = (val + s.charAt(i - k) - 'a' + 1) % modulo;
            if (val == hashValue) {
                ans = i - k;
            }
        }

        // 返回匹配的子串
        return s.substring(ans, ans + k);
    }


    /**
     * 无需GCD的字符串子串哈希函数查找
     *
     * @param s         原始字符串
     * @param power     哈希中的幂基数
     * @param modulo    哈希计算的模数，用于防止整数溢出
     * @param k         要查找的子串长度
     * @param hashValue 要匹配的目标哈希值
     * @return 如果找到匹配哈希值的子串，则返回该子串；否则返回空字符串
     */
    private String subStrHashWithoutGCD(String s, int power, int modulo, int k, int hashValue) {
        long v = 0; // 用于计算当前窗口的哈希值

        // 计算初始窗口（前k个字符）的哈希值
        for (int i = 0; i < k; i++) {
            // 使用幂模运算计算每个字符的权重，并累加到哈希值v中
            v = (v + val(s.charAt(i)) * BigInteger.valueOf(power).modPow(BigInteger.valueOf(i), BigInteger.valueOf(modulo)).longValue()) % modulo;
        }

        // 检查初始窗口的哈希值是否与目标哈希值匹配
        if (v == hashValue) {
            return s.substring(0, k); // 匹配，返回前k个字符
        }

        // 滑动窗口查找剩余部分
        for (int i = k; i < s.length(); i++) {
            // 更新哈希值以移除窗口最左边的字符并添加新的字符到窗口右边
            int vals = val(s.charAt(i - k));
            v = (v - vals + modulo) % modulo; // 移除最左边的字符

            v = v * BigInteger.valueOf(power).modInverse(BigInteger.valueOf(modulo)).longValue() % modulo; // 缩放哈希值以适应新窗口大小

            //正常情况下直接 v/ power 即 计算逆元，但是 在当取模的情况下，要计算模逆元！！！！

//            v = (
//                    v + val(s.charAt(i)) * BigInteger.valueOf(power).modPow(BigInteger.valueOf(k - 1), BigInteger.valueOf(modulo)).longValue()
//            ) % modulo; // 添加新字符

            v = (
                    v + ((val(s.charAt(i)) % modulo) * BigInteger.valueOf(power).modPow(BigInteger.valueOf(k - 1), BigInteger.valueOf(modulo)).longValue()) % modulo
            ) % modulo; // 添加新字符

            // 检查新窗口的哈希值是否与目标哈希值匹配
//            .modPow(BigInteger.valueOf(k - 1), BigInteger.valueOf(modulo)) 表示将 power 进行幂运算，并在模 modulo 下取模。具体来说，它计算 power^(k-1) % modulo。

            if (v == hashValue) {

                return s.substring(i - k + 1, i + 1); // 匹配，返回当前窗口的子串
            }
        }

        // 没有找到匹配的子串
        return "";
    }

    /**
     * 将字符转换为对应的整数值（'a'->1, 'b'->2, ..., 'z'->26）
     *
     * @param c 要转换的字符
     * @return 字符对应的整数值
     */
    private int val(char c) {
        return c - 'a' + 1;
    }

    /**
     * 计算两个整数的最大公约数（GCD），但在此方法中未直接使用
     *
     * @param a 第一个整数
     * @param b 第二个整数
     * @return a和b的最大公约数
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        String s = "leetcode";
        int power = 7;
        int modulo = 20;
        int k = 2;
        int hashValue = 0;
        Solution2156_2 solution = new Solution2156_2();
        System.out.println(solution.subStrHash(s, power, modulo, k, hashValue));
    }
}






