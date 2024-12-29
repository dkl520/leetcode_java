package com.leetcode2.org.数组;

import java.util.HashMap;
import java.util.Map;

public class Solution2365 {
    Map<Integer, Integer> timer = new HashMap<>();

    public long taskSchedulerII(int[] tasks, int space) {
        long count = tasks.length;
        for (int task : tasks) {
            if (timer.containsKey(task)) {
                int lastDay = timer.get(task);
                if (lastDay < space) {
                    breaks(timer, space - lastDay+1);
                    count += space - lastDay;
                }else{
                    breaks(timer, 1);
                }
            } else {
                breaks(timer, 1);
            }
            timer.put(task, 0);
        }
        return count;
    }

    void breaks(Map<Integer, Integer> timer, int day) {
        timer.forEach((k, v) -> {
            timer.put(k, v += day);
        });
    }

    public static void main(String[] args) {
        int[] tasks = new int[]{1, 2, 1, 2, 3, 1};
        int space= 3;
        Solution2365 Solution = new Solution2365();
        System.out.println(Solution.taskSchedulerII(tasks, space));

    }

}