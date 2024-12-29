package com.leetcode2.org.树状数组;


class BIT2D {
    int[][] tree;
    int m, n;

    // 构造二维BIT树
    public BIT2D(int m, int n) {
        this.m = m;
        this.n = n;
        this.tree = new int[m + 1][n + 1];
    }

    // 更新BIT树中指定位置的值
    public void update(int row, int col, int val) {
        for (int i = row; i <= m; i += i & -i) {
            for (int j = col; j <= n; j += j & -j) {
                tree[i][j] += val;
            }
        }
    }

    // 查询BIT树中从(1,1)到(row,col)的前缀和
    public int query(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & -i) {
            for (int j = col; j > 0; j -= j & -j) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}

public class NumMatrix308_2 {
    int[][] matrix;
    BIT2D bit;

    // 构造函数，初始化BIT树和矩阵
    public NumMatrix308_2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        this.matrix = new int[m][n];
        this.bit = new BIT2D(m, n);

        // 初始化BIT树和矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.update(i, j, matrix[i][j]);
            }
        }
    }

    // 更新指定矩阵元素的值
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        bit.update(row + 1, col + 1, diff); // BIT树的索引从1开始
    }

    // 查询指定区域的元素和
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return bit.query(row2 + 1, col2 + 1)
                - bit.query(row1, col2 + 1)
                - bit.query(row2 + 1, col1)
                + bit.query(row1, col1);
    }
}
