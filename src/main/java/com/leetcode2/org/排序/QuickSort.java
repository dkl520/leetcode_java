package com.leetcode2.org.排序;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public List<Integer> sortsList(List<Integer> list) {
        if (list.size() <= 1) return list;
        if (allElementsEqual(list)) return list;
        int midIndex = list.size() / 2;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < list.get(midIndex)) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        sortsList(left).addAll(sortsList(right));
        return left;
    }

    public static <T> boolean allElementsEqual(List<T> list) {
        if (list.isEmpty()) {
            return true;  // 如果列表为空，认为所有元素相同
        }
        T firstElement = list.get(0);
        return list.stream().allMatch(element -> element.equals(firstElement));
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 4, 2, 4, 6, 8);
        QuickSort quickSort = new QuickSort();
        List<Integer> sortedList = quickSort.sortsList(new ArrayList<>(list));
        System.out.println(sortedList);

    }

}
