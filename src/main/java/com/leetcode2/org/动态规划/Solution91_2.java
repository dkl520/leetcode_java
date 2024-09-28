package com.leetcode2.org.动态规划;


public class Solution91_2 {

    /**
     * 计算字符串的解码方式数量
     * @param s 给定的字符串
     * @return 解码方式数量
     */
    public int numDecodings(String s) {
        // 特殊情况处理：空字符串或以0开头的字符串直接返回0
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        // 创建动态规划数组，长度为s.length() + 1
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // 空字符串有一种解码方式
        dp[1] = 1; // 第一个字符有一种解码方式

        for (int i = 2; i <= s.length(); i++) {
            // 当前字符单独解码
            char curr = s.charAt(i - 1);
            if (curr != '0') {
                dp[i] += dp[i - 1];
            }

            // 当前字符和前一个字符组合解码
            char prev = s.charAt(i - 2);
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (prev == '1' || (prev == '2' && curr <= '6')) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        Solution91_2 solution = new Solution91_2();
        System.out.println(solution.numDecodings("2611055971756562")); // 测试样例
    }
}
