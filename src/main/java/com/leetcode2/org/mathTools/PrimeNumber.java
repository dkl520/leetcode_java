package com.leetcode2.org.mathTools;
import java.util.ArrayList;

public class PrimeNumber {
    public static void main(String[] args) {
        ArrayList<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < 100000000; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    break;
                }
                if (i-1==j){
                    primeList.add(i);
                    System.out.println(i);
                }
            }
        }
//        for (int primenum : primeList) {
//            System.out.println(primenum);
//        }

    }
}
