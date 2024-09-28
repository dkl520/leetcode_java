package com.leetcode2.org.mathTools;
import java.util.Scanner;

public class ArrayExer {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 2, 1, 0, 3};
        int[] index = new int[]{2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3};
        String tel = "";
        for (int i = 0; i < index.length; i++) {
            int value = index[i];
            tel += arr[value];
        }
        System.out.println(tel);
        String[] list = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        Scanner scan = new Scanner(System.in);
        System.out.println("请输入数字：");
        int input = scan.nextInt();
        if (input > 7 || input < -1) {
            System.out.println("您输入的数据有误");
            return;
        }
        System.out.println(list[input]);

    }

}
