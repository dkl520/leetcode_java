package com.leetcode2.org.栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution496_2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 创建一个HashMap用于存储nums2中每个元素及其下一个更大元素的映射关系  
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 使用Deque（双端队列）作为栈，从nums2的末尾开始遍历  
        Deque<Integer> stack = new ArrayDeque<Integer>();

        // 从nums2的末尾开始遍历  
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            // 如果栈不为空且当前元素大于等于栈顶元素，则弹出栈顶元素，直到栈为空或找到一个小于当前元素的栈顶元素  
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            // 将当前元素及其下一个更大元素（栈顶元素，如果不存在则为-1）存入HashMap  
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            // 将当前元素压入栈中  
            stack.push(num);
        }

        // 初始化结果数组，用于存储nums1中每个元素的下一个更大元素  
        int[] res = new int[nums1.length];
        // 遍历nums1，根据HashMap中的映射关系填充结果数组  
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        // 返回结果数组  
        return res;
    }
}