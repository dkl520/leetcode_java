package com.leetcode2.org.数论;

import java.util.HashSet;
import java.util.Set;

public class Solution3233 {

    public int nonSpecialCount(int l, int r) {
        Set<Double> setPrimes = new HashSet<>();

        for (int i = 0; i < 10001; i++) {
            if (isPrime(i)) {
                setPrimes.add((double) i);
            }
        }

        int count = r - l + 1;
        for (int i = l; i <= r; i++) {
            double res = Math.sqrt(i);
            if (setPrimes.contains(res)) {
                count--;
            }
        }
        return count;

    }

    public static boolean isPrime(int number) {
        // 小于等于1的数字不是质数
        if (number <= 1) {
            return false;
        }

        // 检查是否能被 2 到 √number 的任意数字整除
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false; // 如果能整除，则不是质数
            }
        }

        return true; // 如果没有发现任何因数，则是质数
    }

    public static void main(String[] args) {
        int l = 4;
        int r = 16;
        Solution3233 sol = new Solution3233();
        System.out.println(sol.nonSpecialCount(l, r));


    }
}
