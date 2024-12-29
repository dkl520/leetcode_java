package com.leetcode2.org.栈;

import java.util.Stack;

public class Solution84 {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // 用于存储柱子的索引

        for (int i = 0; i <= heights.length; i++) {
            // 如果当前柱子高度小于栈顶柱子高度，说明找到了右边界
            while (!stack.isEmpty() &&
                    (i == heights.length || heights[i] < heights[stack.peek()])) {
                int height = heights[stack.pop()]; // 弹出栈顶柱子的高度
                // 计算宽度
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = height * width; // 计算面积
                System.out.println(height + " hhhhh " + area);
                maxArea = Math.max(maxArea, area); // 更新最大面积
            }
            stack.push(i); // 将当前柱子索引入栈
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {4, 3, 5, 2};
        Solution84 solution84 = new Solution84();
        System.out.println(
                solution84.largestRectangleArea(heights)
        );


    }
}