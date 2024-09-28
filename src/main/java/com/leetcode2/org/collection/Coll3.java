package com.leetcode2.org.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Coll3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> checkedList = Collections.checkedList(list, Integer.class);

// 添加元素到 checkedList
        checkedList.add(10);
        checkedList.add(20);

// 尝试添加非 Integer 类型的元素将抛出 ClassCastException
        checkedList.add(Integer.valueOf("30")); // 抛出异常

        System.out.println(checkedList);
        System.out.println(checkedList);
        List <Integer> list1= new ArrayList<>(List.of(1,2,11,2,5,6,7));
        System.out.println(list1);
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.intValue()- o2.intValue();
            }
        });
        System.out.println(list1);

        List<String> listStr = new ArrayList<>(List.of("Apple","Banana","Orange"));
        listStr.add("orange");
        listStr.set(0,"apple");
        Collections.reverse(listStr);
        List<String> elementsToRemove = List.of("Banana", "Orange");
//        listStr.removeAll(elementsToRemove);//listStr.removeRange()
        System.out.println(listStr);
    }


}
