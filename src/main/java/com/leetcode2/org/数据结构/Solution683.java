package com.leetcode2.org.数据结构;

import java.util.TreeSet;

public class Solution683 {


    public int kEmptySlots(int[] bulbs, int k) {

        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < bulbs.length; i++) {
            int cur = bulbs[i];
            Integer lower = set.lower(cur);
            if (lower != null && (cur - lower.intValue()) == k + 1) {
                return i + 1;
            }
            Integer higher = set.higher(cur);
            if (higher != null && (higher.intValue() - cur) == k + 1) {
                return i + 1;
            }
            set.add(cur);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] bulbs = new int[]{1, 2, 3};
        int k = 1;
        Solution683 sol = new Solution683();
        System.out.println(sol.kEmptySlots(bulbs, k));
    }
}
