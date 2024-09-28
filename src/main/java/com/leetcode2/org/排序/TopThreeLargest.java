package com.leetcode2.org.排序;
import java.util.Arrays;

public class TopThreeLargest {
    public static void main(String[] args) {
        int[] nums = {10, 5, 9, 8, 7, 6, 3, 2, 1, 4};
        int[] topThree = findTopThreeLargest(nums);
        System.out.println("Top three largest numbers are: " + Arrays.toString(topThree));
    }

    public static int[] findTopThreeLargest(int[] nums) {
        if (nums.length < 3) {
            throw new IllegalArgumentException("Array must have at least three elements");
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second) {
                third = second;
                second = num;
            } else if (num > third) {
                third = num;
            }
        }

        return new int[]{first, second, third};
    }
}
