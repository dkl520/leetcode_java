// 声明一个名为org.数论的包，用于组织相关的类。  
package com.leetcode2.org.数论;

// 声明一个名为ModularInversion的公共类。  
public class ModularInversion {

    // 函数用于找到一个数在模意义下的逆元（a^-1 mod m）  
    public static int modInverse(int a, int m) {
        // 调用扩展欧几里得算法来计算a和m的最大公约数(gcd)以及对应的系数x0  
        int[] result = extendedEuclidean(a, m);

        // 从结果数组中取得最大公约数和系数x0  
        int gcd = result[0];
        int x0 = result[1];

        // 如果gcd为1，说明a和m互质，存在逆元  
        if (gcd == 1) {
            // 返回x0作为a在模m意义下的逆元，并确保它是非负的  
            return (x0 + m) % m;
        } else {
            // 如果gcd不为1，说明a和m不互质，不存在逆元  
            throw new ArithmeticException("The modular inverse does not exist.");
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
        // 示例用法：设置a和m的值  
        int a = 3, m = 11;

        try {
            // 调用modInverse方法求a在模m意义下的逆元  
            int inverse = modInverse(a, m);
            // 打印结果  
            System.out.println("Modular inverse of " + a + " modulo " + m + ": " + inverse);
        } catch (ArithmeticException e) {
            // 如果不存在逆元，则打印异常信息  
            System.out.println(e.getMessage());
        }
    }
}