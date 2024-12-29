package com.leetcode2.org.dynamicPrograming;

import java.util.*;
import java.util.stream.Collectors;

public class Solution131_2 {
    public List<List<String>> partition(String s) {
        int n = s.length();
        Map<String, List<List<String>>> memo = new HashMap<>();
        return dfs(s, 0, s.length() - 1, memo);
    }

    List<List<String>> dfs(String s, int left, int right, Map<String, List<List<String>>> memo) {
        String key = "" + left + right;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (left == right) {
            List<String> listC = new ArrayList<>();
            listC.add("" + s.charAt(left));
            memo.computeIfAbsent(key, (keys) -> new ArrayList<>()).add(listC);
            return memo.get(key);
        }
        List<List<String>> listAll = new ArrayList<>();
        for (int i = left; i < right; i++) {
            List<List<String>> listLeft = dfs(s, left, i, memo);
            List<List<String>> listRight = dfs(s, i + 1, right, memo);

            for (List<String> stringList : listLeft) {
                for (List<String> strings : listRight) {
                    List<String> temp = new ArrayList<>();
                    temp.addAll(stringList);
                    temp.addAll(strings);
                    listAll.add(temp);
                }
            }
        }

        if (checkPalindrome(s, left, right)) {
            listAll.add(List.of(s.substring(left, right + 1)));
        }

        List<List<String>> deduplicatedList = listAll.stream()
                .distinct()
                .collect(Collectors.toList());
        memo.put(key, deduplicatedList);

        return deduplicatedList;
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
        Solution131_2 solution131 = new Solution131_2();
        System.out.println(solution131.partition(s));
        System.out.println(solution131.checkPalindrome(s, 0, s.length() - 1));
        System.out.println();
    }
}