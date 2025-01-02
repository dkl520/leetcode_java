package com.leetcode2.基本api;

import java.util.regex.*;

public class RegexExamples {
    public static void main(String[] args) {
        String input = "Java 101, Java 102, Java 103";
        String pattern = "Java \\d+";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);

        while (matcher.find()) {
            System.out.println(matcher.group()); // 输出: Java 101, Java 102, Java 103
        }
    }
}
