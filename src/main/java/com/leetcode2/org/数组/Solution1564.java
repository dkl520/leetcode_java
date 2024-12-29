//package com.leetcode2.org.数组;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Solution1564 {
//    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
//
//
//
//    }
//
//    List<Integer> boxesL = new ArrayList<>(Arrays.stream(boxes).boxed().toList());
//    boxesL.sort((a, b) -> b - a);
//
//    int l = boxesL.size();
//    for (int i = n - 1; i >= 0; i--) {
//        int curSpace = warehouse[i];
//        if (boxesL.isEmpty()) {
//            break;
//        }
//        if (curSpace >= boxesL.get(l - 1)) {
//            boxesL.remove(l - 1);
//            l = boxesL.size();
//        }
//    }
//    return boxes.length - boxesL.size();
//}
//}
