package com.leetcode2.org.StringDemo;

import java.util.ArrayList;
import java.util.List;

// Rabin-Karp字符串搜索算法实现
public class RabinKarp2 {
    // 选择一个质数作为基数，用于计算哈希值
    private static final int PRIME = 101;
    // 一个大质数，用于在计算哈希值时取模，防止整数溢出
    private static final int MOD = 1_000_000_007;
    // 搜索text中是否包含pattern，并返回pattern的起始索引，如果不包含则返回-1
    public static int search(String text, String pattern) {
        int patternLength = pattern.length(); // 模式字符串的长度
        int textLength = text.length(); // 文本字符串的长度
        long patternHash = 0; // 模式字符串的哈希值
        long textHash = 0; // 文本中当前窗口的哈希值
        long highestPower = 1; // PRIME^(patternLength-1) % MOD，用于计算新窗口的哈希值
        // 计算 PRIME^(patternLength-1) % MOD
        for (int i = 0; i < patternLength - 1; i++) {
            highestPower = (highestPower * PRIME) % MOD;
        }
        // 计算pattern和text第一个窗口的哈希值
        for (int i = 0; i < patternLength; i++) {
            patternHash = (patternHash * PRIME + pattern.charAt(i)) % MOD;
            textHash = (textHash * PRIME + text.charAt(i)) % MOD;
        }
        // 滑动窗口，比较哈希值
        for (int i = 0; i <= textLength - patternLength; i++) {
            // 如果哈希值匹配，则进行字符串比较
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i; // 找到匹配，返回起始索引
                }
            }
            // 计算下一个窗口的哈希值
            if (i < textLength - patternLength) {
                // 减去窗口最左边的字符对哈希值的影响，并加上新字符的哈希值
                textHash = (PRIME * (textHash - text.charAt(i) * highestPower) + text.charAt(i + patternLength)) % MOD;
                // 如果哈希值变为负数，则加上MOD以确保其为非负
                if (textHash < 0) {
                    textHash += MOD;
                }
            }
        }
        return -1; // 未找到匹配
    }
    // 主函数，用于测试search方法
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        int result = search(text, pattern);
        if (result != -1) {
            System.out.println("Pattern found at index: " + result);
        } else {
            System.out.println("Pattern not found");
        }
    }
}