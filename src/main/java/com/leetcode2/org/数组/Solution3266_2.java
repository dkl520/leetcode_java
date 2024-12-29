package com.leetcode2.org.数组;

import java.util.*;

public class Solution3266_2 {

    static class NumM {
        int index;
        int val;

        NumM(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    ;

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        Queue<NumM> queue = new PriorityQueue<>((a, b) -> {
            if (a.val == b.val) {
                return a.index - b.index;
            }
            return a.val - b.val;
        });
        int MOD = (int) 1e9 + 7;
        if (nums.length == 0) return nums;

        for (int i = 0; i < nums.length; i++) {
            NumM numM = new NumM(i, nums[i]);
            queue.offer(numM);
        }
        while (k > 0) {
            NumM curNum = queue.poll();
            if (curNum != null) {
                curNum.val = curNum.val * multiplier;
            }
            queue.offer(curNum);
            k--;
        }
        List<NumM> list = new ArrayList<>(queue);
        list.forEach(numM -> numM.val %= MOD);
        list.sort((n1, n2) -> n1.index - n2.index);
        return list.stream().mapToInt(numM -> numM.val).toArray();
    }

    public static void main(String[] args) {
        Solution3266_2 s = new Solution3266_2();
        int[] nums = {2, 1, 3, 5, 6};

        // 定义 k
        int k = 5;

        // 定义 multiplier
        int multiplier = 2;
        System.out.println(
                Arrays.toString(s.getFinalState(nums, k, multiplier))
        );

    }
}
