package com.leetcode2.矩阵;

import java.util.Arrays;

public class Solution59 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int left = 0, right = n-1 , top = 0, bottom = n - 1;
        int index = 1;
        int target = (int) Math.pow(n, 2);
        while (index <= target) {

            for (int i = left; i < right; i++) {
                result[top][i] = index++;
            }
            if (index > target) break;
            for (int i = top; i < bottom; i++) {
                result[i][right] = index++;
            }
            if (index > target) break;
            for (int i = right; i > left; i--) {
                result[bottom][i] = index++;
            }
            if (index > target) break;
            for (int i = bottom; i > top; i--) {
                result[i][left] = index++;
            }
            if (index > target) break;
            if(left==right && top==bottom && left==top){
                result[top][right] = index++;
            }

            left++;
            right--;
            top++;
            bottom--;

        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        Solution59 solution = new Solution59();
        System.out.println(Arrays.toString(solution.generateMatrix(n)));
    }
}
