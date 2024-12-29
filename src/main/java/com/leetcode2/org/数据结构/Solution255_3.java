package com.leetcode2.org.数据结构;

import java.util.Arrays;

public class Solution255_3 {
    // 最小会议室数
    public int minMeetingRooms(int[][] intervals) {
        // 如果输入的会议区间为空，直接返回 0
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 创建两个数组，分别用于存储所有会议的开始时间和结束时间
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        // 将每个会议的开始时间和结束时间分别存入对应的数组
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];  // 会议开始时间
            end[i] = intervals[i][1];    // 会议结束时间
        }
        // 对开始时间和结束时间数组进行排序
        Arrays.sort(start);
        Arrays.sort(end);
        // rooms 用于记录当前需要的会议室数量
        int rooms = 0;
        // endPtr 用于跟踪当前可用的最早结束时间
        int endPtr = 0;

        // 遍历每个会议的开始时间
        for (int i = 0; i < start.length; i++) {
            // 如果当前会议的开始时间早于最早结束时间，说明需要新的会议室
            if (start[i] < end[endPtr]) {
                rooms++;  // 增加需要的会议室数量
            } else {
                // 否则，说明最早结束的会议可以使用同一个会议室，结束时间指针前移
                endPtr++;
            }
        }
        // 返回所需的最小会议室数
        return rooms;
    }
}
