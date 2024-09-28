package com.leetcode2.org.栈;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class Solution496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<Integer>();
        int n2 = nums2.length;
        int[] result = new int[n2];
        for (int i = n2 - 1; i >= 0; i--) {
            int num = nums2[i];

            if (stack.isEmpty()) {
                result[i] = -1;
                stack.push(num);
                continue;
            }

            if (stack.peek() > num) {
                result[i] = stack.peek();
            } else {
                while (!stack.isEmpty() && stack.peek() <= num) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = stack.peek();
                }
            }
            stack.push(num);
        }


        return  Arrays.stream(nums1).map(v -> result[IntStream.range(0, nums2.length).filter(i -> nums2[i] == v).findFirst().orElse(-1)]).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        Solution496 solution496 = new Solution496();
        int[] result = solution496.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
