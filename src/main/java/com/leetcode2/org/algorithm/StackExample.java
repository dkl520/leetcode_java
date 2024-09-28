package com.leetcode2.org.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackExample {
    public static boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    // 定义一个静态方法，名为isValid，输入一个字符串s，返回一个布尔值
    public static boolean isValid(String s) {
        // 获取输入字符串的长度，并赋值给变量n
        int n = s.length();
        // 如果字符串长度是奇数，返回false，因为一个有效的括号序列的长度应该是偶数
        if (n % 2 == 1) {
            return false;
        }

        // 创建一个映射，存储右括号和左括号的对应关系
        Map<Character, Character> pairs = Map.of(')', '(', ']', '[', '}', '{');
        // 创建一个栈，用于存储字符，栈遵循后进先出（LIFO）的原则
        Stack<Character> stack = new Stack<>();

        // 遍历字符串s中的每个字符
        for (char ch : s.toCharArray()) {
            // 如果当前字符是右括号
            boolean containKey = pairs.containsKey(ch);
            if (containKey) {
                // 检查栈是否为空，或者栈顶的字符是否与当前的右括号匹配
                char sPeek = stack.peek();
                char pGet = pairs.get(ch);
                if (stack.isEmpty() || sPeek != pGet) {
                    // 如果不匹配，返回false，因为这意味着字符串中的括号不匹配
                    return false;
                }
                // 如果匹配，弹出栈顶的字符，继续检查下一个字符
                stack.pop();
            } else {
                // 如果当前字符是左括号，将其压入栈中，等待匹配的右括号出现
                stack.push(ch);
            }
        }
        // 遍历完字符串后，如果栈为空，说明所有的左括号都有与之匹配的右括号，返回true，否则返回false
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        // 创建一个栈对象
        String str = new String("([])");
        System.out.println(isValid(str));
        Stack<Integer> stack = new Stack<>();

        // 入栈操作
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // 查看栈顶元素
        int topElement = stack.peek();
        System.out.println("栈顶元素：" + topElement);

        // 出栈操作
        int poppedElement = stack.pop();
        System.out.println("出栈元素：" + poppedElement);

        // 遍历栈中的元素
        System.out.print("栈中元素：");
        while (!stack.isEmpty()) {
            int element = stack.pop();
            System.out.print(element + " ");
        }
    }
}
