package com.leetcode2.org.系统设计;

public class TicTacToe348 {
        private final int[] rows;
        private final int[] cols;
        private int diagonal;
        private int antiDiagonal;
        private int n;

        public TicTacToe348(int n) {
            this.n = n;
            this.rows = new int[n];
            this.cols = new int[n];
            this.diagonal = 0;
            this.antiDiagonal = 0;
        }

        public int move(int row, int col, int player) {
            int toAdd = (player == 1) ? 1 : -1;

            // 更新对应的行和列
            rows[row] += toAdd;
            cols[col] += toAdd;

            // 检查主对角线
            if (row == col) {
                diagonal += toAdd;
            }

            // 检查反对角线
            if (row + col == n - 1) {
                antiDiagonal += toAdd;
            }

            // 检查是否满足获胜条件
            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n ||
                    Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
                return player;
            }

            // 没有获胜者返回 0
            return 0;
        }
    }

