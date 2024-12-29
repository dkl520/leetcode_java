//package com.leetcode2.org.系统设计;
//
//import java.util.Comparator;
//import java.util.TreeSet;
//
//public class Solution1187 {
//
//    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
//        TreeSet<Integer> treeSet = new TreeSet<>();
//        for (int i : arr2) {
//            treeSet.add(i);
//        }
//
//        for (int i = 1; i < arr1.length; i++) {
//            int cur = arr1[i];
//            int pre = arr1[i - 1];
//            if (cur <= pre) {
//                arr1[i - 1] = treeSet.lower(cur);
//                treeSet.higher(arr1[i - 2]);
//            }
//
//        }
//
//
//    }
//
//}
