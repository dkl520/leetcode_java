package com.leetcode2.org.系统设计;

import java.util.TreeMap;

public class MyCalendar {
    TreeMap<Integer, Integer> memeo;

    public MyCalendar() {
        memeo = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        var leftTime = memeo.floorEntry(startTime);
        var rightTime = memeo.floorEntry(endTime - 1);

        if (leftTime == null && rightTime == null) {
            memeo.put(startTime, endTime - 1);
            return true;
        }
        if (leftTime == null) {
            return false;
        }

        if (leftTime.getValue() < startTime || rightTime.getValue() < startTime) {
            memeo.put(startTime, endTime - 1);
            return true;
        }

        return false;


    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();

        // 调用 book 方法并打印结果
        System.out.println(myCalendar.book(97, 100));  // true/false
        System.out.println(myCalendar.book(33, 51));  // true/false
        System.out.println(myCalendar.book(89, 100)); // true/false
        System.out.println(myCalendar.book(83, 100)); // true/false
        System.out.println(myCalendar.book(75, 92));  // true/false
        System.out.println(myCalendar.book(76, 95));  // true/false
        System.out.println(myCalendar.book(19, 30));  // true/false
        System.out.println(myCalendar.book(53, 63));  // true/false
        System.out.println(myCalendar.book(8, 23));   // true/false
        System.out.println(myCalendar.book(18, 37));  // true/false
        System.out.println(myCalendar.book(87, 100)); // true/false
        System.out.println(myCalendar.book(83, 100)); // true/false
        System.out.println(myCalendar.book(54, 67));  // true/false
        System.out.println(myCalendar.book(35, 48));  // true/false
        System.out.println(myCalendar.book(58, 75));  // true/false
        System.out.println(myCalendar.book(70, 89));  // true/false
        System.out.println(myCalendar.book(13, 32));  // true/false
        System.out.println(myCalendar.book(44, 63));  // true/false
        System.out.println(myCalendar.book(51, 62));  // true/false
        System.out.println(myCalendar.book(2, 15));   // true/false
    }
}