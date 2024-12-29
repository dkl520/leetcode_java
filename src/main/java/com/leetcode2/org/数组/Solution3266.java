//package com.leetcode2.org.数组;
//
//import java.math.BigInteger;
//import java.util.*;
//
//public class Solution3266 {
//    static class Value {
//        BigInteger val;
//        int index;
//
//        Value(int index, BigInteger val) {
//            this.index = index;
//            this.val = val;
//        }
//    }
//
//    public int[] getFinalState(int[] nums, int k, int multiplier) {
//        int n = nums.length;
//        int mod = 1_000_000_007;
//        int[] result = new int[n];
//
//        if (nums.length == 1) {
//            BigInteger cur = BigInteger.valueOf(nums[0]);
//            for (int i = 0; i < k; i++) {
//                cur = cur.multiply(BigInteger.valueOf(multiplier));
//            }
//            cur = cur.mod(BigInteger.valueOf(mod));
//            return new int[]{cur.intValue()};
//        }
//
//
//        Queue<Value> queue = new PriorityQueue<>((a, b) -> {
//            if (a.val.equals(b.val)) {
//                return a.index - b.index;
//            }
//            return a.val.compareTo(b.val);
//        });
//        for (int i = 0; i < n; i++) {
//            queue.offer(new Value(i, BigInteger.valueOf(nums[i])));
//        }
//        while (k > 0) {
//            Value v = queue.poll();
//            v.val = v.val.multiply(BigInteger.valueOf(multiplier));
//            queue.offer(v);
//            k--;
//        }
//        List<Value> list = new ArrayList<>();
//        while (!queue.isEmpty()) {
//            list.add(queue.poll());
//        }
//        list.sort((a, b) -> a.index - b.index);
//
//        return list.stream().mapToInt(vs -> {
//            vs.val = vs.val.mod(BigInteger.valueOf(mod));
//            return vs.val.intValue();
//        }).toArray();
//    }
//
//
//    public static void main(String[] args) {
//
//        int[] nums = {161209470}; // 数组 nums
//        int k = 56851412;                    // 整数 k
//        int multiplier = 39846;           // 整数 multiplier
//        Solution3266 sol = new Solution3266();
//        int[] result = sol.getFinalState(nums, k, multiplier);
//        System.out.println(Arrays.toString(result));
//        String s = "aaa";
////
//    }
//}
