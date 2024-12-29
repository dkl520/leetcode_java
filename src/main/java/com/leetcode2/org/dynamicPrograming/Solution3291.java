package com.leetcode2.org.dynamicPrograming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution3291 {

    public int minValidStrings(String[] words, String target) {
        Set<String> sets = new HashSet<String>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(c);
                sets.add(sb.toString());
            }
        }
        int[] dp = new int[target.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < target.length(); i++) {
            if (sets.contains(target.substring(0, i + 1))) {
                dp[i] = 1;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && sets.contains(target.substring(j + 1, i + 1))) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[target.length() - 1] == Integer.MAX_VALUE ? -1 : dp[target.length() - 1];


    }

    public static void main(String[] args) {
        String[] words = new String[]{"adaeabcabdcaabbeceeadeaebcdddeadcbceeeadddabdc", "a"};
        String target = "beeea";
        System.out.println(new Solution3291().minValidStrings(words, target));


    }


}
