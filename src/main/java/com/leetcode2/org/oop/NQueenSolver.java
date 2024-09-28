package com.leetcode2.org.oop;

import java.util.ArrayList;
import java.util.List;

public class NQueenSolver {
    private int n;
    private List<String> solutions;

    public NQueenSolver(int n) {
        this.n = n;
        this.solutions = new ArrayList<>();
        solveNQueens();
    }

    public void solveNQueens() {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        placeQueens(board, 0);
    }

    private void placeQueens(char[][] board, int row) {
        if (row == n) {
            StringBuilder sb = new StringBuilder();
            for (char[] chars : board) {
                sb.append(chars);
            }
            solutions.add(sb.toString());
//            System.out.println(sb.toString());
            return;
        }
        for (int col = 0; col < n; col++) {
            boolean isSafe = isSafe(board, row, col);
            if (isSafe) {
                board[row][col] = 'Q';
                placeQueens(board, row + 1);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
            int colOffset = row - i;

            if (col - colOffset >= 0 && board[i][col - colOffset] == 'Q') {
                return false;
            }
            if (col + colOffset < n && board[i][col + colOffset] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int num = 4;
        System.out.println("开始");
        NQueenSolver nQueenSolver = new NQueenSolver(num);
        List<String> solutions = nQueenSolver.solutions;
        long endTime = System.currentTimeMillis();

//        for (String solution : solutions) {
//            System.out.println(solution);
//        }

        System.out.println("运行时间为：" + (endTime - startTime) / 1000.0 + "秒");
        System.out.println(num + "皇后的数量为" + solutions.size());
    }
}