package com.leetcode2.org.数组;

public class Solution3244 {

    /**
     * 计算每次查询后的最短距离。
     * @param n 城市的数量（从 0 到 n-1 表示城市）
     * @param queries 查询数组，每个查询包含两个元素：[start, end]
     * @return 返回每次查询后剩余的最短距离。
     */
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // 初始化一个数组，表示每个城市的下一个城市
        int[] roads = new int[n];
        for (int i = 0; i < n; i++) {
            roads[i] = i + 1; // 默认每个城市的下一个城市是 i+1
        }

        // 用于存储每次查询的结果
        int[] res = new int[queries.length];

        // 当前有效的最短距离为 n - 1，表示 n 个城市之间的初始道路数
        int dist = n - 1;

        // 遍历每个查询
        for (int i = 0; i < queries.length; i++) {
            int startCity = queries[i][0]; // 查询的起始城市
            int endCity = queries[i][1];  // 查询的结束城市

            // 获取起始城市连接的下一个城市
            int k = roads[startCity];

            // 更新起始城市的连接为查询的结束城市
            roads[startCity] = endCity;

            // 遍历起始城市到结束城市之间的路径，将这些城市的连接断开
            while (k != -1 && k < endCity) {
                int t = roads[k]; // 暂存当前城市的下一个连接城市
                roads[k] = -1;    // 将当前城市的连接设置为无效
                k = t;            // 跳转到下一个城市
                dist--;           // 道路总数减少
            }

            // 记录当前查询后剩余的道路数量
            res[i] = dist;
        }
        return res; // 返回结果数组
    }
}
