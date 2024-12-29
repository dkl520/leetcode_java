package com.leetcode2.org.大厂;

import java.util.HashMap;
import java.util.HashSet;

public class SolutionStackPairs {
    public static int stockPairs(int[] stocksProfit, int target) {
        HashSet<String> usedPairs = new HashSet<>(); // 用于存储唯一的配对
        HashMap<Integer, Integer> map = new HashMap<>();
        int pairs = 0;

        // 初始化哈希表，记录每个利润的出现次数
        for (int profit : stocksProfit) {
            map.put(profit, map.getOrDefault(profit, 0) + 1);
        }

        // 遍历数组，查找配对
        for (int profit : stocksProfit) {
            int complement = target - profit; // 目标配对的利润

            // 如果找到配对利润，并且两个元素的出现次数都 > 0
            if (map.getOrDefault(profit, 0) > 0 && map.getOrDefault(complement, 0) > 0) {
                // 生成配对的唯一标识（确保 (profit, complement) 和 (complement, profit) 视为同一对）
                if (profit == complement && map.getOrDefault(profit, 0) < 2) continue;
                String pairKey = Math.min(profit, complement) + "," + Math.max(profit, complement);

                // 如果该配对未被使用过
                if (!usedPairs.contains(pairKey)) {
                    pairs++; // 计入一对
                    usedPairs.add(pairKey); // 记录该配对已使用

                    // 减少这两个元素的出现次数
                    map.put(profit, map.get(profit) - 1);
                    map.put(complement, map.get(complement) - 1);
                }
            }
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] stocksProfit1 = {5, 7, 9, 9, 13, 11, 6, 3, 3};
        int target1 = 12;
        System.out.println(stockPairs(stocksProfit1, target1)); // 输出: 3

        int[] stocksProfit2 = {9, 9, 3, 3};
        int target2 = 12;
        System.out.println(stockPairs(stocksProfit2, target2)); // 输出: 1
    }
}
