package com.leetcode2.org.数组;

import java.util.Arrays;
import java.util.*;

public class Solution2708 {


    public long maxStrength(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        long result = 1;
        List<Integer> listLeft = new ArrayList<Integer>();
        List<Integer> listRight = new ArrayList<Integer>();
        boolean hasZero = false;
        for (int num : nums) {
            if (num < 0) {
                listLeft.add(num);
            }
            if (num > 0) {
                listRight.add(num);
            }
            if (num != 0) {
                result *= num;
            } else {
                hasZero = true;
            }
        }

        listLeft.sort((a, b) -> b - a);
        if (hasZero && result < 0 && listLeft.size() < 2 && listRight.isEmpty()) return 0;
        if (hasZero && listLeft.isEmpty() && listRight.isEmpty()) return 0;
        result = result < 0 ? result / listLeft.get(0) : result;
        return result;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, -1, -5, 2, 5, -9};
        System.out.println(new Solution2708().maxStrength(nums));

    }
}
