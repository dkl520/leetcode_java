package com.leetcode2.组合数学;


//最大公约数
public class GCD {
    // 递归实现
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 循环实现
    public static int gcdIterative(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 56, b = 98;
        System.out.println("GCD of " + a + " and " + b + " (recursive): " + gcd(a, b));
        System.out.println("GCD of " + a + " and " + b + " (iterative): " + gcdIterative(a, b));
    }
}
