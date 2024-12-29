package com.leetcode2.org.栈;

import java.util.Arrays;
import java.util.Stack;

public class Solution135 {
    public int candy(int[] ratings) {
        Stack<Integer> stack = new Stack<>();
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.setAll(candies, (i) -> 1);
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                if (ratings[i] > ratings[i - 1]) {
                    candies[i] = Math.max(candies[i], candies[i - 1] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {

            if (i + 1 < n) {
                if (ratings[i] > ratings[i + 1]) {
                    candies[i] = Math.max(candies[i], candies[i + 1] + 1);
                }
            }
        }


        return Arrays.stream(candies).sum();
    }


    public static void main(String[] args) {
        int[] ratings = new int[]{1, 2, 87, 87, 87, 2, 1};
        Solution135 solution135 = new Solution135();
        System.out.println(
                solution135.candy(ratings)
        );


    }

}
