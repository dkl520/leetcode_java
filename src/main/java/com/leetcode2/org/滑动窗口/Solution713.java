package com.leetcode2.org.滑动窗口;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1;
        Deque<Integer> queue = new ArrayDeque<>();
        int result = 0;
        for (int num : nums) {
            product *= num;
            queue.offer(num);
            while (product >= k && !queue.isEmpty()) {
                product /= queue.poll();
            }
            if(queue.isEmpty()) continue;
            if (queue.getLast() == num) {
                result += queue.size();
            }
        }
        return result;
    }


    public static void main(String[] args) {
      int []nums = {10,5,2,6};
        int k = 100;
        System.out.println(new Solution713().numSubarrayProductLessThanK(nums, k));
    }
}
