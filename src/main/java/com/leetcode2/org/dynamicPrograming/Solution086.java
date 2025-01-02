package com.leetcode2.org.dynamicPrograming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution086 {

    Map<String, Boolean> memo;

    public boolean isPalindrome(String s) {
        // 1. 过滤掉非字母数字字符，并将所有字符转换为小写
        if (memo.containsKey(s)) {
            return memo.get(s);
        }


        StringBuilder filtered = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }
        // 2. 检查是否是回文
        int left = 0, right = filtered.length() - 1;
        while (left < right) {
            if (filtered.charAt(left) != filtered.charAt(right)) {
                memo.put(s, false);
                return false;
            }
            left++;
            right--;
        }
        memo.put(s, true);
        return true;
    }

    //    public List<List<String>> partition(String s) {
//        memo = new HashMap<>();
//        List<List<String>> result = new ArrayList<>();
//        trackBack(0, s, new ArrayList<String>(), result);
//
//        return result;
//    }
    public String[][] partition(String s) {
        memo = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        trackBack(0, s, new ArrayList<String>(), result);

        return result.stream()
                .map(list -> list.toArray(String[]::new))
                .toArray(String[][]::new);
    }

    void trackBack(int start, String original, List<String> cur, List<List<String>> result) {

        if (start >= original.length()) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 1; i <= (original.length() - start); i++) {
            String nextStr = original.substring(start, start + i);

            if (isPalindrome(nextStr)) {
                cur.add(nextStr);
                trackBack(start + i, original, cur, result);
                cur.remove(cur.size() - 1);
            }
        }


    }


    public static void main(String[] args) {
        String s = "google";

        Solution086 solution086 = new Solution086();

        System.out.println(solution086.partition(s));


    }


}
