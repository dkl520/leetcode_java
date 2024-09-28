package com.leetcode2.org.滑动窗口;

public class Solution1052_2 {
    /**
     * 计算在给定时间内，店主使用秘密技巧后能获得的最大满意度。
     *
     * @param customers 顾客数组，表示每个时间点的顾客数量。
     * @param grumpy 店主情绪数组，0 表示店主开心，1 表示店主生气。
     * @param minutes 店主使用秘密技巧的时间长度（分钟）。
     * @return 店主在给定时间内使用秘密技巧后能获得的最大满意度。
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length; // 顾客数组的长度
        int satisfied = 0; // 初始满意度，即店主原本就开心时的满意度总和

        // 计算店主原本就开心时的满意度总和
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                satisfied += customers[i];
            }
        }

        int maxChange = 0; // 最大的额外满意度变化
        int currentChange = 0; // 当前窗口内的额外满意度变化

        // 初始化第一个窗口的额外满意度变化
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                currentChange += customers[i];
            }
        }
        maxChange = currentChange; // 第一个窗口的额外满意度变化即为当前最大额外满意度变化

        // 使用滑动窗口来找到可能的最大额外满意度变化
        for (int i = minutes; i < n; i++) {
            // 移除窗口最左边的顾客（如果店主原本生气）
            if (grumpy[i - minutes] == 1) {
                currentChange -= customers[i - minutes];
            }
            // 添加新进入窗口的顾客（如果店主原本生气）
            if (grumpy[i] == 1) {
                currentChange += customers[i];
            }
            // 更新最大额外满意度变化
            maxChange = Math.max(maxChange, currentChange);
        }

        // 返回总满意度（初始满意度 + 最大额外满意度变化）
        return satisfied + maxChange;
    }
}