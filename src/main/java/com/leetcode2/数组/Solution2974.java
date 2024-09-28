package com.leetcode2.数组;

import java.util.*;

public class Solution2974 {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>(Arrays.stream(nums).boxed().toList());
        for (int i = 0; i < nums.length - 1; i += 2) {
            Collections.swap(list, i, i + 1);

            System.out.println(list);
        }

        int[] array3 = Arrays.stream(list.toArray(Integer[]::new)).mapToInt(i -> i).toArray();
        return array3;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 2, 3};
//        Integer[] numss = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(new Solution2974().numberGame(nums)));

    }

}
