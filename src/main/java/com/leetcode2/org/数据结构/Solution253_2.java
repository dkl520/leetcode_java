package com.leetcode2.org.数据结构;

import java.util.Arrays;
import java.util.TreeMap;

public class Solution253_2 {
    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> maps = new TreeMap<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);



        for (int[] interval : intervals) {

            Integer floorKey = maps.floorKey(interval[0]);
            if (floorKey != null) {
                if (maps.get(floorKey) == 1) {
                    maps.remove(floorKey);
                } else {
                    maps.compute(floorKey, (key, val) -> val - 1);
                }
            }
            maps.compute(interval[1], (key, val) -> val == null ? 1 : val + 1);
        }


//        maps.headMap()
        return   maps.values().stream().mapToInt(Integer::intValue).sum();
    }


    public static void main(String[] args) {
        Solution253 s = new Solution253();
        int[][] intervals = {
                {2, 5},
                {4, 6},
                {7, 20},
                {5, 30}
        };
        System.out.println(
                s.minMeetingRooms(intervals)
        );

    }
}
