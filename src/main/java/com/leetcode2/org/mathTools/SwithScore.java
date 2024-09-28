package com.leetcode2.org.mathTools;
import java.util.Scanner;

public class SwithScore {
    public static void main(String[] args) {
        System.out.println("请输入成绩");
        Scanner input = new Scanner(System.in);
        int score = input.nextInt();
        switch (score/10){
            case 10:
            case 9:
                System.out.println("等级为A");
                break;
            case 8:
            case 7:
                System.out.println("等级为B");
                break;
            case 6:
                System.out.println("等级为C");
                break;
            default:
                System.out.println("等级为D");
        }
    }
}
