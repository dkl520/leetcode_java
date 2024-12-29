package com.leetcode2.数组;

public class Solution3083 {
    public boolean isSubstringPresent(String s) {
        System.out.println(  s.getClass() );
        for (int i = 1; i < s.length(); i++) {
            String subStr = String.valueOf(s.charAt(i) + "" + s.charAt(i - 1));
            if (s.contains(subStr)) return true;

        }
        return false;


    }

    public static void main(String[] args) {
        Solution3083 s = new Solution3083();
        String str = "leetcode";
        s.isSubstringPresent(str);
    }


}
