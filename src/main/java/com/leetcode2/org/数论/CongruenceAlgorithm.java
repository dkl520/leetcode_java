// 声明一个名为org.数论的包，用于组织相关的类。  
package com.leetcode2.org.数论;

// 声明一个名为CongruenceAlgorithm的公共类。  
public class CongruenceAlgorithm {

    // 函数用于解决线性同余方程（ax ≡ b (mod m)）  
    public static int solveLinearCongruence(int a, int b, int m) {
        // 调用扩展欧几里得算法来计算a和m的最大公约数(gcd)以及对应的系数x0  
        int[] result = extendedEuclidean(a, m);

        // 从结果数组中取得最大公约数和系数x0  
        int gcd = result[0];
        int x0 = result[1];

        // 检查b是否是gcd的倍数，这是方程有解的必要条件  
        if (b % gcd == 0) {
            // 如果有解，计算x的值  
            int x = (x0 * (b / gcd)) % m;
            // 确保得到的解是非负的  
            return (x + m) % m;
        } else {
            // 如果没有解，则抛出一个算术异常  
            throw new ArithmeticException("No solution exists for the linear congruence.");
        }
    }

    // 扩展欧几里得算法用于找到gcd(a, b)以及系数x和y，使得ax + by = gcd(a, b)  
    private static int[] extendedEuclidean(int a, int b) {
        // 如果b为0，则gcd为a，x为1，y为0  
        if (b == 0) {
            return new int[]{a, 1, 0};
        } else {
            // 递归调用扩展欧几里得算法，计算b和a%b的gcd以及对应的系数  
            int[] result = extendedEuclidean(b, a % b);
            // 从递归调用的结果中取得gcd，x1和y1  
            int gcd = result[0];
            int x1 = result[2];
            int y1 = result[1] - (a / b) * result[2];

            // 返回新的gcd，x1和y1的值  
            return new int[]{gcd, y1, x1};
        }
    }

    // 主方法，程序的入口点  
    public static void main(String[] args) {
        // 示例用法：设置a, b, m的值  
        int a = 5, b = 1, m = 11;

        try {
            // 调用solveLinearCongruence方法求解线性同余方程  
            int solution = solveLinearCongruence(a, b, m);
            // 打印方程的解  
            System.out.println("Solution to the congruence " + a + "x ≡ " + b + " (mod " + m + "): x ≡ " + solution + " (mod " + m + ")");
        } catch (ArithmeticException e) {
            // 如果方程无解，则打印异常信息  
            System.out.println(e.getMessage());
        }
    }
}