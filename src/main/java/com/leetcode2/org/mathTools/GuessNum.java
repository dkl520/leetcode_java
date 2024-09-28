package com.leetcode2.org.mathTools;
import java.util.Scanner;

public class GuessNum {
    public static void main(String[] args) {
        Scanner  scan = new Scanner(System.in);
        int guessNum= scan.nextInt();
        int randomNum = (int)(Math.random()*100)+1;
        System.out.println("请输入1-100以内的整数："+randomNum);

        while(guessNum!=randomNum ){
            if (guessNum>randomNum){
                System.out.println("你输入的数字大了");
            }else if (guessNum<randomNum){
                System.out.println("你输入的数字小了");
            }
            System.out.println("请继续猜");
            guessNum = scan.nextInt();
        }
        System.out.println("恭喜你猜对了！"+guessNum);
    }
}
