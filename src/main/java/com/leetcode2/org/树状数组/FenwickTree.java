package com.leetcode2.org.树状数组;

public class FenwickTree {
    private int[] BIT;
    private int n;

    public FenwickTree(int size) {
        this.n = size;
        this.BIT = new int[size + 1]; // 索引从 1 开始
    }

    // 更新操作：将第 k 个元素增加 delta
    public void update(int k, int delta) {
        while (k <= n) {
            BIT[k] += delta;
            k += k & -k; // 移动到下一个区间
        }
    }

    // 查询操作：求前缀和 sum(1, k)
    public int sum(int k) {
        int sum = 0;
        while (k > 0) {
            sum += BIT[k];
            k -= k & -k; // 移动到上一个区间
        }
        return sum;
    }

    // 查询区间和：求 sum(l, r)
    public int rangeSum(int l, int r) {
        return sum(r) - sum(l - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        FenwickTree ft = new FenwickTree(arr.length);

        // 建树
        for (int i = 0; i < arr.length; i++) {
            ft.update(i + 1, arr[i]);
        }

        // 查询前缀和
        System.out.println("Sum of first 3 elements: " + ft.sum(3)); // 输出 6 (1+2+3)

        // 查询区间和
        System.out.println("Sum of elements from 2 to 4: " + ft.rangeSum(2, 4)); // 输出 9 (2+3+4)
    }
}
