package com.leetcode2.org.Date;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date1 = new Date();
        System.out.println(date1.toString());
        long milliTimes = date1.getTime();
        System.out.println(milliTimes);
        Date date2 = new Date(milliTimes);
        System.out.println(date2.toString());
//        java.sql.Date sqlD = new java.sql.Date(milliTimes);
//        System.out.println(sqlD);
//        java.sql.Date sqlD= new java.sql.Date(milliTimes);
//        System.out.println(sqlD);
    }


}
