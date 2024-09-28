package com.leetcode2.org.哈希表;

public class RollingHashs {

    public static int[] rollingHash(String s, int k) {
        int n = s.length();
        if (n < k) {
            return new int[0];
        }
        int B = 256;    // 基数，通常选择字符集大小，比如ASCII字符集是256
        int M = 1000000007;  // 一个大素数
        // 计算 B^(k-1) % M
        int B_k_minus_1 = 1;
        for (int i = 0; i < k - 1; i++) {
            B_k_minus_1 = (B_k_minus_1 * B) % M;
        }
        // 计算初始窗口的哈希值
        int currentHash = 0;
        for (int i = 0; i < k; i++) {
            currentHash = (B * currentHash + s.charAt(i)) % M;
        }
        // 保存结果
        int[] result = new int[n - k + 1];
        result[0] = currentHash;
        // 滚动计算后续哈希值
        for (int i = 1; i <= n - k; i++) {
            currentHash = (currentHash - s.charAt(i - 1) * B_k_minus_1) % M;
            currentHash = (currentHash * B + s.charAt(i + k - 1)) % M;
            // 保证哈希值为正数
            if (currentHash < 0) {
                currentHash += M;
            }
            result[i] = currentHash;
        }
        return result;
    }
    public static void main(String[] args) {
        String s = "abcabcfg";
        int k = 3;
        int[] hashes = rollingHash(s, k);

        System.out.print("所有长度为 " + k + " 的子字符串的哈希值: ");
        for (int hash : hashes) {
            System.out.print(hash + " ");
        }
    }
}
