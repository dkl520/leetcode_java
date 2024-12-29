package com.leetcode2.org.数据结构;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution253 {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int result = 0;
        Map<Integer, Integer> mapC = new HashMap<>();
        TreeSet<Integer> setRights = new TreeSet<>();

        for (int[] interval : intervals) {
            if (setRights.isEmpty()) {
                setRights.add(interval[1]);
                mapC.put(interval[1], 1);
                result++;
            } else {
                Integer prevRight = setRights.floor(interval[0]);
                if (prevRight == null) {
                    result++;
                    setRights.add(interval[1]);
                    mapC.put(interval[1], mapC.getOrDefault(interval[1], 0) + 1);
                } else {
                    if (mapC.get(prevRight) == 1) {
                        setRights.remove(prevRight);
                        mapC.remove(prevRight);
                    }
                    setRights.add(interval[1]);
                    mapC.put(interval[1], mapC.getOrDefault(interval[1], 0) + 1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution253 s = new Solution253();
        int[][] intervals = {
                {2, 10},
                {4, 10},
                {10, 20},
                {10, 30}
        };
        System.out.println(
                s.minMeetingRooms(intervals)
        );

    }
}
