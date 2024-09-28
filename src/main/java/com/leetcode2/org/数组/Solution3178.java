package com.leetcode2.org.数组;

public class Solution3178 {
    public int numberOfChild(int n, int k) {

        if (k <= n - 1) {
            return k;
        }
        while (k >= 0) {
            for (int i = 0; i < n - 1; i++) {
                k--;
                if (k < 0) return i;
            }
            for (int j = n - 1; j > 0; j--) {
                k--;
                if (k < 0) return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Solution3178 solution = new Solution3178();
        System.out.println(solution.numberOfChild(2, 2));
    }
}
