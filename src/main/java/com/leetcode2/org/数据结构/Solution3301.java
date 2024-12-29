package com.leetcode2.org.数据结构;

import java.util.*;

public class Solution3301 {

    public long maximumTotalSum(int[] maximumHeight) {
        Deque<Long> queue = new ArrayDeque<>();
        Queue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long result = 0;
        for (long height : maximumHeight) {
            pq.offer(height);
        }
        while (!pq.isEmpty()) {
            long cur = pq.poll();
            if (!queue.isEmpty()) {
                long pre = queue.peekLast();
                if (pre <= cur) {
                    cur = Math.max(pre - 1, 0);
                    if (cur == 0) return -1;
                }
            }
            queue.offer(cur);
            result += cur;
        }
        return result;
    }


}
