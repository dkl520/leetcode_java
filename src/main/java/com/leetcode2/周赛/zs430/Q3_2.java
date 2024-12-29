package com.leetcode2.周赛.zs430;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Q3_2 {
    private Set<String> uniqueSequences;
    private int[] nums;

    public long numberOfSubsequences(int[] nums) {
        this.nums = nums;
        this.uniqueSequences = new HashSet<>();

        // 回溯搜索所有可能的子序列
        backtrack(new ArrayList<>(), 0);

        return uniqueSequences.size();
    }

    private void backtrack(List<Integer> current, int start) {
        // 当找到长度为4的子序列时，检查是否满足条件
        if (current.size() == 4) {
            checkAndAddSequence(current);
            return;
        }

        // 遍历剩余的数字
        for (int i = start; i < nums.length; i++) {
            // 检查是否满足间隔条件
            if (!current.isEmpty() && i - current.get(current.size()-1) <= 1) {
                continue;
            }

            current.add(i);
            backtrack(current, i + 1);
            current.remove(current.size() - 1);
        }
    }

    private void checkAndAddSequence(List<Integer> indices) {
        // 检查是否满足 nums[p] * nums[r] == nums[q] * nums[s]
        int p = indices.get(0);
        int q = indices.get(1);
        int r = indices.get(2);
        int s = indices.get(3);

        // 验证间隔条件
        if (q - p <= 1 || r - q <= 1 || s - r <= 1) {
            return;
        }

        // 验证乘积条件
        if ((long)nums[p] * nums[r] == (long)nums[q] * nums[s]) {
            // 将序列转换为字符串以便去重
            String sequence = String.format("%d,%d,%d,%d", p, q, r, s);
            uniqueSequences.add(sequence);
        }
    }

    public static void main(String[] args) {
        Q3_2 q3 = new Q3_2();
        int[] nums = {3, 4, 3, 4, 3, 4, 3, 4};

        q3.numberOfSubsequences(nums);
    }
}