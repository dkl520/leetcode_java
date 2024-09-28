package com.leetcode2.org.StringDemo;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    // 质数，用于计算哈希值时的模
    private final static int PRIME = 101;
    // Rabin-Karp字符串匹配算法
    public List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        long patternHash = createHash(pattern, m - 1);
        long textHash = createHash(text, m - 1);
        // 检查第一个子串
        if (patternHash == textHash && checkEqual(text, pattern, 0, m)) {
            result.add(0);
        }
        // 滑动窗口检查后续子串
        for (int i = 1; i <= n - m; i++) {
            textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
            if (patternHash == textHash && checkEqual(text, pattern, i, m)) {
                result.add(i);
            }
        }
        return result;
    }

    // 计算初始哈希值
    private long createHash(String str, int end) {
        long hash = 0;
        for (int i = 0; i <= end; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    // 重新计算哈希值，移动窗口
    private long recalculateHash(String str, int oldIndex, int newIndex, long oldHash, int patternLen) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLen - 1);
        return newHash;
    }

    // 检查两个子串是否相等
    private boolean checkEqual(String str1, String str2, int start, int len) {
        for (int i = 0; i < len; i++) {
            if (str1.charAt(start + i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RabinKarp rk = new RabinKarp();
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        List<Integer> result = rk.search(text, pattern);
        System.out.println("Pattern found at indices: " + result);
    }
}
