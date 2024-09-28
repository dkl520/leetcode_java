package com.leetcode2.org.StringDemo;

public class Solution796 {

    public boolean rotateString(String s, String goal) {
        int size = s.length();
        while (size > 0) {
            if (s.equals(goal)) return true;
            s = s.substring(1) + s.charAt(0);
            size--;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abcde", goal = "cdeab";
        Solution796 sol = new Solution796();
        System.out.println(sol.rotateString(s, goal));
    }
}
