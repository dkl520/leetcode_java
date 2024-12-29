package com.leetcode2.org.数组;

public class Solution3258 {

    public int countKConstraintSubstrings(String s, int k) {
        int result = 0;
        if (k == 0 || s.isEmpty()) return result;
        for (int i = 0; i < s.length(); i++) {
            int[] list = new int[2];
            for (int j = i ; j < s.length(); j++) {
//                list[Character.getNumericValue(s.charAt(j))]++;
                list[s.charAt(j) - '0']++;
                if (Math.min(list[0], list[1]) > k) {
                    break;
                }
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "10101";
        int k = 1;
        Solution3258 solution3258 = new Solution3258();
        System.out.println(solution3258.countKConstraintSubstrings(s, k));


    }

}
