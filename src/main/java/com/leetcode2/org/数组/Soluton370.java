package com.leetcode2.org.数组;

import java.util.Arrays;

public class Soluton370 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        for (int i = 0; i < updates.length; i++) {
            int[] cur = updates[i];
            int left= cur[0];
            int right= cur[1];
            for (int j = left; j <= right; j++) {
                result[j]+= cur[2];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        Soluton370 soluton370 = new Soluton370();
        System.out.println(Arrays.toString(soluton370.getModifiedArray(5, updates)));
    }


}
