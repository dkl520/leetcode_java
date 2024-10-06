package com.leetcode2.org.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class subArryConsist {

    // 定义方法，用于查找用户事件日志中最长的一致子数组
    public int findConsistentLogs(int[] userEvent) {
        int n = userEvent.length; // 获取用户事件数组的长度
        if (n == 0) return 0; // 如果数组长度为0，直接返回0

        // 第一步：找到整个数组中的最小频率
        // 使用HashMap记录每个事件ID出现的频率
        Map<Integer, Integer> globalFreq = new HashMap<>();
        int minFreq = n; // 初始化最小频率为数组长度，确保之后的比较能够正确进行

        // 遍历数组，统计每个事件ID的出现次数
        for (int id : userEvent) {
            globalFreq.put(id, globalFreq.getOrDefault(id, 0) + 1); // 更新事件ID的频率
        }

        // 遍历频率Map，找到出现次数最小的频率
        for (int freq : globalFreq.values()) {
            minFreq = Math.min(minFreq, freq); // 更新最小频率
        }

        // 第二步：使用滑动窗口寻找最长的一致子数组
        int maxLength = 0; // 保存最长子数组的长度
        int left = 0, right = 0; // 定义滑动窗口的左右边界
        Map<Integer, Integer> windowFreq = new HashMap<>(); // 定义窗口内事件ID的频率

        // 使用滑动窗口遍历数组
        while (right < n) {
            // 扩展窗口，将右边界的事件ID加入窗口
            int rightId = userEvent[right];
            windowFreq.put(rightId, windowFreq.getOrDefault(rightId, 0) + 1); // 更新窗口内事件ID的频率

            // 检查当前窗口是否需要收缩
            while (isWindowInvalid(windowFreq, minFreq)) { // 如果窗口不合法，则需要收缩
                int leftId = userEvent[left]; // 获取左边界的事件ID
                windowFreq.put(leftId, windowFreq.get(leftId) - 1); // 左边界事件ID的频率减1
                if (windowFreq.get(leftId) == 0) {
                    windowFreq.remove(leftId); // 如果某个事件ID的频率为0，则从窗口中移除
                }
                left++; // 左边界右移，收缩窗口
            }

            // 更新最大子数组长度
            maxLength = Math.max(maxLength, right - left + 1); // 计算当前窗口的长度并更新最大值
            right++; // 右边界右移，继续扩展窗口
        }

        return maxLength; // 返回最大一致子数组的长度
    }

    // 辅助方法：检查当前窗口是否合法
    // 窗口不合法的条件是：窗口内的最大频率超过数组中的最小频率
    private boolean isWindowInvalid(Map<Integer, Integer> windowFreq, int minFreq) {
        int maxFreqInWindow = 0; // 记录窗口内的最大频率
        for (int freq : windowFreq.values()) {
            maxFreqInWindow = Math.max(maxFreqInWindow, freq); // 更新窗口内的最大频率
        }
        return maxFreqInWindow > minFreq; // 如果窗口内的最大频率大于最小频率，则窗口不合法
    }

    // 主方法，测试用例
    public static void main(String[] args) {
        // 定义测试数组
//        int[] userEvent = new int [] {1, 2, 1, 3, 4, 2, 4, 3, 3, 4};
        int[] userEvent = new int[]{5, 5, 1, 1, 1, 3, 3, 2, 4, 3, 4};
        // 实例化类对象
        subArryConsist subArryConsist = new subArryConsist();
        // 调用方法并打印结果
        System.out.println(subArryConsist.findConsistentLogs(userEvent)); // 输出最长一致子数组的长度
    }
}
