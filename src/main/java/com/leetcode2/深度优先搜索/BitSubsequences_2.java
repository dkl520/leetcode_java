package com.leetcode2.深度优先搜索;


import java.util.*;
import java.util.stream.Collectors;

public class BitSubsequences_2 {
    public static List<Integer> findSubsequences(int[] nums) {
        int n = nums.length;
        List<Set<Integer>> list = new ArrayList<>();
//        Arrays.stream(nums)
//        int[] sss = Arrays.copyOf(nums, n);
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(0);
            int next = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                int prev = nums[j];
                if (next >= prev) {
                    set.add(prev | next);
                }
            }
            set.add(next);
            list.add(set);
        }
     /*   List<Integer> setAll = new ArrayList<>(list.stream().reduce(new HashSet<>(), (acc, v) -> {
            acc.addAll(v);
            return acc;
        }).stream().toList());*/
        return list.stream()
                .flatMap(Collection::stream)
                .distinct().sorted(Integer::compare).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        int[] nums = {4, 2, 4, 1};
        List<Integer> result = findSubsequences(nums);
        System.out.println("所有子序列：");
        System.out.println(result);
    }
}