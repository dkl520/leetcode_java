package com.leetcode2.org.排序;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    List<Integer> sorts(List<Integer> list) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        List<Integer> left = sorts(list.subList(0, mid));
        List<Integer> right = sorts(list.subList(mid, list.size()));
        List<Integer> merged = new ArrayList<Integer>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }
        return merged;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        List<Integer> list = new ArrayList<>(List.of(2, 3, 1, 2, 8, 5, 4, 3, 6, 7));
        list = mergeSort.sorts(list);
        System.out.println(list);
    }
}
