package com.leetcode2.org.大厂;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CitdelFindCnsistentLogs2 {

    public int findConsistentLogs(int[] userEvent) {
        // 数组长度
        int n = userEvent.length;
        // 用于统计每个用户事件的频率
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 更新每个用户的事件频率
            freq.put(userEvent[i], freq.getOrDefault(userEvent[i], 0) + 1);
        }
        // 找到全局的最小频率（即某个事件在整个数组中的最少出现次数）

        int min = Integer.MAX_VALUE;
        for (int v : freq.values()) {
            min = Math.min(min, v);
        }
        // 清空频率映射表，为滑动窗口统计做准备
        freq.clear();
        // 使用 TreeMap 维护频率的计数，支持按频率排序（键为频率，值为该频率的用户数量）
        TreeMap<Integer, Integer> freqCount = new TreeMap<>();
        // 左指针（窗口左边界）、最大频率、以及结果变量（记录最长的有效窗口长度）
        int l = 0;
        int max = 0;
        int res = Integer.MIN_VALUE;
        // 遍历数组，使用滑动窗口方法
        for (int r = 0; r < n; r++) {
            int num = userEvent[r]; // 当前事件
            // 更新当前事件的频率
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            int newFreq = freq.get(num); // 新的频率
            int oldFreq = newFreq - 1; // 旧的频率
            // 更新频率计数（TreeMap 中记录频率出现的次数）
            freqCount.put(newFreq, freqCount.getOrDefault(newFreq, 0) + 1);
            freqCount.put(oldFreq, freqCount.getOrDefault(oldFreq, 0) - 1);
            // 如果某个频率的计数为 0，则从 TreeMap 中移除该频率
            if (freqCount.get(oldFreq) == 0) {
                freqCount.remove(oldFreq);
            }
            // 缩小窗口左边界，直到窗口内的最大频率不超过全局最小频率
            while (freqCount.lastKey() > min) {
                int numL = userEvent[l]; // 左边界事件
                // 更新左边界事件的频率
                int lOldFreq = freq.get(numL); // 左边界事件的当前频率
                int lNewFreq = lOldFreq - 1; // 左边界事件的新频率
                // 更新频率计数
                freqCount.put(lOldFreq, freqCount.get(lOldFreq) - 1);
                if (freqCount.get(lOldFreq) == 0) {
                    freqCount.remove(lOldFreq); // 移除频率为 0 的记录
                }
                freqCount.put(lNewFreq, freqCount.getOrDefault(lNewFreq, 0) + 1);
                // 更新左边界事件的频率映射，并右移左指针
                freq.put(numL, freq.get(numL) - 1);
                l++;
            }
            // 如果窗口内的最高频率等于全局最小频率，更新结果为最大窗口长度
            if (freqCount.lastKey() == min) {
                res = Math.max(res, r - l + 1);
            }
        }
        return res; // 返回最长的有效窗口长度
    }

    public static void main(String[] args) {
        CitdelFindCnsistentLogs2 solution = new CitdelFindCnsistentLogs2();
        int[] events = {1, 2, 1, 3, 4, 2, 4, 3, 3, 4};
        System.out.println(solution.findConsistentLogs(events)); // 输出结果
    }
}
