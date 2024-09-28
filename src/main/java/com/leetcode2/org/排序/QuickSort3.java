package com.leetcode2.org.排序;

import java.util.ArrayList;
import java.util.List;

public class QuickSort3 {

    public static List<Integer> quickSort(List<Integer> arr) {
        if (arr.size() <= 1) {
            return arr;
        }

        // 选择基准元素
        int pivot = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // 将数组分成两部分，小于基准的放在左边，大于基准的放在右边
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < pivot) {
                left.add(arr.get(i));
            } else {
                right.add(arr.get(i));
            }
        }

        // 递归地对左右两部分进行快速排序，然后合并结果
        List<Integer> sortedLeft = quickSort(left);
        List<Integer> sortedRight = quickSort(right);
        List<Integer> result = new ArrayList<>(sortedLeft);
        result.add(pivot);
        result.addAll(sortedRight);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(3, 6, 8, 10, 1, 2, 1);
        List<Integer> sortedArr = quickSort(arr);
        System.out.println(sortedArr);
    }
}
