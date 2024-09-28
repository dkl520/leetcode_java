package com.leetcode2.org.树状数组;


import java.util.TreeSet;

public class Solution683_2 {

    int kEmptySlots(int[] bulbs, int k) {
        TreeSet<Integer> flowers = new TreeSet<>();
        for (int i = 0; i < bulbs.length; i++) {
            int position = bulbs[i];
            flowers.add(position);

            // 找到当前花的前一个和后一个种花的位置
            Integer lower = flowers.lower(position); // 前一朵花的位置
            Integer higher = flowers.higher(position); // 后一朵花的位置
            // 检查前一个花之间的距离是否是k
            if (lower != null && position - lower == k + 1) {
                return i + 1;  // 找到了满足条件的天数
            }
            // 检查后一个花之间的距离是否是k
            if (higher != null && higher - position == k + 1) {
                return i + 1;  // 找到了满足条件的天数
            }
        }
        return -1;  // 如果没有找到符合条件的情况
    }

    public static void main(String[] args) {
        int[] bulbs = {1, 3, 2};
        int k = 1;
        Solution683_2 solution = new Solution683_2();
        System.out.println(
                solution.kEmptySlots(bulbs, k)
        );
    }
}
