package com.leetcode2.深度优先搜索;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subsequences {

    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsequences(nums, 0, new ArrayList<>(), result);

//        Set<Integer> set = new HashSet<>();
//
//        for (int i = 0; i < result.size(); i++) {
//            int num = result.get(i).stream().reduce(0, (acc, elem) -> acc | elem);
//            set.add(num);
//        }

        return result;
    }

    private static void generateSubsequences(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // 将当前子序列添加到结果中（空序列也是一个有效的子序列）
        result.add(new ArrayList<>(current));

        // 从当前索引开始，尝试添加每个元素到子序列中
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsequences(nums, i + 1, current, result);
            current.remove(current.size() - 1); // 回溯
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 4, 1};
        List<List<Integer>> subsequences = findSubsequences(nums);

        System.out.println("所有子序列：");
        for (List<Integer> subsequence : subsequences) {
            System.out.println(subsequence);
        }
    }
}