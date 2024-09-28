package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution2938 {
    public long minimumSteps(String s) {
        long size = s.length();
//        int blackNums = (int) Arrays.stream(s.chars().filter(v -> v == '1').toArray()).count();
        long targetIndex = size - 1;
        long result = 0;

        for (long i = size - 1; i >= 0; i--) {
            if (s.charAt((int) i) == '1') {
                result += targetIndex - i;
                targetIndex--;
            }

        }
        return result;
    }

    public static void main(String[] args) {

        String s = "101";
        System.out.println(new Solution2938().minimumSteps(s));

    }


}
