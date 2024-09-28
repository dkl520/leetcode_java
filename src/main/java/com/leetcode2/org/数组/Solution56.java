package com.leetcode2.org.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution56 {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] curInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] nextInterval = intervals[i];
             if (curInterval[1] >= nextInterval[0]) {
                 curInterval[1] = Math.max(curInterval[1], nextInterval[1]);
             }else{
                 list.add(curInterval);
                 curInterval = nextInterval;
             }
        }
        list.add(curInterval);
        return list.toArray(new int[list.size()][]);
    }
}
