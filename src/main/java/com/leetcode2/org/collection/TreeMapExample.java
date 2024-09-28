package com.leetcode2.org.collection;

import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2, "Alice");
        treeMap.put(1, "Bob");
        treeMap.put(4, "CHAar");
        String value = treeMap.get(2);
        System.out.println(value);
        for (Map.Entry<Integer, String> map : treeMap.entrySet()) {
            Integer key = map.getKey();
            String values = map.getValue();
            System.out.println(key + "" + values);
        }
        treeMap.replace(2, "22222");

        treeMap.forEach((key, values) -> {
            System.out.println(key + "value" + values);
        });
        Set<Integer> keySet = treeMap.keySet();
        for (Integer key : keySet) {
            System.out.println(key + "kley" + treeMap.get(key));
        }
        TreeMap<Integer, String> treeMap1 = new TreeMap<Integer, String>(treeMap);
        treeMap1.put(2, "2222");
        treeMap1.put(3, "2222234444");
        treeMap1.put(1, "1111111");
        System.out.println(treeMap1);
        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(3, "David");
        sortedMap.put(1, "Alice");
        sortedMap.put(2, "Bob");
        sortedMap.put(4, "John");
        System.out.println(sortedMap);

        SortedMap<Integer, String> sortedMap1 = new TreeMap<>() {{
            put(3, "David");
            put(2, "Bob");
            put(4, "John");
        }};


        System.out.println(sortedMap1);


        // 使用 SortedMap 初始化 TreeMap

    }
}

class CustomComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1.intValue(), o2.intValue());
    }
}