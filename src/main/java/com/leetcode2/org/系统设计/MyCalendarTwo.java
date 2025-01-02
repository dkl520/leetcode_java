package com.leetcode2.org.系统设计;

import java.util.Map;
import java.util.TreeMap;

/**
 * MyCalendarTwo 类允许预订事件，且不超过两个事件重叠。
 */
public class MyCalendarTwo {
    private Map<Integer, Integer> tm = new TreeMap<>();

    /**
     * MyCalendarTwo 的构造函数。
     */
    public MyCalendarTwo() {
    }

    /**
     * 预订一个事件，如果不会导致超过两个事件重叠则成功。
     *
     * @param start 事件的开始时间
     * @param end 事件的结束时间
     * @return 如果事件可以预订则返回 true，否则返回 false
     */



    public boolean book(int start, int end) {
        tm.put(start, tm.getOrDefault(start, 0) + 1);
        tm.put(end, tm.getOrDefault(end, 0) - 1);
        int s = 0;
        for (int v : tm.values()) {
            s += v;
            if (s > 2) {
                tm.put(start, tm.get(start) - 1);
                tm.put(end, tm.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}