package com.leetcode2.org.数组;

import java.util.*;


public class Solution228 {
    public List<String> summaryRanges(int[] nums) {
        StringBuilder str = new StringBuilder();
        List<String> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : nums) {
            if (!deque.isEmpty() && num != deque.getLast() + 1) {
                if (deque.size() == 1) {
                    list.add(deque.poll().toString());
                } else {
                    list.add(deque.poll() + "->" + deque.removeLast());
                    deque.clear();
                }
            }
            deque.addLast(num);
        }
        if (!deque.isEmpty()) {
            if (deque.size() == 1) {
                list.add(deque.poll().toString());
            }else{
                list.add(deque.poll() + "->" + deque.removeLast());
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 4, 6, 8, 9};
        Solution228 solution228 = new Solution228();
        System.out.println(solution228.summaryRanges(nums));

    }


}
