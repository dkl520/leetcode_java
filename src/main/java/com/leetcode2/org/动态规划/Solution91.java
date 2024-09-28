package com.leetcode2.org.动态规划;


public class Solution91 {
    public int numDecodings(String s) {
//        int num = Integer.parseInt(s);
        if (s == null || s.isEmpty()) return 0;
        if (s.charAt(0) == '0') return 0;
        int[] n = new int[s.length() + 1];
        n[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            char alphabet = s.charAt(i);
            String twoAlphabets = s.substring(i - 1, i + 1);
            if (twoAlphabets.equals("00")) {
                return 0;
            }
            int number = Integer.parseInt(twoAlphabets);
            if (alphabet == '0' && number > 26) {
                return 0;
            }
            if (s.charAt(i - 1) == '0' || number > 26) {
                n[i] = n[i - 1];
                continue;
            }
            if (alphabet == '0') {
                if (i >= 2) {
                    n[i] = n[i - 2];
                } else {
                    n[i] = n[i - 1];
                }
                continue;
            }
            if (i <= 1) {
                n[i] = 2;
                continue;
            }
            if (n[i - 1] == 2 && n[i - 2] == 2) {
                n[i] = n[i - 1] * n[i - 2];
                continue;
            }
            n[i] = n[i - 1] + n[i - 2];
        }
        return n[s.length() - 1];
    }

    public static void main(String[] args) {
        Solution91 solution = new Solution91();
        System.out.println(solution.numDecodings("2611055971756562"));
    }
}
