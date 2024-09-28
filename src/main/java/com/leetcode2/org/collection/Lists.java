package com.leetcode2.org.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
//        String[] arr = new String[]{"AA", "BB", "CC"};
//        Collection list = Arrays.asList(arr);
//        System.out.println(list);
//        List<String> list1 = Arrays.asList("AA", "BB", "CC");
//        System.out.println(list1);
//        Integer[] listI = new Integer[]{1, 2, 3};
//        List<Integer> listI1 = Arrays.asList(listI);
//        int[] listII = new int[]{1, 2, 3};
//        List listII2 = Arrays.asList(listII);
//        System.out.println(listI);
//        System.out.println(listII);
//        Iterator iterator = listI1.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        for (Object obj : listI) {
//            System.out.println(obj);
//        }
//        int[] arr = new int[]{1, 2, 4, 57, 78, 89, 9};
//        for (int values : arr) {
//            System.out.println(values);
//        }
        List<String> listRandom = new ArrayList<>();
        String[] strArr = new String[30];
        for (int i = 0; i < 30; i++) {
            strArr[i] = String.valueOf(Math.ceil((Math.random() * 100)));
        }
        System.out.println(Arrays.toString(strArr));

    }
}
