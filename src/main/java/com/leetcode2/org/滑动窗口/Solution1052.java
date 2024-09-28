package com.leetcode2.org.滑动窗口;

public class Solution1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                count += customers[i];
            }
        }
        int maxChange = 0;
        for (int i = 0; i < n; i++) {
            int change = 0;
            for (int j = i; (j < i + minutes) && (j < n); j++) {
                if (grumpy[j] == 1) {
                    change += customers[j];
                }
            }
            maxChange = Math.max(maxChange, change);
        }
        return maxChange + count;
    }

    public static void main(String[] args) {
//        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
//        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
//        int minutes = 3;
        int[] customers = {8, 8};
        int[] grumpy = {1, 0};
        int minutes = 2;
        Solution1052 solution1052 = new Solution1052();
        int max = solution1052.maxSatisfied(customers, grumpy, minutes);
        System.out.println(max);
    }
}
