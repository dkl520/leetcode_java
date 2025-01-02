//package com.leetcode2.org.周赛3;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Solution1955 {
//    public int countSpecialSubsequences(int[] nums) {
//        int n = nums.length;
//        boolean start = false;
//        List<int[]> list = new ArrayList<>();
////        List<Integer> curBlank = new ArrayList<>(2);
//        int[] curBlank = new int[2];
//        int curStart = 0;
//        for (int i = 0; i < n; i++) {
//            if (!start) {
//                if (nums[i] == 0) {
//                    start = true;
//                    curBlank[0] = 0;
//                    curStart = i;
//                }
//            } else {
//                if (nums[i] != curBlank[0]) {
//                    curBlank[1] = i - curStart;
//                    list.add(curBlank);
//                    curBlank = new int[2];
//                    curBlank[0] = nums[i];
//                    curStart = i;
//                }
//            }
//        }
//        curBlank[1] = n - curStart;
//        list.add(curBlank);
//
//        System.out.println(list);
//        int MOD = (int) 1e9 + 7;
//        if (list.size() < 3) return 0;
//        int countSize = 0;
//        int[] dp = new int[list.size()];
//
//        for (int i = 0; i < list.size(); i++) {
//            int[] curNode = list.get(i);
//            if (curNode[0] == 0) {
//                dp[i] = Math.max(1, (int) Math.pow(2, curNode[1]) - 1);
//                continue;
//            }
//            for (int j = 0; j < i; j++) {
//                int[] preNode = list.get(j);
//                if (preNode[0] + 1 == curNode[0]) {
//                    dp[i] = dp[i] + dp[j] * Math.max(1, (int) Math.pow(2, curNode[1]) - 1);
//                }
//            }
//
//            if (curNode[0] == 2) {
//                countSize += dp[i];
//                countSize %= MOD;
//            }
//        }
//        return countSize;
//    }
//
//    public static void main(String[] args) {
//        Solution1955 solution1955 = new Solution1955();
//        int[] nums = {0, 1, 2, 0, 1, 2};
//        solution1955.countSpecialSubsequences(nums);
//    }
//
//
//}
