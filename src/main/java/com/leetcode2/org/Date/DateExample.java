package com.leetcode2.org.Date;

import java.util.Date;
import  java.time.*;
import java.util.Locale;


//
//在 java.time.LocalDate 类中的 getYear 方法与 java.util.Date 类中的 getYear 方法有一些关键的区别。
//        java.util.Date 的 getYear 方法返回的是自 1900 年开始计算的年份。例如，如果当前日期是 2023 年，getYear 返回的将是 123。
//        java.time.LocalDate 的 getYear 方法返回的是直观的年份，不会从 1900 年开始计算。例如，如果当前日期是 2023 年，getYear 返回的将是 2023。
//        类型不同：
//        java.util.Date 的 getYear 方法返回的是 int 类型。
//        java.time.LocalDate 的 getYear 方法返回的是 int 类型。
//        因此，在使用 java.time.LocalDate 的 getYear 方法时，不需要进行额外的纠正操作，而在使用 java.util.Date 的 getYear 方法时，需要注意返回值是从 1900 年开始计算的。
//        这个变化是为了提高 API 的直观性和易用性，并规避一些旧的 java.util.Date 类的设计上的问题。因此，在新的代码中，特别是在 Java 8 及以后的版本中，建议使用 java.time 包中的日期和时间 API。

public class DateExample {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        LocalDate currentDate= LocalDate.now();
        System.out.println(currentDate);
        int currentYear= currentDate.getYear();
        System.out.println(currentYear);
        System.out.println(currentDate.getMonth());
        System.out.println(currentDate.getDayOfMonth());
        System.out.println(currentDate.getDayOfWeek());
//        System.out.println(currentDate.getHour);
        LocalTime localeTime= LocalTime.now();
        System.out.println(localeTime.getHour()+"--" + localeTime.getMinute()+"--" + localeTime.getSecond());
        System.out.println(currentDate.getMonthValue());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime +"ms" );

    }
}
