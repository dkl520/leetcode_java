package com.leetcode2.org.dynamicPrograming; // 声明包名为org.dynamicPrograming

public class Leetcode72 { // 声明一个公共类Leetcode72

    // 定义minDistance方法，输入两个字符串word1和word2，返回它们的编辑距离
    public int minDistance(String word1, String word2) {
        int n = word1.length(); // 获取word1的长度
        int m = word2.length(); // 获取word2的长度

        // 如果有一个字符串为空串，那么编辑距离就等于非空串的长度
        if (n * m == 0) {
            return n + m;
        }

        // 创建一个二维数组D，用于存储动态规划的状态
        int[][] D = new int[n + 1][m + 1];

        // 初始化边界状态，即当word1或word2为空串时，编辑距离就是另一个串的长度
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 使用动态规划计算所有状态
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // left表示word1删除一个字符的操作
                int left = D[i - 1][j] + 1;
                // down表示word2删除一个字符的操作
                int down = D[i][j - 1] + 1;
                // left_down表示word1替换一个字符或word2替换一个字符的操作
                int left_down = D[i - 1][j - 1];

                // 如果当前字符不相等，则需要替换操作，left_down加1
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }

                // D[i][j]的值是三种操作中的最小值
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }

        // 返回最后的编辑距离，即D[n][m]
        return D[n][m];
    }

    // 主函数，用于测试minDistance方法
    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        Leetcode72 leetcode72 = new Leetcode72();
        int result = leetcode72.minDistance(word1, word2);
        System.out.println(result); // 输出结果
    }
}