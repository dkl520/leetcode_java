package com.leetcode2.org.堆;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution1931 {

    public long maxSpending(int[][] values) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<List<Integer>> queue = new PriorityQueue<>((a, b) ->
                a.get(a.size() - 1) - b.get(b.size() - 1)
        );
        for (int[] row : values) {
            List<Integer> list = new ArrayList<Integer>();
            for (int val : row) {
                list.add(val);
            }
            queue.offer(list);
        }
        int day = 0;
        long result = 0;
        while (!queue.isEmpty()) {
            day++;
            List<Integer> list = queue.poll();
            result += (long) list.remove(list.size() - 1) * day;
            if (!list.isEmpty()) {
                queue.offer(list);
            }
        }
        return result;


    }

}
