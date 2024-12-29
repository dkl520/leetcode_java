package com.leetcode2.org.数组;

public class Solution3232 {
    public boolean canAliceWin(int[] nums) {
        int sumOne = 0;
        int sumTwo = 0;
        int sumAll = 0;
        for (int i : nums) {
            if (i >= 1 && i < 10) {
                sumOne += i;
            } else if (i >= 10 && i < 100) {
                sumTwo += i;
            }
            sumAll += i;
        }
        if (sumAll - Math.max(sumOne, sumTwo) > Math.max(sumOne, sumTwo)) {
            return false;
        }

        return true;
    }
}
