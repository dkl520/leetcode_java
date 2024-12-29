package com.leetcode2.org.大厂;

import java.util.*;

public class CitdelFindCnsistentLogs {

    /**
     * 计算符合条件的最长一致日志子数组的长度
     *
     * @param events 表示事件日志中的用户ID数组
     * @return 最长符合条件的子数组长度
     */
    public static int consistentLogs(int[] events) {
        // 1. 计算整个数组中每个用户的频率
        Map<Integer, Integer> globFreq = new HashMap<>();
        for (int event : events) {
            globFreq.put(event, globFreq.getOrDefault(event, 0) + 1);
        }

        // 2. 找到全局最小频率（整个数组中频率最小的用户）
        int globMinFreq = Integer.MAX_VALUE;
        for (int freq : globFreq.values()) {
            globMinFreq = Math.min(globMinFreq, freq);
        }

        // 3. 定义滑动窗口的左边界、最高频率、对应用户ID，以及结果变量
        int left = 0, maxFreq = -1, maxFreqUser = -1, res = 0;
        // 用于存储当前窗口内各用户的频率
        Map<Integer, Integer> freq = new HashMap<>();
        // 4. 使用滑动窗口算法遍历整个事件数组
        for (int right = 0; right < events.length; right++) {
            // 更新窗口内当前用户的频率
            freq.put(events[right], freq.getOrDefault(events[right], 0) + 1);
            // 如果当前用户的频率超过最高频率，则更新最高频率和对应用户ID
            if (freq.get(events[right]) > maxFreq) {
                maxFreqUser = events[right];  // 记录最高频率用户的ID
                maxFreq = freq.get(maxFreqUser);  // 更新最高频率
            }
            // 5. 如果窗口中的最高频率大于全局最小频率，则收缩左边界
            while (maxFreq > globMinFreq) {
                // 左边界用户的频率减少
                freq.put(events[left], freq.get(events[left]) - 1);
                // 如果左边界用户是最高频率用户，需要更新最高频率
                if (events[left] == maxFreqUser) {
                    maxFreq--;
                }
                left++;  // 左边界右移，缩小窗口
            }
            // 6. 如果窗口内最高频率等于全局最小频率，更新结果
            if (maxFreq == globMinFreq) {
                res = Math.max(res, right - left + 1);  // 更新最大长度
            }
        }
        // 返回最大的一致日志子数组的长度
        return res;
    }

    /**
     * 主方法，测试consistentLogs函数
     */
    public static void main(String[] args) {
        int[] events = {1, 2, 1, 3, 4, 2, 4, 3, 3, 4};
        System.out.println(consistentLogs(events));  // 输出结果：8
    }
}
