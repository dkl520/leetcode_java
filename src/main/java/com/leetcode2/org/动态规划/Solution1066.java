package com.leetcode2.org.动态规划;

import java.util.Arrays;

public class Solution1066 {
    // 使用位掩码记忆化搜索的备忘录数组
    // memo数组大小为2^(自行车数量)，因为我们使用位图来表示自行车的使用状态
    // 最大自行车数量为10，因此数组大小为1024（2^10）
    int memo[] = new int[1024];

    /**
     * 计算两点之间的曼哈顿距离
     * 曼哈顿距离：在二维平面上，两点之间的距离是横坐标之差的绝对值与纵坐标之差的绝对值之和
     *
     * @param worker 工人的坐标 [x, y]
     * @param bike 自行车的坐标 [x, y]
     * @return 两点之间的曼哈顿距离
     */
    private int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    /**
     * 递归+记忆化搜索求解最小总距离分配问题
     * 使用位掩码记录自行车的使用状态，通过动态规划求解最优分配方案
     *
     * @param workers 工人坐标数组
     * @param bikes 自行车坐标数组
     * @param workerIndex 当前正在分配自行车的工人索引
     * @param mask 记录已被分配自行车的位图（二进制状态）
     * @return 最小总距离
     */
    private int minimumDistanceSum(int[][] workers, int[][] bikes, int workerIndex, int mask) {
        // 递归终止条件：所有工人都已分配自行车
        if (workerIndex >= workers.length) {
            return 0;
        }

        // 备忘录优化：如果当前状态已经计算过，直接返回之前的结果
        // 避免重复计算，提高算法效率
        if (memo[mask] != -1)
            return memo[mask];

        // 初始化最小距离和为最大整数值
        int smallestDistanceSum = Integer.MAX_VALUE;

        // 尝试为当前工人分配每一辆未使用的自行车
        for (int bikeIndex = 0; bikeIndex < bikes.length; bikeIndex++) {
            // 通过位运算检查当前自行车是否已被分配
            // (1 << bikeIndex) 创建一个只在bikeIndex位置为1的二进制数
            // mask & (1 << bikeIndex) == 0 表示该位置的自行车未被使用
            if ((mask & (1 << bikeIndex)) == 0) {
                // 递归求解：
                // 1. 计算当前工人到当前自行车的曼哈顿距离
                // 2. 递归求解下一个工人的最小距离和
                // 3. 通过位运算将当前自行车标记为已使用 (mask | (1 << bikeIndex))
                smallestDistanceSum = Math.min(smallestDistanceSum,
                        findDistance(workers[workerIndex], bikes[bikeIndex]) +
                                minimumDistanceSum(workers, bikes, workerIndex + 1,
                                        mask | (1 << bikeIndex)));
            }
        }

        // 将计算结果存入备忘录，避免重复计算
        return memo[mask] = smallestDistanceSum;
    }

    /**
     * 主方法：分配自行车给工人，使总距离最小
     *
     * @param workers 工人坐标数组
     * @param bikes 自行车坐标数组
     * @return 最小总距离
     */
    public int assignBikes(int[][] workers, int[][] bikes) {
        // 初始化备忘录数组，将所有元素设置为-1
        // -1 表示该状态尚未计算
        Arrays.fill(memo, -1);

        // 从第0个工人开始，初始状态mask为0（无自行车被使用）
        return minimumDistanceSum(workers, bikes, 0, 0);
    }
}