// 声明一个名为org.数论的包，用于组织相关的类。  
package com.leetcode2.org.数论;

// 导入Java的ArrayList和List类，用于创建和操作列表。  
import java.util.ArrayList;
import java.util.List;

// 声明一个名为Factorization的公共类。  
public class Factorization {

    // 使用试除法将一个数分解为质因数列表  
    public static List<Integer> factorize(int number) {
        // 创建一个空列表来存储质因数  
        List<Integer> factors = new ArrayList<>();

        // 处理特殊情况：小于或等于1的数本身就是一个质因数  
        if (number <= 1) {
            factors.add(number);
            return factors;
        }

        // 试除法：从2开始，逐个尝试是否可以整除给定的数  
        for (int i = 2; i <= number; i++) {
            // 如果当前数i可以整除给定的数number  
            while (number % i == 0) {
                // 将i添加为质因数  
                factors.add(i);
                // 用i来整除number，继续寻找下一个质因数  
                number /= i;
            }
        }

        // 返回质因数列表  
        return factors;
    }

    // 主方法，程序的入口点  
    public static void main(String[] args) {
        // 示例用法：将数字36分解为质因数  
        int number = 36;
        // 调用factorize方法来获取质因数列表  
        List<Integer> factors = factorize(number);

        // 打印结果  
        System.out.println("Prime factors of " + number + ": " + factors);
    }
}
//因子分解算法（Factorization Algorithms）

//这段代码定义了一个Factorization类，其中包含一个静态方法factorize用于将一个整数分解为质因数列表。
//        factorize方法使用试除法来找出给定数的所有质因数，并将它们添加到一个ArrayList中。main方法提供了使用factorize方法的示例，并打印出数字36的质因数列表。
//
//        注意：这段代码没有处理负数的情况，并且对于非质数的分解是完整的，包括重复的因数。例如，对于数字36，质因数分解应该是2, 2, 3, 3，因为36 = 2^2 * 3^2。
//        在实际应用中，可能需要进一步优化算法，例如只存储唯一的质因数及其幂次。