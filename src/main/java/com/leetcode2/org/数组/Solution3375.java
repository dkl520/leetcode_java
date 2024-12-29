package com.leetcode2.org.数组;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution3375 {
    public int minOperations(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toSet()).stream().toList();
        if (list.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE) < k) return -1;
        return (int) list.stream().filter(a -> a > k).count();
    }

    public static void main(String[] args) {
        int[] nums = {2};
        int k = 1;
        Solution3375 solution3375 = new Solution3375();
        System.out.println(
                solution3375.minOperations(nums, k)
        );

    }


}
