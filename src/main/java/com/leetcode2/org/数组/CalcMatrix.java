package com.leetcode2.org.数组;

public class CalcMatrix {
    int[] transformMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxNum = 0;
        int[] result = {0, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (maxNum == 0) {
                        maxNum = 1;
                        result = new int[]{i, j};
                    }
                    int curNum = calcM(matrix, i, j) + 1;
                    if (maxNum < curNum) {
                        maxNum = curNum;
                        result = new int[]{i, j};
                    }
                }
            }
        }
        return result;
    };

    int calcM(int[][] matrix, int row, int col) {
        int max = Math.max(matrix.length, matrix[0].length);
        for (int i = 1; i < max; i++) {
            int[][] dirs = {
                    {i, i},
                    {i, -i},
                    {-i, -i},
                    {-i, i}
            };
            for (int[] dir : dirs) {
                int newY = row + dir[0];
                int newX = col + dir[1];
                if (newY >= 0 && newX >= 0 && newY < matrix.length && newX < matrix[0].length && matrix[newY][newX] == 1) {
                    continue;
                } else {
                    return i - 1;
                }
            }
        }
        return -1;
    };

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 1, 0}
        };
        CalcMatrix calcMatrix = new CalcMatrix();
        calcMatrix.transformMatrix(matrix);
    }
}
