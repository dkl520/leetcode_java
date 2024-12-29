package com.leetcode2.org.扫描线;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0) return new ArrayList<>();

        // 存储所有关键点 (x, 高度)，按照 x 排序，如果 x 相同则按照高度升序排序
        TreeMap<Integer, List<Integer>> points = new TreeMap<>();
        for (int[] b : buildings) {
            points.putIfAbsent(b[0], new ArrayList<>());
            points.putIfAbsent(b[1], new ArrayList<>());
            points.get(b[0]).add(-b[2]); // 左边高度插入为负
            points.get(b[1]).add(b[2]);  // 右边高度插入为正
        }

        // 结果集
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, Integer> height = new TreeMap<>();
        height.put(0, 1); // 初始化地平线为高度 0
        int maxHeight = 0; // 记录当前最高高度

        for (var entry : points.entrySet()) {
            int x = entry.getKey();
            List<Integer> heights = entry.getValue();

            // 更新当前高度
            for (int h : heights) {
                if (h < 0) { // 左侧边界，高度加入
                    height.put(-h, height.getOrDefault(-h, 0) + 1);
                } else { // 右侧边界，移除高度
                    if (height.get(h) == 1) {
                        height.remove(h);
                    } else {
                        height.put(h, height.get(h) - 1);
                    }
                }
            }

            // 获取当前的最高高度
            int currentHeight = height.lastKey();
            if (currentHeight != maxHeight) { // 如果高度变化则添加关键点
                result.add(Arrays.asList(x, currentHeight));
                maxHeight = currentHeight;
            }
        }

        return result;
    }
}
