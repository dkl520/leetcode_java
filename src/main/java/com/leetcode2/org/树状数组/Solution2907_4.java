package com.leetcode2.org.树状数组;

import java.util.Map;
import java.util.TreeMap;

public class Solution2907_4 {
    private static class BIT2D {
        private final int n, m;
        private final int[][] tree;

        public BIT2D(int n, int m) {
            this.n = n;
            this.m = m;
            this.tree = new int[n + 1][m + 1];
        }

        public void update(int x, int y, int val) {
            for (int i = x + 1; i <= n; i += i & -i) {
                for (int j = y + 1; j <= m; j += j & -j) {
                    tree[i][j] = Math.max(tree[i][j], val);
                }
            }
        }

        private int query(int x, int y) {
            int res = 0;
            for (int i = x + 1; i > 0; i -= i & -i) {
                for (int j = y + 1; j > 0; j -= j & -j) {
                    res = Math.max(res, tree[i][j]);
                }
            }
            return res;
        }

        // 查询指定区间 [startX, endX] 和 [startY, endY] 的最大值
        public int queryRange(int startX, int endX, int startY, int endY) {
            return query(endX, endY) - query(startX - 1, endY) - query(endX, startY - 1) + query(startX - 1, startY - 1);
        }
    }

    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        if (n < 3) return -1;

        TreeMap<Integer, Integer> compress = new TreeMap<>();
        for (int price : prices) {
            compress.put(price, 0);
        }
        int rank = 0;
        for (Map.Entry<Integer, Integer> entry : compress.entrySet()) {
            entry.setValue(rank++);
        }

        int m = compress.size();
        BIT2D leftBit = new BIT2D(n, m);  // 用于存储左侧的最大利润
        BIT2D rightBit = new BIT2D(n, m); // 用于存储右侧的最大利润

        // 初始化左侧树状数组
        for (int i = 0; i < n; i++) {
            leftBit.update(i, compress.get(prices[i]), profits[i]);
        }

        // 初始化右侧树状数组（从右向左）
        for (int i = n - 1; i >= 0; i--) {
            rightBit.update(n - 1 - i, compress.get(prices[i]), profits[i]);
        }

        int res = -1;
        for (int i = 1; i < n - 1; i++) {
            int curPrice = prices[i];
            int curRank = compress.get(curPrice);

            // 查询左侧较小价格的最大利润
            int leftMax = leftBit.query(i - 1, curRank - 1);
            if (leftMax == 0) continue;

            // 查询右侧较大价格的最大利润
            int rightMax = rightBit.query(n - 1 - (i + 1), m - 1) - rightBit.query(n - 1 - (i + 1), curRank);
            if (rightMax == 0) continue;

            res = Math.max(res, leftMax + profits[i] + rightMax);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{10, 2, 3, 4};
        int[] profits = new int[]{100, 2, 7, 10};
        Solution2907_4 solution2907 = new Solution2907_4();
        System.out.println(solution2907.maxProfit(prices, profits));
    }
}