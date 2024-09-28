//package com.leetcode2.org.双指针;
//
//public class Solution1147_2 {
//    // 主方法，调用递归分解方法
//    public int longestDecomposition(String text) {
//        return decompose(text, 0, text.length() - 1);
//    }
//
//    // 递归分解方法
//    private int decompose(String text, int left, int right) {
//        // 如果左指针超过右指针，返回0，表示没有更多的段
//        if (left > right) return 0;
//        // 如果左指针等于右指针，返回1，表示剩下一个字符单独作为一段
//        if (left == right) return 1;
//
//        // 尝试从左到右寻找相同的子串
//        for (int len = 1; len <= (right - left + 1) / 2; len++) {
//            // 如果找到相同的子串，则返回2加上去掉这对子串后的递归分解结果
//            if (text.substring(left, left + len).equals(text.substring(right - len + 1, right + 1))) {
//                return 2 + decompose(text, left + len, right - len);
//            }
//        }
//
//        // 如果没有找到相同的子串，返回1，表示剩下的部分作为一个单独的段
//        return 1;
//    }
//
//    public static void main(String[] args) {
//        // 测试用例
//        String text = "elvtoelvto";
//        Solution solution = new Solution();
//        // 输出分解后的段数
//        System.out.println(solution.longestDecomposition(text));  // 输出: 2
//    }
//}
