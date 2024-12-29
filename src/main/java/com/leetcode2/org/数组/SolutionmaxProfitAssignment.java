package com.leetcode2.org.数组;

import java.util.Map;
import java.util.TreeMap;

public class SolutionmaxProfitAssignment {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // 1. 直接构建最优解映射
        TreeMap<Integer, Integer> difficultyToMaxProfit = new TreeMap<>();
        // 对每个难度等级，只保留最大收益
        for (int i = 0; i < difficulty.length; i++) {
            difficultyToMaxProfit.merge(
                    difficulty[i],
                    profit[i],
                    (existing, newProfit) -> Math.max(existing, newProfit)
            );
        }
        // 2. 确保利润是单调递增的
        int maxProfitSoFar = 0;
        TreeMap<Integer, Integer> optimizedMap = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : difficultyToMaxProfit.entrySet()) {
            maxProfitSoFar = Math.max(maxProfitSoFar, entry.getValue());
            optimizedMap.put(entry.getKey(), maxProfitSoFar);
        }

        // 3. 计算总收益
        int totalProfit = 0;
        for (int capacity : worker) {
            Map.Entry<Integer, Integer> bestJob = optimizedMap.floorEntry(capacity);
            if (bestJob != null) {
                totalProfit += bestJob.getValue();
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        // Test case
        int[] difficulty = {68, 35, 52, 47, 86};
        int[] profit =     {67, 17, 1, 81, 3};
        int[] worker =     {92, 10, 85, 84, 82};

        SolutionmaxProfitAssignment solution = new SolutionmaxProfitAssignment();
        System.out.println(solution.maxProfitAssignment(difficulty, profit, worker));
    }
}