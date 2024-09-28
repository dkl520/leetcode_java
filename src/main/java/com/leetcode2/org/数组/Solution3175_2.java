package com.leetcode2.org.数组;

import java.util.Arrays;

public class Solution3175_2 {
    public int findWinningPlayer(int[] skills, int k) {
        if (k >= skills.length) {
            return findMaxIndex(skills);
        }
        int count = k;
        for (int i = 0; i < skills.length; ) {
            for (int j = i + 1; j < skills.length; ) {
                if (skills[i] < skills[j]) {
                    i = j;
                    j = j + 1;
                    count = k-1;
                } else {
                    j = j + 1;
                    count--;
                    if (count == 0) {
                        return i;
                    }
                }

            }
        }
        return -1;

    }
    public int findMaxIndex(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        int maxIndex = 0;  // 初始化最大值的索引为0
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;  // 更新最大值的索引
            }
        }

        return maxIndex;
    }

    public static void main(String[] args) {
        int[] skills = {4, 2, 6, 3, 9};
        int k = 2;
        System.out.println(new Solution3175_2().findWinningPlayer(skills, k));


    }


}
