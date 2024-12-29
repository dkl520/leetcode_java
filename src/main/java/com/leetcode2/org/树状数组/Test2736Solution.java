package com.leetcode2.org.树状数组;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Test2736Solution {
    public static void main(String[] args) {
        try {
            // 读取 nums1
            System.out.println("当前工作目录: " + System.getProperty("user.dir"));
            int[] nums1 = readArrayFromFile("./src/txt/nums1.txt");

            // 读取 nums2
            int[] nums2 = readArrayFromFile("./src/txt/nums2.txt");

            // 读取 queries
            int[][] queries = readQueriesFromFile("./src/txt/queries.txt");

            // 执行解决方案
            long start = System.currentTimeMillis();
            Solution2736_4 solution = new Solution2736_4();
            int[] result = solution.maximumSumQueries(nums1, nums2, queries);
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start) + "ms");
            System.out.println(result.length + "result.length");
            System.out.println(queries.length + "queries.length");
            // 将结果写入文件
            writeResultToFile(result, "./src/txt/result.txt");

        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int[] readArrayFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 去除空白字符并按逗号分割
                String[] values = line.trim().split(",");
                for (String value : values) {
                    if (!value.trim().isEmpty()) {
                        numbers.add(Integer.parseInt(value.trim()));
                    }
                }
            }
        }

        // 转换为 int 数组
        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }

    private static int[][] readQueriesFromFile(String filename) throws IOException {
        List<int[]> queriesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        int x = Integer.parseInt(values[0].trim());
                        int y = Integer.parseInt(values[1].trim());
                        queriesList.add(new int[]{x, y});
                    }
                }
            }
        }

        // 转换为二维数组
        int[][] queries = new int[queriesList.size()][2];
        for (int i = 0; i < queriesList.size(); i++) {
            queries[i] = queriesList.get(i);
        }
        return queries;
    }

    private static void writeResultToFile(int[] result, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(Arrays.toString(result));
        }
    }
}