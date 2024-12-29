package com.leetcode2.org.状态压缩;

import java.util.List;

public class Solution3376_2 {
    public int findMinimumTime(List<Integer> strength, int K) {
        int size = strength.size(); // 获取任务数量
        int dp[] = new int[1 << size]; // 初始化 dp 数组，大小为 2 的 size 次方

        for (int i = (1 << size) - 2; i >= 0; i--) { // 从倒数第二个状态开始遍历到第一个状态
            int t = i, dep = 0; // 初始化 t 为当前状态，dep 为深度
            int ret = Integer.MAX_VALUE; // 初始化 ret 为最大值

            while (dep < size) { // 遍历所有任务
                if ((i >> dep & 1) == 0) { // 如果当前任务未被选择
                    int X = Integer.bitCount(i) * K + 1; // 计算当前增益
                    int num = strength.get(dep); // 获取当前任务的需求
                    int d = (num + X - 1) / X; // 计算完成当前任务所需的天数
                    ret = Math.min(ret, d + dp[i ^ 1 << dep]); // 更新 ret 为最小值
                }
                dep++; // 深度加一
            }
            dp[i] = ret; // 更新 dp 数组
        }

        return dp[0]; // 返回初始状态的最小天数
    }
}