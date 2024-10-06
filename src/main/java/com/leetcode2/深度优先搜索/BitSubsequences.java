package com.leetcode2.深度优先搜索;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitSubsequences {

    public static List<Integer> findSubsequences(int[] nums) {
        List<List<Integer>> subSequences = new ArrayList<>();
        generateSubsequences(nums, 0, new ArrayList<>(), subSequences);
        Set<Integer> set = new HashSet<>();
        subSequences = subSequences.stream().filter(list -> IntStream.range(1, list.size())
                        .allMatch(i -> list.get(i) >= list.get(i - 1)))
                .collect(Collectors.toList());

        for (int i = 0; i < subSequences.size(); i++) {
            int num = subSequences.get(i).stream().reduce(0, (acc, elem) -> acc | elem);
            set.add(num);
        }
        List<Integer> result = new ArrayList<>(set);
        result.sort(Integer::compare);
        return result;
    }

    private static void generateSubsequences(int[] nums, int index, List<Integer> current, List<List<Integer>> subSequences) {
        // 将当前子序列添加到结果中（空序列也是一个有效的子序列）
        subSequences.add(new ArrayList<>(current));
        // 从当前索引开始，尝试添加每个元素到子序列中
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsequences(nums, i + 1, current, subSequences);
            current.remove(current.size() - 1); // 回溯
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 4, 1};
        List<Integer> result = findSubsequences(nums);
        System.out.println("所有子序列：");
        System.out.println(result);
    }
}