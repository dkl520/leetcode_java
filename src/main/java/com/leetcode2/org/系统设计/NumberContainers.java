package com.leetcode2.org.系统设计;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberContainers {
    // 存储索引和数字的映射关系
    Map<Integer, Integer> m = new HashMap<>();
    // 存储每个数字对应的所有索引集合，TreeSet 用于保持索引按自然顺序排序
    Map<Integer, TreeSet<Integer>> ms = new HashMap<>();
    /**
     * 修改指定索引位置的数字。
     *
     * @param index  要修改的索引位置
     * @param number 要赋予的新数字
     */
    public void change(int index, int number) {
        // 获取当前索引位置的旧数字
        var old = m.get(index);
        // 如果旧数字存在，从旧数字对应的索引集合中移除该索引
        if (old != null) {
            ms.get(old).remove(index); // 移除旧数据
            // 如果移除后集合为空，删除该数字在映射中的条目
            if (ms.get(old).isEmpty()) {
                ms.remove(old);
            }
        }

        // 将新数字放入索引映射中
        m.put(index, number);
        // 将索引添加到新数字对应的索引集合中，如果集合不存在则创建新集合
        ms.computeIfAbsent(number, k -> new TreeSet<>()).add(index); // 添加新数据
    }

    /**
     * 查找指定数字的最小索引。
     *
     * @param number 要查找的数字
     * @return 如果数字存在，返回其最小索引；否则返回 -1
     */
    public int find(int number) {
        // 获取数字对应的索引集合
        var s = ms.get(number);
        // 如果集合为空或不存在，返回 -1；否则返回集合中的第一个（最小）索引
        return s == null || s.isEmpty() ? -1 : s.first();
    }
}
