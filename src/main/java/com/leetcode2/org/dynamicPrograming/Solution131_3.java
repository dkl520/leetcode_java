package com.leetcode2.org.dynamicPrograming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution131_3 {
    public List<List<String>> partition(String s) {
        // Use Map to store computed results
        Map<Integer, List<List<String>>> memo = new HashMap<>();
        return partitionHelper(s, 0, memo);
    }

    private List<List<String>> partitionHelper(String s, int start, Map<Integer, List<List<String>>> memo) {
        // Base case: if we've reached end of string
        if (start >= s.length()) {
            List<List<String>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        // Return memoized result if available
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<List<String>> result = new ArrayList<>();

        // Try all possible partitions from current position
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                String palindrome = s.substring(start, end + 1);
                List<List<String>> subPartitions = partitionHelper(s, end + 1, memo);

                for (List<String> sub : subPartitions) {
                    List<String> current = new ArrayList<>();
                    current.add(palindrome);
                    current.addAll(sub);
                    result.add(current);
                }
            }
        }

        memo.put(start, result);
        return result;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}