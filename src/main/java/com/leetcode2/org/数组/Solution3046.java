package com.leetcode2.org.数组;

import java.util.HashMap;
import java.util.Map;

public class Solution3046 {
    public boolean isPossibleToSplit(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        for (int num : nums) {
            memo.put(num, memo.getOrDefault(num, 0) + 1);
            if (memo.getOrDefault(num, 0) > 2) {
                return false;
            }

        }

        return true;
    }
}
