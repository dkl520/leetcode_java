package com.leetcode2.org.mathTools;
import java.util.ArrayList;

public class ForTest {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println("hello world");
            System.out.println(i);
        }

        int count = 0;
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            if (i % 2 == 0) {
                count++;
                sum += i;
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.println("偶数个数是" + count);
        System.out.println("总数是" + sum);
        ArrayList<Integer> narcissusNum = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            int numHundred = i / 100;
            int numUnit = i % 10;
            int numTens = (i % 100) / 10;
            if (i == numHundred * numHundred * numHundred + numUnit * numUnit * numUnit + numTens * numTens * numTens) {
                narcissusNum.add(i);
            }
        }
        for (int element : narcissusNum) {
            System.out.println(element);
        }
    }
}
