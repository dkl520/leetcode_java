// 声明一个名为org.StringDemo的包  
package com.leetcode2.org.StringDemo;

// 定义一个公共类NaiveStringMatching  
public class NaiveStringMatching {

    // 定义一个静态方法naiveStringMatch，用于在text字符串中查找pattern字符串  
    public static void naiveStringMatch(String text, String pattern) {
        // 获取text字符串的长度  
        int n = text.length();
        // 获取pattern字符串的长度  
        int m = pattern.length();

        // 外层循环，遍历text字符串，直到text字符串的长度减去pattern字符串的长度  
        for (int i = 0; i <= n - m; i++) {
            // 内层循环的计数器，初始化为0  
            int j;
            // 内层循环，遍历pattern字符串  
            for (j = 0; j < m; j++) {
                // 如果text字符串在i+j位置上的字符与pattern字符串在j位置上的字符不相等  
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    // 跳出内层循环  
                    break;
                }
            }
            // 如果内层循环正常结束（即j等于m），说明找到了匹配  
            if (j == m) {
                // 输出匹配的位置  
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    // 主方法，程序的入口点  
    public static void main(String[] args) {
        // 定义一个text字符串  
        String text = "ABABCABABABCABABCABABABC";
        // 定义一个pattern字符串  
        String pattern = "ABABC";

        // 输出text字符串  
        System.out.println("Text: " + text);
        // 输出pattern字符串  
        System.out.println("Pattern: " + pattern);

        // 调用naiveStringMatch方法，在text字符串中查找pattern字符串  
        naiveStringMatch(text, pattern);
    }
}