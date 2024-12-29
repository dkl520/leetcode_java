package com.leetcode2.org.dynamicPrograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution131 {
    public List<List<String>> partition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.setAll(dp, (k) -> 0);
        List<List<String>> result = new ArrayList<>();
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (checkPalindrome(s, j, i)) {
                    dp[i+1] += dp[j];
                }

            }
        }
        return result;
    }

    boolean checkPalindrome(String s, int left, int right) {
        int n = right - left + 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        Solution131 solution131 = new Solution131();
        System.out.println(solution131.partition(s));
        System.out.println(solution131.checkPalindrome(s, 0, s.length() - 1));
        System.out.println();
    }
}
