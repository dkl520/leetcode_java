package com.leetcode2.org.数论;

public class SolutionCombination {

    // 使用暴力遍历方法计算和为S的四位数个数
    public static int combination(int S) {
        int count = 0;

        // 遍历所有四位数（从0000到9999）
        for (int i = 0; i <= 9999; i++) {
            int sum = 0;
            int num = i;

            // 计算每个数字的各位数之和
            for (int j = 0; j < 4; j++) {
                sum += num % 10; // 提取最低位数字并累加到sum中
                num /= 10;       // 将数字右移一位
            }

            // 如果和等于S，计数器加1
            if (sum == S) {
                count++;
            }
        }

        return count; // 返回满足条件的数字个数
    }

    // 使用动态规划计算和为S的四位数个数
    public static int solution(int S) {
        // 如果S不在合理范围内，直接返回0
        if (S < 0 || S > 36) {
            return 0;  // 无效的S值
        }

        // dp[i][j] 表示使用i个数字，和为j的组合数
        int[][] dp = new int[5][S + 1];

        // 初始化：只使用1个数字的情况
        for (int i = 0; i <= 9 && i <= S; i++) {
            dp[1][i] = 1; // 使用1个数字且其值为i，只有1种组合方式
        }

        // 填充动态规划表
        for (int i = 2; i <= 4; i++) {  // i表示当前使用的数字个数（最多4位数）
            for (int j = 0; j <= S; j++) {  // j表示当前数字和
                for (int k = 0; k <= 9 && k <= j; k++) {  // k表示当前位数字（范围0到9）
                    dp[i][j] += dp[i - 1][j - k];  // 使用i个数字和为j的组合数等于之前使用i-1个数字和为j-k的组合数的累加
                }
            }
        }

        return dp[4][S]; // 返回使用4个数字和为S的组合数
    }

    public static void main(String[] args) {
        // 测试例子
        System.out.println("S = 35, Result: " + solution(35)); // 应该返回4
        System.out.println("S = 4, Result: " + solution(4));   // 应该返回35
        System.out.println("S = 2, Result: " + solution(2));   // 应该返回10
    }

}
