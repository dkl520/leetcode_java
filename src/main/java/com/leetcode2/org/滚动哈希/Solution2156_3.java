package com.leetcode2.org.滚动哈希;

public class Solution2156_3 {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        long curr = 0, pk = 1;
        int n = s.length();
        int firstIndex = 0;

        // Calculate p^(k-1) % modulo
        for (int i = 0; i < k - 1; i++) {
            pk = (pk * power) % modulo;
        }

        // Calculate hash for the last k characters
        for (int i = n - 1; i >= n - k; i--) {
            curr = (curr * power + val(s.charAt(i))) % modulo;
        }

        // Check if the last substring matches the hashValue
        if (curr == hashValue) {
            firstIndex = n - k;
        }

        // Use rolling hash to check other substrings
        for (int i = n - k - 1; i >= 0; i--) {
            // Remove the last character of the previous window
            curr = ((curr - val(s.charAt(i + k)) * pk % modulo + modulo) * power + val(s.charAt(i))) % modulo;

            if (curr == hashValue) {
                firstIndex = i;
            }
        }

        return s.substring(firstIndex, firstIndex + k);
    }

    // Helper function to get the value of a character
    private int val(char c) {
        return c - 'a' + 1;
    }
}