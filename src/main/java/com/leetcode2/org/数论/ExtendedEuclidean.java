package com.leetcode2.org.数论;

public class ExtendedEuclidean {

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
            // 如果 b 为 0，则 gcd(a, b) = a，贝祖系数 x = 1, y = 0
            return new Result(a, 1, 0);
        }

        // 递归调用
        Result result = extendedGCD(b, a % b);

        // 更新贝祖系数
        int x = result.y;
        int y = result.x - (a / b) * result.y;

        // 返回当前的 gcd 和贝祖系数
        return new Result(result.gcd, x, y);
    }

    public static void main(String[] args) {
        int a = 30;
        int b = 20;

        Result result = extendedGCD(a, b);

        System.out.println("GCD: " + result.gcd);
        System.out.println("x: " + result.x);
        System.out.println("y: " + result.y);
        System.out.println("Verification: " + (a * result.x + b * result.y));
    }
//  扩展欧几里得算法
//    ax+by=gcd(a,b)  贝祖等式
}
