package com.leetcode2.org.动态规划;

import java.util.HashMap;
import java.util.Map;

public class Solution397_2 {
    public int integerReplacement(int n) {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        int resuilt = helper(n, map);
        return resuilt;
    }

    int helper(long n, Map<Long, Integer> map) {
        if (n == 1) return 0;
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int result;
        if (n % 2 == 0) {
            result = helper(n / 2, map) + 1;
        } else {
            result = 1 + Math.min(helper(n + 1, map), helper(n - 1, map));
        }
        map.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(new Solution397_2().integerReplacement(n));
    }
}

