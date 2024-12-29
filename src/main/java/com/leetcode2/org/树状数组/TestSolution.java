package com.leetcode2.org.树状数组;

import java.util.Random;

public class TestSolution {
    public static void main(String[] args) {
        // 测试输入规模
        int n = 100000; // 数组长度，可以根据需求调整
        int lower = -10000;
        int upper = 10000;

        // 随机生成数组
        int[] nums = generateRandomArray(n, -5000, 5000);

        // 测试运行时间
//        Solution327 solution = new Solution327();
//        long startTime = System.currentTimeMillis();
//        int result = solution.countRangeSum(nums, lower, upper);
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("Result: " + result);
//        System.out.println("Execution Time:chatGPT 版本 " + (endTime - startTime) + " ms");

        Solution327_2 solution2 = new Solution327_2();
        long startTime2 = System.currentTimeMillis();
        int result2 = solution2.countRangeSum(nums, lower, upper);
        long endTime2 = System.currentTimeMillis();

        System.out.println("Result: " + result2);
        System.out.println("Execution Time: leetcode 版本" + (endTime2 - startTime2) + " ms");





    }

    // 随机生成一个指定长度和范围的数组
    private static int[] generateRandomArray(int n, int min, int max) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
}
