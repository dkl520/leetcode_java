package com.leetcode2.org.mathTools;
public class BreakContinue {
    public static void main(String[] args) {
//        for (int i = 1; i <11; i++) {
//            if (i%4==0) {
//                continue;
////                break;
//            }
//            System.out.println(i);
//        }

        label:
        for (int j = 1; j < 5; j++) {
            for (int i = 1; i < 11; i++) {
                if (i % 4 == 0) {
                    continue label;
                }
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
