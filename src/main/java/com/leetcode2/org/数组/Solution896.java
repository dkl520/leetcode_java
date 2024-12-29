//package com.leetcode2.org.数组;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//public class Solution896 {
//    public boolean isMonotonic(int[] nums) {
//        // 直接使用stream转换成List
//        List<Integer> listNum = Arrays.stream(nums).boxed().toList();
//        // 创建并排序一个新List
//        List<Integer> sortedList = new ArrayList<>(listNum);
//        Collections.sort(sortedList);
//        // 直接返回比较结果，不需要if语句
//        return listNum.equals(sortedList) ||
//                listNum.equals(new ArrayList<>(sortedList).reversed());
//    }
//
//
//}
