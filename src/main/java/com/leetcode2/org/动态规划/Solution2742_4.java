package com.leetcode2.org.动态规划;

public class Solution2742_4 {
    // 墙壁的数量
    private int n;
    // 每个墙壁的粉刷成本
    private int[] cost;
    // 每个墙壁粉刷所需的时间
    private int[] time;
    // 动态规划表，用于存储已计算过的结果，避免重复计算
    private Integer[][] f;

    // 主要的函数，用于计算并返回粉刷墙壁的最小成本
    public int paintWalls(int[] cost, int[] time) {
        // 初始化墙壁数量
        n = cost.length;
        // 初始化粉刷成本和时间数组
        this.cost = cost;
        this.time = time;
        // 初始化动态规划表，大小为 n x (2n+1)，因为 j 可能为负值（表示额外的时间），但 Java 不支持负索引，所以通过偏移实现
        f = new Integer[n][n << 1 | 1]; // n << 1 | 1 等同于 2n+1
        // 从第一个墙壁开始，计算到最后一个墙壁，并且不限制额外时间（即 j 为 n）
//        System.out.println(f);
        int result = dfs(0, n);
        return result;
    }
    // 深度优先搜索函数，用于计算粉刷墙壁的最小成本
    // i 表示当前要处理的墙壁索引，j 表示当前可用的额外时间（相对于 n 的偏移量）
    private int dfs(int i, int j) {
        // 如果剩余墙壁数量小于或等于额外时间（偏移量）所能提供的天数，则不需要粉刷更多墙壁，成本为 0
        if (n - i <= j - n) {
            return 0;
        }
        // 如果已经超出了墙壁的索引范围，说明无法在规定时间内完成粉刷，返回一个大数作为无效值
        if (i >= n) {
            return 1 << 30; // 返回一个足够大的数，表示无效或不可达的状态
        }
        // 如果当前状态的结果已经计算过，则直接返回结果
        if (f[i][j] == null) {
            // 计算当前墙壁粉刷或不粉刷的最小成本
            // 粉刷当前墙壁的成本为粉刷成本加上剩余墙壁在剩余时间内的最小成本
            // 不粉刷当前墙壁的成本为剩余墙壁在剩余时间减一（因为当前时间没有使用）的最小成本
            f[i][j] = Math.min(dfs(i + 1, j + time[i]) + cost[i], dfs(i + 1, j - 1));
            System.out.println(f[i][j]);
        }
        // 返回计算得到的最小成本
        return f[i][j];
    }
    public static void main(String[] args) {
        Solution2742_4 solution = new Solution2742_4();
        int[] cost1 = {1, 2, 3, 2};
        int[] time1 = {1, 2, 3, 2};
        System.out.println(solution.paintWalls(cost1, time1)); // 输出：3
    }
}