package com.leetcode2.org.数组;

public class Solution1014 {
    public int maxScoreSightseeingPair(int[] values) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                result = Math.max(result, values[i] + values[j] + i - j);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
