package com.leetcode2.org.StringDemo;

// 定义一个类KMPAlgorithm，实现KMP字符串匹配算法
public class KMPAlgorithm {

    // 辅助方法：计算模式串的LPS（最长公共前后缀）数组
    public static int[] computeLPSArray(String pattern) {
        int m = pattern.length(); // 模式串的长度
        int[] lps = new int[m]; // 初始化LPS数组，长度与模式串相同
        int len = 0; // 最长公共前后缀的长度
        int i = 1; // 从模式串的第二个字符开始遍历

        // 遍历模式串
        while (i < m) {
            // 如果当前字符与最长公共前后缀的下一个字符相等
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++; // 延长最长公共前后缀
                lps[i] = len; // 记录当前位置的最长公共前后缀长度
                i++; // 继续向后遍历
            } else {
                // 如果当前字符与最长公共前后缀的下一个字符不相等
                if (len != 0) {
                    // 则将最长公共前后缀长度缩减为LPS数组中前一个值
                    len = lps[len - 1];
                } else {
                    // 如果最长公共前后缀长度已经是0，说明当前位置没有公共前后缀
                    lps[i] = 0;
                    i++; // 继续向后遍历
                }
            }
        }
        return lps; // 返回LPS数组
    }

    // KMP算法的主方法：在文本串中查找模式串
    public static void kmpSearch(String text, String pattern) {
        int n = text.length(); // 文本串的长度
        int m = pattern.length(); // 模式串的长度
        int[] lps = computeLPSArray(pattern); // 计算模式串的LPS数组

        int i = 0, j = 0; // 初始化两个指针，i指向文本串，j指向模式串

        // 遍历文本串
        while (i < n) {
            // 如果当前字符匹配
            if (pattern.charAt(j) == text.charAt(i)) {
                i++; // 文本串指针后移
                j++; // 模式串指针后移
            }

            // 如果模式串指针到达尾部，说明找到匹配
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j)); // 输出匹配位置
                j = lps[j - 1]; // 根据LPS数组回退模式串指针，继续查找可能的匹配
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // 如果当前字符不匹配
                if (j != 0) {
                    // 则根据LPS数组回退模式串指针
                    j = lps[j - 1];
                } else {
                    // 如果模式串指针已经是0，则文本串指针后移
                    i++;
                }
            }
        }
    }

    // 主方法，程序的入口点
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB"; // 文本串
        String pattern = "ABABC"; // 模式串

        System.out.println("Text: " + text); // 输出文本串
        System.out.println("Pattern: " + pattern); // 输出模式串

        kmpSearch(text, pattern); // 调用kmpSearch方法查找模式串
    }
}