package com.leetcode2.org.StringDemo;

import java.util.Arrays;

public class Solution3292 {

    public int minValidStrings(String[] words, String target) {
        int n = target.length(); // target字符串的长度
        int[] back = new int[n]; // back数组记录从每个位置开始可以匹配的最大前缀长度
        // 遍历输入的每个word
        for (String word : words) {
            // 计算当前word与target拼接后的前缀函数pi数组
            int[] pi = prefixFunction(word, target);
            int m = word.length(); // 当前word的长度
            // 更新back数组，确保back[i]记录的是从位置i开始的最大前缀匹配长度
            for (int i = 0; i < n; i++) {
                back[i] = Math.max(back[i], pi[m + 1 + i]);
            }
        }
        // dp数组用于记录从target的0到i位置需要多少个word才能完全覆盖
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, n + 1, (int) 1e9); // 将dp数组的[1, n]部分初始化为无穷大
        // 遍历target字符串的每个位置，进行动态规划
        for (int i = 0; i < n; i++) {
            // 如果从当前位置向前可以覆盖back[i]长度，则dp[i + 1]可以更新
            dp[i + 1] = dp[i + 1 - back[i]] + 1;
            // 如果dp[i + 1]超过了target的长度，说明无法匹配，直接返回-1
            if (dp[i + 1] > n) {
                return -1;
            }
        }
        return dp[n]; // 返回完全覆盖target所需的最少word数量
    }

    // 计算拼接字符串(word + "#" + target)的前缀函数数组pi
    private int[] prefixFunction(String word, String target) {
        String s = word + "#" + target; // 拼接字符串，使用特殊字符'#'分隔
        int n = s.length(); // 拼接后字符串的长度
        int[] pi = new int[n]; // 前缀函数数组pi，pi[i]表示以i结尾的最长相等前后缀长度
        // 遍历拼接后的字符串，计算前缀函数
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1]; // j为当前最长相等前后缀的长度
            // 如果字符不匹配，回溯到上一个最长前缀的位置
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            // 如果字符匹配，最长相等前后缀长度+1
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j; // 更新pi数组
        }
        return pi; // 返回计算得到的pi数组
    }
}
