package com.leetcode2.org.collection;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList<>();
        coll.add("AA");
        coll.add(123);
        coll.add("AA");
        Collection coll2 = new ArrayList<>();
//        coll2.add('2');
        coll2.addAll(coll);
//        coll.clear();
//        coll.addAll(coll2);
        coll.remove("AA");
        System.out.println(coll);

        System.out.println(coll2);
        System.out.println(coll.equals(coll2));
//        System.out.println(coll2.contains('2'));
//        Collection col3= new
        ArrayList list2 = new ArrayList<>();
//        list2.re
        List list = Arrays.asList(45, 2, 2, 33, 4, 58, 8, 9, 66, 11, 396);
        System.out.println(list + " " + "listssss");
        Collections.reverse(list);
        System.out.println(list + "reverseLists");
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    return ((Integer) o1).intValue() - ((Integer) o2).intValue();
                }
                throw new RuntimeException("类型不匹配");
            }
        });


        List dest = Arrays.asList(new Object[list.size()]);

        Collections.copy(dest, list);

        System.out.println(dest + "ddesttttt");
        System.out.println("从下到大" + list);
        Collections.swap(list, 1, 2);
        System.out.println("交换位置" + list);

//        Collections.sort(list, new Comparator() {
//            @overide;
//            public int compare(Object o1, Object o2) {
//                if (o1 instanceof Integer && o2 instanceof Integer) {
//                    return ((Integer) o1).intValue() - ((Integer) o2).intValue();
//                }
//                throw new RuntimeException("类型不匹配");
//            }
//
//        })


    }


}
