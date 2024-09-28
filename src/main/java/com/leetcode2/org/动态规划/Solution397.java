package com.leetcode2.org.动态规划;

import java.util.*;

public class Solution397 {
    /**
     * 给定一个正整数 n，你可以执行以下操作之一：
     * 如果 n 是偶数，则 n 除以 2
     * 如果 n 是奇数，则 n 可以变为 n + 1 或 n - 1
     * 目标是使 n 变为 1，请计算并返回最少的操作次数。
     *
     * 使用广度优先搜索（BFS）和哈希表（HashMap）来存储已访问的数字及其到达该数字所需的操作次数。
     *
     * @param n 初始正整数
     * @return 使 n 变为 1 所需的最少操作次数
     */
    public int integerReplacement(int n) {
        if (n == 1) return 0; // 如果已经是1，则不需要任何操作
        // 使用HashMap来存储每个数字到达所需的操作次数
        Map<Long, Integer> map = new HashMap<>();
        // 使用双端队列作为BFS的队列
        Deque<Long> queue = new ArrayDeque<>();
        queue.addLast(n * 1L); // 将n转换为long类型并加入队列
        map.put(n * 1L, 0); // 初始化n的操作次数为0
        while (!queue.isEmpty()) {
            long current = queue.pollFirst(); // 从队列中取出当前数字
            int currentSteps = map.get(current); // 获取当前数字到达所需的操作次数
            // 根据当前数字是奇数还是偶数，生成下一步可能的数字
            long[] nextNumbers;
            if (current % 2 == 0) {
                // 如果当前数字是偶数，则只能除以2
                nextNumbers = new long[]{current / 2};
            } else {
                // 如果当前数字是奇数，则可以加1或减1
                nextNumbers = new long[]{current + 1, current - 1};
            }
            for (long next : nextNumbers) {
                if (next == 1) return currentSteps + 1; // 如果下一个数字是1，则返回当前操作次数加1
                if (!map.containsKey(next)) {
                    // 如果哈希表中没有下一个数字，则将其加入哈希表，并加入队列中继续搜索
                    map.put(next, currentSteps + 1);
                    queue.addLast(next);
                }
            }
        }

        // 理论上，如果输入合法，则不会执行到这里。但为了代码完整性，返回-1（虽然实际上不会发生）
        return -1;
    }
}