package com.leetcode2.org.StringDemo;

import java.util.HashSet;
import java.util.Set;

public class Solution1044 {
    // 用于存储前缀哈希值数组和幂次数组
    long[] h, p;

    /**
     * 查找给定字符串中最长的重复子串
     * @param s 输入的字符串
     * @return 最长重复子串
     */
    public String longestDupSubstring(String s) {
        int base = 131; // 选择的哈希基数
        int n = s.length(); // 字符串长度

        // 初始化幂次数组和前缀哈希数组
        p = new long[n + 1];
        h = new long[n + 1];
        p[0] = 1; // 幂次数组的初始值

        // 预处理前缀哈希值和幂次数组
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base; // 计算幂次数组
            h[i + 1] = h[i] * base + s.charAt(i); // 计算前缀哈希值
        }

        String ans = ""; // 用于存储最终答案
        int left = 0, right = n; // 二分查找的左右边界

        // 二分查找最长重复子串长度
        while (left < right) {
            int mid = (left + right + 1) >> 1; // 取中间值
            String t = check(s, mid); // 检查长度为 mid 的重复子串
            if (!t.isEmpty()) {
                left = mid; // 如果找到长度为 mid 的重复子串，增加左边界
                ans = t; // 更新答案
            } else {
                right = mid - 1; // 否则减小右边界
            }
        }
        return ans;
    }

    /**
     * 检查字符串中是否存在长度为 len 的重复子串
     * @param s 输入的字符串
     * @param len 子串长度
     * @return 找到的重复子串，如果不存在则返回空字符串
     */
    private String check(String s, int len) {
        int n = s.length(); // 字符串长度
        Set<Long> vis = new HashSet<>(); // 用于存储哈希值的集合

        // 遍历所有长度为 len 的子串
        for (int i = 1; i + len - 1 <= n; ++i) {
            int j = i + len - 1; // 子串的结束位置
            long t = h[j] - h[i - 1] * p[j - i + 1]; // 计算子串的哈希值

            // 如果该哈希值已存在，则找到重复子串
            if (vis.contains(t)) {
                return s.substring(i - 1, j); // 返回重复子串
            }

            vis.add(t); // 将当前子串的哈希值加入集合
        }
        return ""; // 如果没有找到重复子串，返回空字符串
    }
}
