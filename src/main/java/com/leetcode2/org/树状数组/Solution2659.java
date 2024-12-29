//package com.leetcode2.org.树状数组;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Solution2659 {
//    static class BIT {
//        int n;
//        int[] cnt;
//
//        public BIT(int n) {
//            this.n = n;
//            cnt = new int[n + 1];
//        }
//
//        int lowBit(int x) {
//            return x & (-x);
//        }
//
//        int query(int x) {
//            int ans = 0;
//            while (x != 0) {
//                ans += cnt[x];
//                x -= lowBit(x);
//            }
//            return ans;
//        }
//
//        void update(int x, int i) {
//            while (x >= n) {
//                cnt[x] += i;
//                x += lowBit(x);
//            }
//        }
//
//
//    }
//
//    public long countOperationsToEmptyArray(int[] nums) {
//        int n = nums.length;
//        int[] list = Arrays.copyOf(nums, n);
//        BIT bit = new BIT(n);
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
////            bit.update(list[i], n - i);
//            map.put(list[i], i);
//        }
//        int [] rankList = new int[n];
//      for (int i = 0; i < n; i++) {
//          rankList[i] = map.get(nums[i]);
//      }
//
//
//
//
//     /*   int ans = 0;
//        for (int i = n; i >= 1; i--) {
//            ans += bit.query(i);
//        }
//        return ans;
//*/
//
//    }
//}
