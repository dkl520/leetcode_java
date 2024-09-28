package com.leetcode2.org.数论;

public class ModularInverse {

    // 存储结果的类
    static class Result {
        int gcd;  // 最大公约数
        int x;    // 贝祖系数 x
        int y;    // 贝祖系数 y

        Result(int gcd, int x, int y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    // 扩展欧几里得算法
    public static Result extendedGCD(int a, int b) {
        if (b == 0) {
            return new Result(a, 1, 0);
        }
        Result result = extendedGCD(b, a % b);
        int x = result.y;
        int y = result.x - (a / b) * result.y;
        return new Result(result.gcd, x, y);
    }

    // 计算 a 在 m 下的逆元
    public static int modInverse(int a, int m) {
        Result result = extendedGCD(a, m);
        if (result.gcd != 1) {
            throw new ArithmeticException("Inverse does not exist.");
        } else {
            int inverse = result.x % m;
            if (inverse < 0) {
                inverse += m;
            }
            return inverse;
        }
    }

    public static void main(String[] args) {
        int a = 7;
        int m = 20;

        try {
            int inverse = modInverse(a, m);
            System.out.println("Modular inverse of " + a + " under modulo " + m + " is: " + inverse);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
