package com.leetcode2.org.mathTools;
public class SwitchCase {
    public static void main(String[] args) {
        int score =17;
        System.out.println(score/10);
        switch (score/10){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("成绩不及格");
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("成绩合格");
                break;
            default:
                System.out.println("成绩输入有误！");
        }

    }
}
