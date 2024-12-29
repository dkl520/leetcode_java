package com.leetcode2.org.滑动窗口;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Solution239 {
    static class NumIn {
        int num;
        int index;

        public NumIn(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<NumIn> queue = new ArrayDeque<>();
        int n = nums.length;
        int[] result = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            int curNum = nums[i];
            while (!queue.isEmpty()) {
                if (queue.getLast().num < curNum) {
                    queue.removeLast();
                } else {
                    break;
                }
            }
            if (queue.peekFirst() != null && i - queue.peekFirst().index >= k) {
                queue.pollFirst();
            }
            queue.offer(new NumIn(curNum, i));
        }
        result[0] = queue.peekFirst().num;
        for (int i = k; i < n; i++) {
            int curNum = nums[i];
            while (!queue.isEmpty()) {
                if (queue.getLast().num < curNum) {
                    queue.removeLast();
                } else {
                    break;
                }
            }
            if (queue.peekFirst() != null && i - queue.peekFirst().index >= k) {
                queue.pollFirst();
            }
            queue.offer(new NumIn(curNum, i));
            if (queue.peekFirst() != null) {
                result[i - k + 1] = queue.peekFirst().num;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        Solution239 solution239 = new Solution239();
        int[] result = solution239.maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(result));
    }


}
