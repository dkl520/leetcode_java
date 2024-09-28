package com.leetcode2.org.哈希表;

import java.util.*;

public class Solution290 {

    public boolean wordPattern(String pattern, String s) {
        Map<String, String> map = new HashMap<String, String>();
        String[] strList = s.split(" ");
        if (pattern.length() != strList.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            String c = String.valueOf(pattern.charAt(i));
            String curStr = strList[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(curStr)) return false;
            } else {
                if (map.containsValue(curStr)) {
                    return false;
                }
                map.put(c, curStr);
            }

        }
        return true;

    }

    public static void main(String[] args) {
//        String pattern = "abc";
//        String s = "c b a";
        String pattern = "abba";
        String s = "dog cat cat dog";
        System.out.println(new Solution290().wordPattern(pattern, s));
    }
}
