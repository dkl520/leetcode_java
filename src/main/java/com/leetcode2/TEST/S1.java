package com.leetcode2.TEST;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class S1 {
    public static void main(String[] args) {
        int[] numbers = new int[]{5, 2, 5, 2, 2};
        for (int number : numbers) {
            System.out.println("*".repeat(number));
        }

        int[] numbers_copy = new int[numbers.length];
        System.arraycopy(numbers, 0, numbers_copy, 0, numbers.length);
        int[] numbers_c2 = Arrays.copyOf(numbers, numbers.length);
        Set<Integer> sets = Arrays.stream(numbers_c2).boxed().collect(Collectors.toSet());

        System.out.println(Arrays.toString(numbers_copy));
        System.out.println(Arrays.toString(numbers_c2));
    }
}
