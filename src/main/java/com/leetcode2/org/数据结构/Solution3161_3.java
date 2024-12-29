package com.leetcode2.org.数据结构;

import java.util.*;

public class Solution3161_3 {
    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        // 使用 TreeMap 来维护有序的键顺序，类似于 C++ 的 map
        TreeMap<Integer, Integer> distance = new TreeMap<>();
        distance.put(0, 0); // 初始化起点，键为0，值为0

        for (int[] query : queries) {
            if (query[0] == 1) {
                // 添加障碍物
                int x = query[1];
                // 找到前一个条目（相当于 prev(lower_bound(x))）
                Map.Entry<Integer, Integer> prevEntry = distance.lowerEntry(x);
                // 插入新条目，值为当前键与前一个键的差值和前一个键的值中的较大者
                distance.put(x, Math.max(x - prevEntry.getKey(), prevEntry.getValue()));
                // 更新后续条目
                NavigableMap<Integer, Integer> tailMap = distance.tailMap(x, false);
                for (Map.Entry<Integer, Integer> entry : tailMap.entrySet()) {
                    int curKey = entry.getKey();
                    Map.Entry<Integer, Integer> prevMapEntry = distance.lowerEntry(curKey);
                    int oldDistance = entry.getValue();
                    // 计算新距离，取前一个条目的值和当前键与前一个键的差值中的较大者
                    int newDistance = Math.max(
                            prevMapEntry.getValue(),
                            curKey - prevMapEntry.getKey()
                    );
                    // 如果旧距离等于新距离，停止更新
                    if (oldDistance == newDistance) {
                        break;
                    }
                    // 更新条目的值为新距离
                    entry.setValue(newDistance);
                }
            } else {
                // 检查距离
                int x = query[1];
                // 找到前一个条目
                Map.Entry<Integer, Integer> prevEntry = distance.lowerEntry(x);
                // 判断给定的距离是否小于等于当前键与前一个键的差值和前一个键的值中的较大者
                ans.add(query[2] <= Math.max(x - prevEntry.getKey(), prevEntry.getValue()));
            }
        }

        return ans;
    }
}