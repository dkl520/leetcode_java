package com.leetcode2.org.动态规划;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestSolution1871 {

    public static void main(String[] args) {
        String filePath = "src/txt/1871.text"; // 文件路径
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // 读取 minJump
            int minJump = Integer.parseInt(reader.readLine().trim());
            // 读取 maxJump
            int maxJump = Integer.parseInt(reader.readLine().trim());
            // 读取字符串 s
            String s = reader.readLine().trim();

            reader.close();

            // 创建 Solution1871 实例
            Solution1871 solution = new Solution1871();

            // 记录开始时间
            long startTime = System.currentTimeMillis();

            // 调用 canReach 方法
            boolean result = solution.canReach(s, minJump, maxJump);

            // 记录结束时间
            long endTime = System.currentTimeMillis();

            // 输出结果和运行时间
            System.out.println("Can reach: " + result);
            System.out.println("贪心：Execution time: " + (endTime - startTime) + " ms");

            System.out.println("贪心：EndTime: " + endTime + " ms");
            Solution1871_2 solution2 = new Solution1871_2();

            // 记录开始时间
            startTime = System.currentTimeMillis();

            // 调用 canReach 方法
            result = solution2.canReach(s, minJump, maxJump);

            // 记录结束时间
            endTime = System.currentTimeMillis();

            // 输出结果和运行时间
            System.out.println("Can reach: " + result);
            System.out.println("动态规划： Execution time: " + (endTime - startTime) + " ms");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers: " + e.getMessage());
        }
    }
}
