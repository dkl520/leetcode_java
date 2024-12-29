package com.leetcode2.org.栈;

import java.util.Stack;

public class Solution85 {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] bars = new int[n];
        int area = 0;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    bars[j] = 0;
                } else {
                    bars[j]++;
                }
            }
            area = Math.max(area, largestRectangleArea(bars));
        }

        return area;
    }

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
              /*  stack.isEmpty() 为 true 时（栈为空）：

                宽度 = i
                这种情况发生在当前柱子左侧的所有柱子都比它高
                意味着这个矩形可以向左延伸到最开始的位置（索引0）    所以 宽度为i */
//                当 stack.isEmpty() 为 false 时（栈不为空）：
//
//                宽度 = i - stack.peek() - 1
//                stack.peek() 是左边第一个比当前柱子矮的位置
//                i 是右边第一个比当前柱子矮的位置
//                减1是因为要排除边界柱子本身


                int area = height * width; // 计算面积
//                System.out.println(height + " hhhhh " + area);
                maxArea = Math.max(maxArea, area); // 更新最大面积
            }
            stack.push(i); // 将当前柱子索引入栈
        }

        return maxArea;
    }



    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        Solution85 solution85 = new Solution85();
        System.out.println(
                solution85.maximalRectangle(matrix)
        );


    }

}
