package com.leetcode2.org.树状数组;

public class NumMatrix {
    static class BIT {
        int[] cnt;
        int n;

        public BIT(int n) {
            this.n = n;
            cnt = new int[n + 1];
        }

        int lowBit(int x) {
            return x & (-x);
        }

        int query(int x) {
            int ans = 0;
            while (x > 0) {
                ans += cnt[x];
                x -= lowBit(x);
            }
            return ans;
        }

        void update(int rank, int val) {
            while (rank <= n) {
                cnt[rank] += val;
                rank += lowBit(rank);
            }
        }
    }
    int m;
    int n;
    int[][] matrix;
    BIT binaryIndexTree;

    public NumMatrix(int[][] matrix) {
        // 增加空矩阵检查
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        this.m = matrix.length;
        this.n = matrix[0].length;
        this.matrix = matrix;
        this.binaryIndexTree = new BIT(m * n);

        // 初始化时直接赋值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                binaryIndexTree.update(calcIndex(i, j) + 1, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int dis = val - this.matrix[row][col];
        this.matrix[row][col] = val;
        this.binaryIndexTree.update(calcIndex(row, col) + 1, dis);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            // 计算每一行的区间和
            int left = calcIndex(i, col1);
            int right = calcIndex(i, col2) + 1;
            sum += binaryIndexTree.query(right) - binaryIndexTree.query(left);
        }
        return sum;
    }

    // 修正拼写错误：clacIndex -> calcIndex
    int calcIndex(int row, int col) {
        return row * n + col;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {
                        {3, 0, 1, 4, 2},
                        {5, 6, 3, 2, 1},
                        {1, 2, 0, 1, 5},
                        {4, 1, 0, 1, 7},
                        {1, 0, 3, 0, 5}
                };

        NumMatrix numMatrix308 = new NumMatrix(matrix);
        System.out.println(numMatrix308.sumRegion(2,1,4,3));
        numMatrix308.update(3,2,2);
        System.out.println(numMatrix308.sumRegion(2,1,4,3));
    }
}