package com.leetcode2.org.数组;

import java.util.ArrayList;
import java.util.List;

class Solution3256 {
    // 存储输入的二维数组
    private int[][] board;
    // 数组的行数
    private int nRows;
    // 数组的列数
    private int nCols;
    // 用于标记某行是否被排除在搜索范围外
    private boolean[] excludeRows;
    // 用于标记某列是否被排除在搜索范围外
    private boolean[] excludeCols;

    /**
     * 计算二维数组中的最大值和
     * @param board 输入的二维数组
     * @return 返回可能的最大值和
     */
    public long maximumValueSum(int[][] board) {
        // 初始化成员变量
        this.board = board;
        nRows = board.length;
        nCols = board[0].length;
        excludeRows = new boolean[nRows];
        excludeCols = new boolean[nCols];

        // 获取数组中的最大值点坐标
        int[] maxP = getMaxValuePoint(null);
        // 返回两种计算方案中的较大值
        return Math.max(maxValueSum1(maxP), maxValueSum2(maxP));
    }

    /**
     * 获取指定坐标点的值
     * @param point 坐标点 [行,列]
     * @return 该坐标点的值
     */
    private long getValue(int[] point) {
        return board[point[0]][point[1]];
    }

    /**
     * 第一种计算最大值和的方案
     * 选择一个最大值点后，在其行和列中分别找次大值
     * @param maxP 最大值点的坐标
     * @return 计算得到的最大值和
     */
    private long maxValueSum1(int[] maxP) {
        // 在最大值点所在行中找最大值和次大值（不包括最大值点）
        int rowMaxVal1 = Integer.MIN_VALUE;
        int rowMaxVal2 = Integer.MIN_VALUE;
        for(int i = 0; i < nCols; i++) {
            if(i == maxP[1]) {
                continue;
            }
            int value = board[maxP[0]][i];
            if(rowMaxVal1 < value) {
                rowMaxVal2 = rowMaxVal1;
                rowMaxVal1 = value;
            } else if(rowMaxVal2 < value) {
                rowMaxVal2 = value;
            }
        }

        // 在最大值点所在列中找最大值和次大值（不包括最大值点）
        int colMaxVal1 = Integer.MIN_VALUE;
        int colMaxVal2 = Integer.MIN_VALUE;
        for(int i = 0; i < nRows; i++) {
            if(i == maxP[0]) {
                continue;
            }
            int value = board[i][maxP[1]];
            if(colMaxVal1 < value) {
                colMaxVal2 = colMaxVal1;
                colMaxVal1 = value;
            } else if(colMaxVal2 < value) {
                colMaxVal2 = value;
            }
        }

        // 遍历其他位置，计算可能的最大值和
        long maxSum = Long.MIN_VALUE;
        for(int i = 0; i < nRows; i++) {
            if(i == maxP[0]) {
                continue;
            }
            for(int j = 0; j < nCols; j++) {
                if(j == maxP[1]) {
                    continue;
                }
                // 当前点的值
                long num1 = board[i][j];
                // 在列方向上选择合适的值（如果当前值是最大值则选次大值）
                long num2 = board[i][maxP[1]] == colMaxVal1 ? colMaxVal2 : colMaxVal1;
                // 在行方向上选择合适的值（如果当前值是最大值则选次大值）
                long num3 = board[maxP[0]][j] == rowMaxVal1 ? rowMaxVal2 : rowMaxVal1;
                maxSum = Math.max(maxSum, num1 + num2 + num3);
            }
        }
        return maxSum;
    }

    /**
     * 第二种计算最大值和的方案
     * 依次选择三个不同位置的最大值点
     * @param maxP1 第一个最大值点的坐标
     * @return 计算得到的最大值和
     */
    private long maxValueSum2(int[] maxP1) {
        // 记录已选择的点
        List<int[]> excludePoints = new ArrayList<>();
        excludePoints.add(maxP1);
        // 获取第二个最大值点
        int[] maxP2 = getMaxValuePoint(excludePoints);
        excludePoints.add(maxP2);
        // 获取第三个最大值点
        int[] maxP3 = getMaxValuePoint(excludePoints);

        // 在第二个点所在列上寻找最大值（排除已选点所在行）
        long num1 = Integer.MIN_VALUE;
        for(int i = 0; i < nRows; i++) {
            if(i != maxP1[0] && i != maxP2[0]) {
                num1 = Math.max(num1, board[i][maxP2[1]]);
            }
        }
        // 在第二个点所在行上寻找最大值（排除已选点所在列）
        long num2 = Integer.MIN_VALUE;
        for(int i = 0; i < nCols; i++) {
            if(i != maxP1[1] && i != maxP2[1]) {
                num2 = Math.max(num2, board[maxP2[0]][i]);
            }
        }
        // 返回两种可能的最大值和
        return getValue(maxP1) + Math.max(getValue(maxP2) + getValue(maxP3), num1 + num2);
    }

    /**
     * 在未被排除的位置中寻找最大值点
     * @param excludePoints 需要排除的点的列表
     * @return 最大值点的坐标 [行,列]
     */
    private int[] getMaxValuePoint(List<int[]> excludePoints) {
        // 标记需要排除的点
        if(excludePoints != null) {
            for(int[] point : excludePoints) {
                excludeRows[point[0]] = true;
                excludeCols[point[1]] = true;
            }
        }
        // 寻找最大值点
        int maxValRowId = -1;
        int maxValColId = -1;
        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < nRows; i++) {
            if(excludeRows[i]) {
                continue;
            }
            for(int j = 0; j < nCols; j++) {
                if(excludeCols[j]) {
                    continue;
                }
                int value = board[i][j];
                if(maxValue <= value) {
                    maxValue = value;
                    maxValRowId = i;
                    maxValColId = j;
                }
            }
        }
        // 恢复排除标记
        if(excludePoints != null) {
            for(int[] point : excludePoints) {
                excludeRows[point[0]] = false;
                excludeCols[point[1]] = false;
            }
        }
        return new int[]{maxValRowId, maxValColId};
    }
}