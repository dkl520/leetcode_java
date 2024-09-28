package com.leetcode2.数组;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Solution3011 {
    public boolean canSortArray(int[] nums) {
        String[] strs = new String[nums.length];
        strs = Arrays.stream(nums).boxed().map(Integer::toBinaryString).toArray(String[]::new);
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int leftMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            leftMax = Math.max(leftMax, nums[i]);
            left[i] = leftMax;
        }

        int rightMin = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            rightMin = Math.min(rightMin, nums[i]);
            right[i] = rightMin;
        }

        List<Integer> listLeft = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            listLeft.add(nums[i]);
            int cur = countOnesInBinary(nums[i]);
            if (i + 1 >= nums.length) break;
            int next = countOnesInBinary(nums[i + 1]);
            if (cur != next) {
                int leftMAX = Collections.max(listLeft);
                if (leftMAX > right[i + 1]) return false;
                listLeft.clear();
            }
        }
        List<Integer> listRight = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            listRight.add(nums[i]);
            int cur = countOnesInBinary(nums[i]);
            if (i - 1 < 0) break;
            int pre = countOnesInBinary(nums[i - 1]);
            if (cur != pre) {
                int rightMIN = Collections.min(listRight);
                if (rightMIN < left[i - 1]) return false;
                listRight.clear();
            }
        }
        return true;
    }

    public static int countOnesInBinary(int number) {
        int count = 0;
        while (number != 0) {
            count += number & 1; // 检查最低位是否为1
            number >>= 1;        // 右移一位
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {179, 93, 247, 127, 191};
        Solution3011 solution3011 = new Solution3011();
        System.out.println(solution3011.canSortArray(nums));
    }
}
