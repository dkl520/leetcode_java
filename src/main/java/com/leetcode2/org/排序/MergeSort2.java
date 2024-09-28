package com.leetcode2.org.排序;

import java.util.ArrayList;
import java.util.List;

public class MergeSort2 {
    public List<Integer> sorts(List<Integer> list) {
        if (list.size() <= 1)
            return list;

        int mid = list.size() / 2;
        List<Integer> left = sorts(list.subList(0, mid));
        List<Integer> right = sorts(list.subList(mid, list.size()));
        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        // 将剩余的元素添加到 merged 中
        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));

        return merged;
    }

    public static void main(String[] args) {
        MergeSort2 mergeSort = new MergeSort2();
        List<Integer> list = new ArrayList<>(List.of(2, 3, 1, 2, 8, 5, 4, 3, 6, 7));
        list = mergeSort.sorts(list);
        System.out.println(list);
    }
}
