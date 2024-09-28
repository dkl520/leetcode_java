package com.leetcode2.org.数论;

public class QinJiuShaoAlgorithm {
    // 使用秦九韶算法计算多项式的值
    public static double horner(double[] coefficients, double x) {
        int n = coefficients.length;
        double result = coefficients[0]; // 从最高次项开始
        for (int i = 1; i < n; i++) {
            result = result * x + coefficients[i];
        }
        return result;
    }

    public static void main(String[] args) {
        // 多项式: 2x^3 - 6x^2 + 2x - 1
        double[] coefficients = {2, -6, 2, -1};
        double x = 3.0;
        double result = horner(coefficients, x);
        System.out.println("P(3) = " + result); // 输出: P(3) = 5.0
    }
}
