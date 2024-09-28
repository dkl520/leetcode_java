package com.leetcode2.org.mathTools;
import java.util.Scanner;

public class SwitchDay {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入2023年月份");
        int month = input.nextInt();
        System.out.println("请输入2023年的天");
        int days = input.nextInt();
        int sumDays = 0;
        switch (month) {
            case 12:
                sumDays += 30;
            case 11:
                sumDays += 31;
            case 10:
                sumDays += 30;
            case 9:
                sumDays += 31;
            case 8:
                sumDays += 31;
            case 7:
                sumDays += 30;
            case 6:
                sumDays += 31;
            case 5:
                sumDays += 30;
            case 4:
                sumDays += 31;
            case 3:
                sumDays += 28;
            case 2:
                sumDays = 31 + sumDays;

            case 1:
                sumDays += days;
        }
        System.out.println("天数是" + sumDays);
//        switch (month){
//            case 1:
//                sumDays= days;
//                break;
//            case 2:
//                sumDays = days+31;
//                break;
//            case 3:
//                sumDays = days+31+28;
//                break;
//            case 4:
//                sumDays = days+31+28+31;
//                break;
//            case 5:
//                sumDays = days+31+28+31+30;
//                break;
//            case 6:
//                sumDays = days+31+28+31+30+31;
//                break;
//            case 7:
//                sumDays = days+31+28+31+30+31+30;
//                break;
//            case 8:
//                sumDays = days+31+28+31+30+31+30+31;
//                break;
//            case 9:
//                sumDays = days+31+28+31+30+31+30+31+31;
//                break;
//            case 10:
//                sumDays = days+31+28+31+30+31+30+31+31+30;
//                break;
//            case 11:
//                sumDays = days+31+28+31+30+31+30+31+31+30+31;
//                break;
//            case 12:
//                sumDays = days+31+28+31+30+31+30+31+31+30+31+30;
//                break;
//        }
//        System.out.println("天数是：" + sumDays);

    }
}
