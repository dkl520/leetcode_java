package com.leetcode2.org.数据结构;

import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        // 创建 TreeMap
        TreeMap<String, Integer> fruitMap = new TreeMap<>();

        // 添加元素
        fruitMap.put("Apple", 10);
        fruitMap.put("Banana", 5);
        fruitMap.put("Orange", 15);
        fruitMap.put("Mango", 20);

        // 打印 TreeMap（会按键的字母顺序排序）
        System.out.println("TreeMap: " + fruitMap);

        // 获取特定键的值
        System.out.println("Value of Apple: " + fruitMap.get("Apple"));

        // 检查键是否存在
        System.out.println("Contains Grape? " + fruitMap.containsKey("Grape"));

        // 获取第一个和最后一个条目
        System.out.println("First entry: " + fruitMap.firstEntry());
        System.out.println("Last entry: " + fruitMap.lastEntry());

        // 获取小于等于某个键的条目
        System.out.println("Floor entry for Cherry: " + fruitMap.floorEntry("Cherry"));

        // 获取大于等于某个键的条目
        System.out.println("Ceiling entry for Cherry: " + fruitMap.ceilingEntry("Cherry"));

        // 删除元素
        fruitMap.remove("Banana");
        System.out.println("After removing Banana: " + fruitMap);

        // 使用 descendingMap() 反转顺序
        System.out.println("Descending Map: " + fruitMap.descendingMap());

        // 获取子Map
        System.out.println("SubMap from Apple to Orange: " + fruitMap.subMap("Apple", true, "Orange", true));
        List<Integer> list = new ArrayList<>(fruitMap.values());

    }
}