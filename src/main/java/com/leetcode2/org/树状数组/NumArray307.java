package com.leetcode2.org.树状数组;

import java.util.Map;
import java.util.TreeMap;

//树状数组求区间和。
public class NumArray307 {
    int[] nums;
    BIT binaryIndexTree;

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

    public NumArray307(int[] nums) {
        this.nums = nums;
        this.binaryIndexTree = new BIT(nums.length);
        for (int i = 0; i < nums.length; i++) {
            binaryIndexTree.update(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int dis = val - this.nums[index];
        this.nums[index] = val;
        this.binaryIndexTree.update(index + 1, dis);
    }

    public int sumRange(int left, int right) {
        int leftSum = binaryIndexTree.query(left);
        int rightSum = binaryIndexTree.query(right + 1);
        return rightSum - leftSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray307 numArray307 = new NumArray307(nums);
        System.out.println(numArray307.sumRange(0, 2));
        numArray307.update(1, 2);
        System.out.println(numArray307.sumRange(0, 2));
    }

    public static class Solution2907_4 {
        private static class BIT2D {
            private final int n, m; // n: 总行数, m: 总列数
            private final int[][] tree; // 树状数组的存储结构

            // 构造函数, 初始化二维树状数组
            public BIT2D(int n, int m) {
                this.n = n;
                this.m = m;
                this.tree = new int[n + 1][m + 1]; // 树状数组索引从1开始, 所以需要额外空间
            }

            // 更新点 (x, y) 的值为 max(原值, val)
            public void update(int x, int y, int val) {
                for (int i = x + 1; i <= n; i += i & -i) { // 从下往上更新影响的行
                    for (int j = y + 1; j <= m; j += j & -j) { // 从左往右更新影响的列
                        tree[i][j] = Math.max(tree[i][j], val); // 更新为当前最大值
                    }
                }
            }

            // 查询矩形区域 (0, 0) 到 (x, y) 的最大值
            private int query(int x, int y) {
                int res = 0; // 初始化最大值为0
                for (int i = x + 1; i > 0; i -= i & -i) { // 从上往下遍历行
                    for (int j = y + 1; j > 0; j -= j & -j) { // 从右往左遍历列
                        res = Math.max(res, tree[i][j]); // 更新最大值
                    }
                }
                return res; // 返回矩形区域的最大值
            }
        }

        public int maxProfit(int[] prices, int[] profits) {
            int n = prices.length;
            if (n < 3) return -1; // 至少需要3个点才能构成要求的三段价格

            // 离散化价格数组, 将价格映射到0到压缩后的索引范围
            TreeMap<Integer, Integer> compress = new TreeMap<>();
            for (int price : prices) {
                compress.put(price, 0); // 初始化映射表
            }
            int rank = 0;
            for (Map.Entry<Integer, Integer> entry : compress.entrySet()) {
                entry.setValue(rank++); // 为每个价格分配唯一的索引值
            }

            int m = compress.size(); // 离散化后价格的种类数
            BIT2D bit = new BIT2D(n, m); // 初始化二维树状数组

            // 初始化树状数组, 逐个将价格和利润插入
            for (int i = 0; i < n; i++) {
                bit.update(i, compress.get(prices[i]), profits[i]);
            }

            int res = -1; // 记录最大利润, 初始化为-1表示无法满足要求
            for (int i = 1; i < n - 1; i++) { // 遍历每个可能作为中间点的价格
                int curPrice = prices[i];
                int curRank = compress.get(curPrice);

                // 查询左侧价格较小的最大利润
                int leftMax = bit.query(i - 1, curRank - 1);
                if (leftMax == 0) continue; // 如果左侧没有合法利润, 跳过

                // 查询右侧价格较大的最大利润
                int rightMax = bit.query(n-1, rank-1) - bit.query(i, curRank);
    //            int maxRight = 0;
    //            for (int j = i + 1; j < n; j++) {
    //                if (prices[j] > curPrice) { // 只有右侧价格大于当前价格才有效
    //                    maxRight = Math.max(maxRight, profits[j]);
    //                }
    //            }
    //            if (maxRight == 0) continue; // 如果右侧没有合法利润, 跳过

                // 更新全局最大利润
                res = Math.max(res, leftMax + profits[i] + rightMax);
            }

            return res; // 返回最终最大利润
        }
        public static void main(String[] args) {

            int[] prices = new int[]{10, 2, 3, 4};
            int[] profits = new int[]{100, 2, 7, 10};
            Solution2907_4 solution2907 = new Solution2907_4();
            System.out.println(solution2907.maxProfit(prices, profits));
        }
    }
}
