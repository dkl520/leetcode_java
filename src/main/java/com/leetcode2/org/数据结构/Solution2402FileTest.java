package com.leetcode2.org.数据结构;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution2402FileTest {
    public static void main(String[] args) {
        String filePath = "src/txt/2402.txt";  // 请替换为你的测试文件路径

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // 读取第一行获取房间数量n
            int n = Integer.parseInt(br.readLine().trim());

            // 读取会议时间
            List<int[]> meetings = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.trim().split(",");
                if (parts.length != 2) {
                    System.err.println("无效的行格式: " + line);
                    continue;
                }

                int[] meeting = {
                        Integer.parseInt(parts[0].trim()),  // 开始时间
                        Integer.parseInt(parts[1].trim())   // 结束时间
                };
                meetings.add(meeting);
            }

            int[][] meetingsArray = meetings.toArray(new int[0][]);

            // 执行测试
            Solution2402 solution = new Solution2402();
            int result = solution.mostBooked(n, meetingsArray);

            // 输出结果
            System.out.println("房间数量: " + n);
            System.out.println("会议数量: " + meetings.size());
            System.out.println("测试结果: " + result);

        } catch (IOException e) {
            System.err.println("读取文件时发生错误: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("数据格式错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}