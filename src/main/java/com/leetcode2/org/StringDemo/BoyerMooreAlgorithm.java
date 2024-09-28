package com.leetcode2.org.StringDemo;

// Boyer-Moore 字符串搜索算法的实现
public class BoyerMooreAlgorithm {

    // 字符集大小，这里假设为ASCII码表的256个字符
    private static final int CHAR_SET_SIZE = 256;

    // Boyer-Moore 搜索算法的实现
    public static void boyerMooreSearch(String text, String pattern) {
        int m = pattern.length();      // 模式串的长度
        int n = text.length();         // 文本串的长度

        // 计算坏字符表
        int[] badChar = badCharHeuristic(pattern);

        int s = 0;  // s 表示文本串中与模式串对齐的位置

        // 当s的位置还可能在模式串长度范围内时，继续搜索
        while (s <= (n - m)) {
            int j = m - 1;

            // 从模式串的末尾向前匹配
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j))
                j--;

            // 如果j小于0，说明模式串在当前位置s匹配成功
            if (j < 0) {
                // 找到匹配，输出匹配的位置
                System.out.println("Pattern found at index " + s);

                // 移动s到下一个可能的位置
                // 如果s+m小于文本串长度，则移动m-badChar[text.charAt(s+m)]，否则移动1位
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                // 否则，当前字符为坏字符，根据坏字符表移动s
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }
    }

    // 坏字符规则的启发式函数，用于计算坏字符表
    private static int[] badCharHeuristic(String pattern) {
        int m = pattern.length();
        int[] badChar = new int[CHAR_SET_SIZE];

        // 初始化数组，所有字符的初始值为模式串的长度m
        // 表示如果字符在模式串中不出现，则移动m位
        for (int i = 0; i < CHAR_SET_SIZE; i++)
            badChar[i] = m;

        // 遍历模式串的每个字符，更新它们在坏字符表中的位置
        // 如果字符在模式串中出现，则更新为最后出现的位置到模式串末尾的距离
        for (int i = 0; i < m - 1; i++)
            badChar[pattern.charAt(i)] = m - 1 - i;

        return badChar;
    }

    public static void main(String[] args) {
        String text = "ABAAABCD";     // 文本串
        String pattern = "ABC";        // 模式串

        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);

        // 调用Boyer-Moore搜索算法
        boyerMooreSearch(text, pattern);
    }
}