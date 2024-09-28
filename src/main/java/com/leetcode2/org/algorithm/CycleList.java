package com.leetcode2.org.algorithm;//package org.algorithm; //
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class CycleList {
//    public boolean hasCycle(ListNode head) {
//        Set<ListNode> seen  = new HashSet<ListNode>();
//
//        while (head!=null){
//            boolean result= !seen.add(head);
//            if (result){
//                return true;
//            }
//        }
//        return  false;
//    }
//
//    public static void main(String[] args) {
//        Set<Integer> seenInt= new HashSet<Integer>();
//        seenInt.add(12);
//        boolean result= seenInt.add(12);
//        System.out.println(result);
//    }
//}
