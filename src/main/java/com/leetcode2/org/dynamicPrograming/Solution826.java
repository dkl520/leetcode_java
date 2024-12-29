package com.leetcode2.org.dynamicPrograming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution826 {
    static class Job {
        int difficulty;
        int profit;

        public Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        TreeMap<Integer, Job> set = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            Job job = new Job(difficulty[i], profit[i]);
            set.put(difficulty[i], job);
        }
        List<Integer> keysToRemove = new ArrayList<>();
        for (Map.Entry<Integer, Job> entry1 : set.entrySet()) {
            for (Map.Entry<Integer, Job> entry2 : set.entrySet()) {
                if (entry1.getValue().difficulty > entry2.getValue().difficulty && entry1.getValue().profit <= entry2.getValue().profit) {
                    keysToRemove.add(entry1.getKey());
                }
            }
        }
        for (Integer key : keysToRemove) {
            set.remove(key);
        }

        int maxProfit = 0;
        for (int w : worker) {
            Integer key = set.floorKey(w);
            if (key == null) continue;
            maxProfit += set.get(key).profit;
        }
        return  maxProfit;

    }
}
