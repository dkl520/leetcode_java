package com.leetcode2.基本api;

import java.io.*;

public class IOExamples {
    public static void main(String[] args) throws IOException {
        // 文件读取和写入
        File file = new File("example.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Hello, Java I/O!");
                 // 换行
//            writer.newLine();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 输出: Hello, Java I/O!
            }
        }
    }
}
