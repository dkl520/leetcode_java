package com.leetcode2.org.图论;

import java.util.*;

class Solution787_2 {
    /**
     * 查找从源点到目标点的最便宜价格，最多中转k次。
     *
     * @param n 节点（城市）的数量
     * @param flights 航班信息，每个航班表示为[起点, 终点, 价格]
     * @param src 起始节点（城市）
     * @param dst 目标节点（城市）
     * @param k 最多中转次数
     * @return 从源点到目标点的最便宜价格，如果无法到达则返回-1
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 初始化价格数组，所有城市的价格初始化为最大值，除了起始城市的价格为0
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        // 遍历k+1次，因为每遍历一次可能产生一次中转
        for (int i = 0; i <= k; i++) {
            // 使用临时数组来存储当前轮次更新后的价格，避免直接修改原数组
            int[] temp = Arrays.copyOf(prices, n);
            // 遍历所有航班
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                // 如果起点城市的价格不是初始的最大值（即已经计算过价格），则更新终点城市的价格
                if (prices[from] != Integer.MAX_VALUE) {
                    temp[to] = Math.min(temp[to], prices[from] + price);
                }
            }
            // 更新原数组为当前轮次的价格
            prices = temp;
        }

        // 如果目标城市的价格仍然是初始的最大值，则表示无法到达，返回-1；否则返回目标城市的价格
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    public static void main(String[] args) {
        // 示例航班信息
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };

        // 节点数量、起始城市、目标城市和最多中转次数
        int n = 5, src = 2, dst = 1, k = 1;
        Solution787_2 solution = new Solution787_2();

        // 调用方法并打印结果
        System.out.println(solution.findCheapestPrice(n, flights, src, dst, k));
    }
}