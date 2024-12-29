package com.leetcode2.org.贪心;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution1705 {
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        int count = 0;
        int day = 0;

        // 优先队列，按苹果的过期时间升序排序
        Queue<int[]> appleQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        while (day < n || !appleQueue.isEmpty()) {
            // 添加当天的苹果记录到队列
            if (day < n && apples[day] > 0) {
                appleQueue.offer(new int[]{apples[day], day + days[day] - 1});
            }

            // 移除已过期的苹果
            while (!appleQueue.isEmpty() && appleQueue.peek()[1] < day) {
                appleQueue.poll();
            }

            // 吃一个苹果
            if (!appleQueue.isEmpty()) {
                int[] current = appleQueue.poll();
                count++;
                current[0]--; // 减少一个苹果
                if (current[0] > 0) {
                    appleQueue.offer(current); // 还有剩余苹果，放回队列
                }
            }
            day++;
        }

        return count;
    }
}
