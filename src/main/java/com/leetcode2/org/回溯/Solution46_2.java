package com.leetcode2.org.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution46_2 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> currentPermutation = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(results, currentPermutation, nums, used);
        return results;
    }

    private void backtrack(List<List<Integer>> results, List<Integer> currentPermutation, int[] nums, boolean[] used) {
        if (currentPermutation.size() == nums.length) {
            results.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            currentPermutation.add(nums[i]);
            backtrack(results, currentPermutation, nums, used);
            currentPermutation.remove(currentPermutation.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
//        int[] nums = {1, 2, 3};
        int[] res = new int[10];
        for (int i = 0; i < res.length; i++) {
            res[i] = i;
        }
        Solution46 solution = new Solution46();
        List<List<Integer>> allPermutations = solution.permute(res);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time taken: " + duration + " milliseconds");
//        System.out.println(allPermutations);
    }

}
