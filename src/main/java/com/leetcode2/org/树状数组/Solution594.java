package com.leetcode2.org.树状数组;

import java.util.*;

public class Solution594 {
    static class FenwickTree {
        int[] root;

        public FenwickTree(int size) {
            this.root = new int[size + 1];
        }

        int query(int num) {
            int sum = 0;
            while (num > 0) {
                sum += root[num];
                num -= num & -num;
            }
            return sum;
        }

        void update(int num) {

            while (num < root.length) {
                root[num] += 1;
                num += num & -num;
            }
        }

    }

    public int findLHS(int[] nums) {
        Set<Integer> set = new HashSet<>(Arrays.stream(nums).boxed().toList());
        List<Integer> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        FenwickTree tree = new FenwickTree(sortedList.size());
        for (int num : nums) {
            int rank = getRank(num, sortedList);
            tree.update(rank);
        }
        int result = 0;
        for (int i = 0; i < sortedList.size() - 1; i++) {
            if (sortedList.get(i + 1) - sortedList.get(i) >= 2) {
                continue;
            }
            int cur = tree.query(i);
            int next = tree.query(i + 2);
            result = Math.max(result, next - cur);
        }
        return result;
    }


    int getRank(int num, List<Integer> sortedList) {
        return sortedList.indexOf(num) + 1;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        int[] nums = {1, 4, 1, 3, 1, -14, 1, -13};
//        int[] nums = {1, 3, 5, 7, 9, 11, 13, 15, 17};

        Solution594 solution594 = new Solution594();
        System.out.println(solution594.findLHS(nums));

    }
}
