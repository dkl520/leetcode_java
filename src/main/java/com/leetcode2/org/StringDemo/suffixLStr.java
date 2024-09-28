package com.leetcode2.org.StringDemo;

import java.util.List;

import java.util.*;
public class suffixLStr {
        // 辅助函数：计算后缀 <i/y> 的长度
        public static int suffixLength(int i, int y) {
            return 3 + String.valueOf(i).length() + String.valueOf(y).length();
        }

        public static List<String> solution(String text, int limit) {
            int n = text.length();

            // 尝试从 y = 1 开始，逐步增加消息数量 y
            for (int totalMessages = 1; totalMessages <= n; totalMessages++) {
                List<String> result = new ArrayList<>();
                int index = 0; // 当前文本索引

                for (int messageNumber = 1; messageNumber <= totalMessages; messageNumber++) {
                    int suffixLen = suffixLength(messageNumber, totalMessages);
                    int availableSpace = limit - suffixLen;

                    // 检查是否有足够空间放字符
                    if (availableSpace <= 0) {
                        return new ArrayList<>();  // 如果空间不足，直接返回空列表
                    }

                    // 找到当前消息能容纳的最多字符数
                    int endIndex = Math.min(index + availableSpace, n);
                    result.add(text.substring(index, endIndex) + "<" + messageNumber + "/" + totalMessages + ">");
                    index = endIndex;

                    // 如果文本已经全部分割完，返回结果
                    if (index == n) {
                        return result;
                    }
                }
            }

            return new ArrayList<>();  // 如果无法分割，返回空列表
        }

        public static void main(String[] args) {
            // 测试用例
            String text = "Hello, world!";
            int limit = 10;
            List<String> result = solution(text, limit);

            if (result.isEmpty()) {
                System.out.println("无法在限制内分割文本");
            } else {
                System.out.println(result);
            }
        }
    }
