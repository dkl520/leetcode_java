package com.leetcode2.org.数组;


import java.util.List;

public class Solution2860 {
    public int countWays(List<Integer> nums) {
        List<Integer> sortedList = nums.stream().sorted(Integer::compareTo).toList();
        int result = 0;
        for (int i = -1; i < sortedList.size(); i++) {
            int num = i + 1;
            if (i == -1) {
                if (sortedList.get(0) > num) {
                    result++;
                }
                continue;
            }
            if (i == sortedList.size() - 1) {
                if (sortedList.get(sortedList.size() - 1) < num) {
                    result++;
                }
                continue;
            }
            if (sortedList.get(i) < num && num < sortedList.get(i + 1)) {
                result++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
//        List<Integer> nums = List.of(6, 0, 3, 3, 6, 7, 2, 7);
        List<Integer> nums = List.of(1, 1);
        Solution2860 solution2860 = new Solution2860();
        System.out.println(solution2860.countWays(nums));
    }
}
