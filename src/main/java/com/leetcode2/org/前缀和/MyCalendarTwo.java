package com.leetcode2.org.前缀和;

import java.util.ArrayList;
import java.util.List;

// 定义一个可以处理多次重叠预订的日历类
public class MyCalendarTwo {
    // 存储所有预订的列表
    private final List<int[]> bookings;
    // 存储所有重叠区域的列表，用于快速判断新的预订是否与已有预订重叠
    private final List<int[]> overlaps;

    // 构造函数，初始化预订列表和重叠列表
    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    // 尝试预订一个新的时间段 [start, end)
    // 如果预订不与任何现有预订重叠，则返回 true，并添加此预订
    // 否则，返回 false，表示预订失败
    public boolean book(int start, int end) {
        // 首先检查新预订是否与已有的重叠区域重叠
        for (int[] overlap : overlaps) {
            if (start < overlap[1] && end > overlap[0]) {
                return false; // 如果与重叠区域重叠，则预订失败
            }
        }

        // 接下来，检查新预订是否与已有的预订重叠，并更新重叠列表
        for (int[] booking : bookings) {
            if (start < booking[1] && end > booking[0]) {
                // 如果新预订与已有预订重叠，则更新重叠列表
                overlaps.add(new int[]{Math.max(start, booking[0]), Math.min(end, booking[1])});
            }
        }

        // 将新预订添加到预订列表中
        bookings.add(new int[]{start, end});
        System.out.println(overlaps);
        return true; // 预订成功
    }
//    这个类通过维护两个列表来管理预订：bookings列表存储了所有的预订，而overlaps列表存储了所有重叠区域的起始和结束时间。通过这两个列表
//    ，我们可以快速判断新的预订是否与已有的预订重叠，并相应地更新重叠列表。这种方式在处理大量预订和重叠时可以提高效率。

    // 主函数，用于测试 MyCalendarTwo 类的功能
    public static void main(String[] args) {
        MyCalendarTwo cal = new MyCalendarTwo();
        System.out.println(cal.book(10, 20)); // 返回 true，预订成功
        System.out.println(cal.book(50, 60)); // 返回 true，预订成功
        System.out.println(cal.book(10, 40)); // 返回 true，预订成功，但与前两个预订重叠
        System.out.println(cal.book(5, 15));  // 返回 false，预订失败，与第一个预订重叠
        System.out.println(cal.book(5, 10));  // 返回 true，预订成功，不与现有重叠区域重叠
        System.out.println(cal.book(25, 55)); // 返回 true，预订成功，但与前两个预订重叠
    }
}