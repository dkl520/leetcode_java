package com.leetcode2.org.数组;

import java.util.Arrays;

public class Solution370_2 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        int[] differenceArray = new int[length];
        for (int i = 0; i < updates.length; i++) {
            int[] sliceArr = updates[i];
            int left = sliceArr[0];
            int right = sliceArr[1];
            int increment  = sliceArr[2];
            differenceArray[left] += increment ;
            if (right + 1 < length) {
                differenceArray[right + 1] -= increment ;
            }
        }
        result[0] = differenceArray[0];
        for (int i = 1; i < differenceArray.length; i++) {
            result[i] += differenceArray[i] + result[i - 1];
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        Solution370_2 soluton370 = new Solution370_2();
        System.out.println(Arrays.toString(soluton370.getModifiedArray(5, updates)));
    }


}
