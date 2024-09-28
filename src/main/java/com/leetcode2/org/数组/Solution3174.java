package com.leetcode2.org.数组;

import java.util.Stack;

public class Solution3174 {
    public String clearDigits(String s) {
        int len = s.length();
        Stack<Character> list = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                list.pop();
            } else {
                list.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!list.isEmpty()) {
            sb.append(list.pop());
        }
        return sb.reverse().toString();
    }
}
