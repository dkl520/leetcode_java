package com.leetcode2.org.数组;

import java.util.HashMap;
import java.util.Map;

public class Solution2365_2 {
    public long taskSchedulerII(int[] tasks, int space) {
        // 使用哈希表记录每个任务上次执行的日期
        Map<Integer, Long> lastExecutionDay = new HashMap<>();
        // 当前的天数，表示到达任务执行的当前天数
        long currentDay = 0;

        // 遍历每个任务
        for (int task : tasks) {
            currentDay++; // 每次执行任务时，天数加1

            // 检查该任务是否之前已经执行过
            if (lastExecutionDay.containsKey(task)) {
                // 获取该任务上次执行的日期
                long lastDay = lastExecutionDay.get(task);
                // 计算当前任务下次允许执行的最早日期：上次执行日期 + 间隔天数 + 1
                long waitUntilDay = lastDay + space + 1;

                // 如果当前天数小于允许执行的最早日期，更新当前天数为等待后的日期
                if (currentDay < waitUntilDay) {
                    currentDay = waitUntilDay;
                }
            }

            // 更新任务的最后执行日期为当前日期
            lastExecutionDay.put(task, currentDay);
        }

        // 返回最后一天的日期，即完成所有任务所需的天数
        return currentDay;
    }
}
