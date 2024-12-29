package com.leetcode2.org.数组;

public class Solution59 {
    public int[][] generateMatrix(int n) {

        int[][] grid = new int[n][n];

        int startRow = 0;
        int startCol = 0;
        int row = n - 1;
        int col = n - 1;

        int count = 1;
        int max = (int) Math.pow(n, 2);
        while (count <= max) {
            for (int i = startCol; i < col; i++) {
                grid[startRow][i] = count++;
            }
            for (int i = startRow; i < row; i++) {
                grid[i][col] = count++;
            }
            for (int i = col; i > startCol; i--) {
                grid[row][i] = count++;
            }
            for (int i = row; i > startRow; i--) {
                grid[i][startCol] = count++;
            }
            startCol++;
            startRow++;
            col--;
            row--;
            if (startRow == startCol && startRow == row && startRow == col) {
                grid[startRow][startCol] = count++;
            }


        }


        return grid;
    }

    public static void main(String[] args) {
        Solution59 solution59 = new Solution59();
        solution59.generateMatrix(11);
    }
}
