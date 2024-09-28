package com.leetcode2.org.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SteanEXample {
    public static void main(String[] args) {
        int[] arrs = {1, 12, 23, 4, 5};
        List<Integer> integerList = Arrays.stream(arrs).boxed().toList();
        Arrays.sort((arrs));
        int[] arrNext = Arrays.copyOf(arrs, arrs.length);
        for (int num : arrNext) {
            System.out.println(num);
        }
        int[] arr2 = new int[3];
        Arrays.fill(arr2, 1);

        Arrays.stream(arr2).forEach(System.out::println);
        System.out.println(integerList);
        String[] strList = {"sss", "aaa", "bbb"};
        String[] strList2 = {"sss", "aaaa", "ccc"};

        boolean equals = Arrays.equals(strList, strList2);
        System.out.println(equals);
        int[][] array1 = {{1, 2}, {3, 4}};
        int[][] array2 = {{1, 2}, {3, 4}};
        String array1ToStrig = Arrays.deepToString(array2);

        System.out.println(array1ToStrig);

        Integer[] intArray = {5, 3, 9, 1, 7};

        Arrays.sort(intArray, new DescendingComparator());
        System.out.println(Arrays.asList(intArray));
        System.out.println(Arrays.toString(intArray));
        //        Arrays.sort(arrNext,new Comparator<>());
    }
}


class DescendingComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {

        return Integer.compare(o1, o2);
    }

}