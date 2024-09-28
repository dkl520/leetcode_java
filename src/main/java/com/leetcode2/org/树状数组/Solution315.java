package com.leetcode2.org.树状数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution315 {
    public List<Integer> countSmaller(int[] nums) {
        // 如果数组为空，返回空列表
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        // 初始化最小值和最大值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 找到数组中的最小值和最大值
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 计算树状数组的大小
        int size = max - min + 1; // 数组大小，范围是 [min, max]
        int[] tree = new int[size + 1]; // 树状数组，+1 是为了方便树状数组的计算
        Integer[] result = new Integer[nums.length]; // 结果数组

        // 从右向左遍历输入数组
        for (int i = nums.length - 1; i >= 0; i--) {
            // 查询当前位置元素的左侧有多少比它小的元素
            int rank = nums[i] - min + 1;
            result[i] = query(rank - 1, tree);
            // 更新树状数组以包含当前元素
            update(rank, tree);
        }

        // 将结果数组转换为列表并返回
        return Arrays.asList(result);
    }

    // 更新树状数组，增加index位置的计数
    private void update(int index, int[] tree) {
        while (index < tree.length) {
            tree[index]++; // 计数加一
            index += index & -index; // 移动到下一个位置
        }
    }

    // 查询树状数组，获取小于index的元素个数
    private int query(int index, int[] tree) {
        int sum = 0; // 计数和
        while (index > 0) {
            sum += tree[index]; // 累加当前计数
            index -= index & -index; // 移动到上一个位置
        }
        return sum;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        Solution315 solution = new Solution315();
        int[] nums1 = {5, 2, 6, 1};
        // 输出 [2, 1, 1, 0]
        System.out.println(solution.countSmaller(nums1));
    }
}
