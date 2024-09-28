package com.leetcode2.org.collection;

import java.util.Arrays;
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList("11", "22", "2222"));
        System.out.println(stack);
        Stack<Integer> stack1 = new Stack<>() {{
            addAll(Arrays.asList(1, 2, 3, 57));
        }};
        stack1.add(22);
        stack1.push(11);
        System.out.println(stack1.empty());
        Integer last = stack1.pop();
        System.out.println(last);
        boolean addBol = stack1.add(22);
        Integer pushInt = stack1.push(111111);

        Integer peekInt = stack1.peek();
//        Arrays.asList(stack1);
        
        System.out.println(peekInt);
        System.out.println(addBol);
        System.out.println(stack1);
        System.out.println(pushInt);
    }
}
