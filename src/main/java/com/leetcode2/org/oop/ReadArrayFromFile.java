package com.leetcode2.org.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class ReadArrayFromFile {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        File file = new File("E:/javaprog/demo/untitled/src/oop/data.txt");

        // 检查文件的读取权限
        if (file.canRead()) {
            System.out.println("文件可读");
        } else {
            System.out.println("文件不可读");
        }


        int[][] array = readArrayFromFile("data.txt");

        // 打印二维数组
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static int[][] readArrayFromFile(String filename) {
        int[][] array = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            line = line.replaceAll("\\{\\{", "").replaceAll("\\}\\}", ""); // 去除外层的大括号

            String[] rows = line.split("\\},\\{"); // 按照 "},{" 进行分割
            int rowCount = rows.length;
            int colCount = rows[0].split(",").length;

            array = new int[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                String[] elements = rows[i].split(",");
                for (int j = 0; j < colCount; j++) {
                    array[i][j] = Integer.parseInt(elements[j]);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }
}