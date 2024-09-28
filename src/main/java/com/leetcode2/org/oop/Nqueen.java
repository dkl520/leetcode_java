package com.leetcode2.org.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nqueen {
    private int n;
    char[][] board;
    private List<String> solutions;

    Nqueen(int n) {
        this.n = n;
        this.solutions = new ArrayList<>();
        this.initNQueens();
        this.placeQueens(this.board, 0);
    }

    public void initNQueens() {
        this.board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(this.board[i], '.');
        }
    }

    private void placeQueens(char[][] board, int row) {
        if (row==this.n){
            StringBuilder sb= new StringBuilder();
            for (char[] chars:board){
                  sb.append(chars);
            }
            this.solutions.add(sb.toString());
            return;
        }

        for (int col = 0; col <this.n ; col++) {
              if (this.isSafe(board,row,col)){
                    board[row][col]='Q';
                    this.placeQueens(board,row+1);
                  board[row][col]='.';

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

}
