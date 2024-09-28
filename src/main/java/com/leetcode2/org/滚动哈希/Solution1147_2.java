package com.leetcode2.org.滚动哈希;

import java.util.Arrays;
import java.util.Random;

public class Solution1147_2 {
    // 存储两个哈希算法的前缀和
    long[] pre1;
    long[] pre2;
    // 存储两个哈希算法的幂次，用于计算子串的哈希值
    long[] pow1;
    long[] pow2;
    // 定义两个哈希算法的模数，用于避免哈希冲突
    static final int MOD1 = 1000000007;
    static final int MOD2 = 1000000009;
    // 两个随机选择的基数，用于双哈希算法
    int base1, base2;
    // 用于生成随机数的Random对象
    Random random = new Random();
    /**
     * 计算给定字符串的最长分解长度
     *
     * @param text 输入的字符串
     * @return 最长分解长度
     */
    public int longestDecomposition(String text) {
        init(text); // 初始化哈希相关的数组和基数
        int n = text.length(); // 字符串长度
        int res = 0; // 最长分解长度
        int l = 0, r = n - 1; // 左右指针，用于遍历字符串
        while (l <= r) { // 当左指针小于等于右指针时继续遍历
            int len = 1; // 当前考虑的子串长度
            // 尝试扩展子串长度，直到左右两个子串哈希值相等或子串长度为中间位置
            while (l + len - 1 < r - len + 1) {
                if (Arrays.equals(getHash(l, l + len - 1), getHash(r - len + 1, r))) {
                    res += 2; // 找到一个匹配的分解，增加结果长度
                    break;
                }
                ++len; // 尝试更长的子串
            }
            if (l + len - 1 >= r - len + 1) {
                ++res; // 如果左右子串相邻或重合，则单独作为一个分解
            }
            l += len; // 左指针右移
            r -= len; // 右指针左移
        }
        return res;
    }

    /**
     * 初始化哈希相关的数组和基数
     *
     * @param s 输入的字符串
     */
    public void init(String s) {
        // 随机选择两个基数，确保它们不相等
        base1 = 1000000 + random.nextInt(9000000);
        base2 = 1000000 + random.nextInt(9000000);
        while (base2 == base1) {
            base2 = 1000000 + random.nextInt(9000000);
        }
        int n = s.length(); // 字符串长度
        // 初始化幂次数组和前缀和数组
        pow1 = new long[n];
        pow2 = new long[n];
        pre1 = new long[n + 1];
        pre2 = new long[n + 1];
        pow1[0] = pow2[0] = 1; // 幂次数组初始化为1
        pre1[1] = pre2[1] = s.charAt(0); // 前缀和数组第一个元素为字符串的第一个字符
        // 计算幂次数组和前缀和数组
        for (int i = 1; i < n; i++) {
            pow1[i] = (pow1[i - 1] * base1) % MOD1;
            pow2[i] = (pow2[i - 1] * base2) % MOD2;
            pre1[i + 1] = (pre1[i] * base1 + s.charAt(i)) % MOD1;
            pre2[i + 1] = (pre2[i] * base2 + s.charAt(i)) % MOD2;
        }
    }

    /**
     * 获取指定子串的哈希值（双哈希）
     *
     * @param l 子串的左边界
     * @param r 子串的右边界
     * @return 包含两个哈希值的数组
     */
    // 根据前缀和数组和幂次数组计算子串的哈希值
    public long[] getHash(int l, int r) {
        return new long[]{(pre1[r + 1] - ((pre1[l] * pow1[r + 1 - l]) % MOD1) + MOD1) % MOD1, (pre2[r + 1] - ((pre2[l] * pow2[r + 1 - l]) % MOD2) + MOD2) % MOD2};
    }
    public static void main(String[] args) {
//        String text = "antaprezatepzapreanta";
        String text = "elvtoelvto";
        Solution1147_2 solution1147 = new Solution1147_2();
        System.out.println(solution1147.longestDecomposition(text));
    }
}
