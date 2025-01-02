package com.leetcode2.org.数组;

import java.util.*;

public class Solution3404_3 {

    public long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> suf = new HashMap<>();
        // 枚举 c
        for (int i = 4; i < n - 2; i++) {
            int c = nums[i];
            // 枚举 d
            for (int j = i + 2; j < n; j++) {
                int d = nums[j];
                int g = gcd(c, d);
                // 把分子和分母（两个 short）压缩成一个 int
                suf.merge((d / g) << 16 | (c / g), 1, Integer::sum);
            }
        }

        long ans = 0;
        // 枚举 b
        for (int i = 2; i < n - 4; i++) {
            int b = nums[i];
            // 枚举 a
            for (int j = 0; j < i - 1; j++) {
                int a = nums[j];
                int g = gcd(a, b);
                ans += suf.getOrDefault((a / g) << 16 | (b / g), 0);
            }
            // 撤销之前统计的 d'/c'
            int c = nums[i + 2];
            for (int j = i + 4; j < n; j++) {
                int d = nums[j];
                int g = gcd(c, d);
                suf.merge((d / g) << 16 | (c / g), -1, Integer::sum);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
