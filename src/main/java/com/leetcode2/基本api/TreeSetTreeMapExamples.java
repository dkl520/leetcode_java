package com.leetcode2.基本api;

import java.util.*;

public class TreeSetTreeMapExamples {
    public static void main(String[] args) {
        // TreeSet API Examples

        // 1. 创建和初始化
        TreeSet<Integer> treeSet = new TreeSet<>(Arrays.asList(5, 3, 8, 1, 2));

        // 2. 基本操作
        treeSet.add(6);
        treeSet.remove(3);
        System.out.println(treeSet); // 输出: [1, 2, 5, 6, 8]

        // 3. 获取集合的大小和检查是否包含某个元素
        System.out.println(treeSet.size());       // 输出: 5
        System.out.println(treeSet.contains(5)); // 输出: true

        // 4. 获取第一个和最后一个元素
        System.out.println(treeSet.first());  // 输出: 1
        System.out.println(treeSet.last());   // 输出: 8

        // 5. 子集操作
        System.out.println(treeSet.headSet(5));   // 输出: [1, 2]
        System.out.println(treeSet.tailSet(5));   // 输出: [5, 6, 8]
        System.out.println(treeSet.subSet(2, 6)); // 输出: [2, 5]

        // 6. 遍历
        for (int num : treeSet) {
            System.out.println(num);
        }

        // TreeMap API Examples

        // 1. 创建和初始化
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Alice", 25);
        treeMap.put("Bob", 30);
        treeMap.put("Charlie", 35);

        // 2. 基本操作
        treeMap.put("David", 40);
        treeMap.remove("Bob");
        System.out.println(treeMap); // 输出: {Alice=25, Charlie=35, David=40}

        // 3. 获取键、值和键值对
        System.out.println(treeMap.keySet());   // 输出: [Alice, Charlie, David]
        System.out.println(treeMap.values());  // 输出: [25, 35, 40]
        System.out.println(treeMap.entrySet());// 输出: [Alice=25, Charlie=35, David=40]

        // 4. 获取第一个和最后一个键值对
        System.out.println(treeMap.firstKey());     // 输出: Alice
        System.out.println(treeMap.lastKey());      // 输出: David
        System.out.println(treeMap.firstEntry());   // 输出: Alice=25
        System.out.println(treeMap.lastEntry());    // 输出: David=40

        // 5. 子映射操作
        System.out.println(treeMap.headMap("Charlie")); // 输出: {Alice=25}
        System.out.println(treeMap.tailMap("Charlie")); // 输出: {Charlie=35, David=40}
        System.out.println(treeMap.subMap("Alice", "David")); // 输出: {Alice=25, Charlie=35}  //这个没看懂

        // 6. 遍历
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
