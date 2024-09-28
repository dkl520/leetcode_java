// 声明一个名为org.数论的包，通常用于组织相关的类。  
package com.leetcode2.org.数论;

// 声明一个名为PrimalityTesting的公共类。  
public class PrimalityTesting {

    // 定义一个静态方法isPrime，用于检查一个整数是否为质数。  
    public static boolean isPrime(int number) {
        // 处理特殊情况：小于或等于1的数不是质数。  
        if (number <= 1) {
            return false;
        }
        // 2是唯一的偶数质数。  
        if (number == 2) {
            return true;
        }
        // 如果数字是偶数（除了2），则它不是质数。  
        if (number % 2 == 0) {
            return false;
        }

        // 只需要检查到数字的平方根，因为如果n有大于其平方根的因子，那么它必定也有一个小于或等于其平方根的因子。  
        int sqrt = (int) Math.sqrt(number);
        // 从3开始，每次增加2（因为偶数不可能是质数），检查是否可以被整除。  
        for (int i = 3; i <= sqrt; i += 2) {
            // 如果找到了一个可以整除的因子，那么这个数不是质数。  
            if (number % i == 0) {
                return false; // 找到了一个因子，不是质数  
            }
        }

        // 如果检查到这里都没有找到因子，那么这个数是质数。  
        return true; // 没有找到因子，是质数  
    }

    // 主方法，程序的入口点。  
    public static void main(String[] args) {
        // 示例用法：检查17是否为质数。  
        int number = 17;
        // 调用isPrime方法检查number是否为质数。  
        if (isPrime(number)) {
            // 如果是质数，打印出相应的信息。  
            System.out.println(number + " is a prime number.");
        } else {
            // 如果不是质数，打印出相应的信息。  
            System.out.println(number + " is not a prime number.");
        }
    }
}

//素数测试算法（Primality Testing Algorithms）：
//
//这段代码定义了一个PrimalityTesting类，其中包含一个isPrime方法用于检查一个整数是否为质数，以及一个main方法作为程序的入口点
//。isPrime方法使用了一个简单的算法来检查质数性，
//        该算法基于一个事实：如果一个数有大于其平方根的因子，那么它必定也有一个小于或等于其平方根的因子。因此，只需要检查到数字的平方根即可。