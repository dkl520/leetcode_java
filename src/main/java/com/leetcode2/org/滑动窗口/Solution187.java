package com.leetcode2.org.滑动窗口;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Solution187 {

    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<String>();
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        set1.add(s.substring(0, 10));
        for (int i = 1; i < s.length() - 9; i++) {
            String line = s.substring(i, i + 10);
            if (set1.contains(line)) {
                set2.add(s.substring(i, i + 10));
            } else {
                set1.add(line);
            }
        }
        return new ArrayList<String>(set2);
    }


    public static void main(String[] args) {
        String s = "AAAAAAAAAAA";
        System.out.println(new Solution187().findRepeatedDnaSequences(s));
    }
}
