//package com.leetcode2.org.滑动窗口;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//class TypeNum {
//    int num;
//    int frequency;
//
//    public TypeNum(int num, int frequency) {
//        this.num = num;
//        this.frequency = frequency;
//    }
//}
//
//public class FindConsistentLosers {
//
//
//    int ConsistentLosers(int[] userEvent) {
//
//        int n = userEvent.length;
//        PriorityQueue<TypeNum> pq = new PriorityQueue<>((a, b) -> {
//            return Integer.compare(a.frequency, b.frequency);
//        });
//        Set<Integer> setEvent= Arrays.stream(userEvent).boxed().collect(Collectors.toSet());
//
//        for(int num : setEvent){
//            TypeNum typeNum = new TypeNum(num, 0);
//            pq.add(typeNum);
//        }
//
//        int result = 0;
//        for (int i = 0; i < n; i++) {
//
//            for (int j = i + 1; j < n; j++) {
//
//            }
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//        int[] userEvent = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//    }
//
//
//}
