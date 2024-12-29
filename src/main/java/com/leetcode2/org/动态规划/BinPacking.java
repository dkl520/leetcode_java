package com.leetcode2.org.动态规划;

// Java 程序，用于使用下一适配算法计算所需的箱子数量。
public class BinPacking {

    // 使用下一适配算法计算所需的箱子数量
    static int calculateBinsRequired(int itemWeights[], int itemCount, int binCapacity) {

        // 初始化箱子数量计数器，以及当前箱子的剩余容量。
        int binCount = 0;
        int remainingCapacity = binCapacity;

        // 遍历每件物品
        for (int i = 0; i < itemCount; i++) {
            // 如果当前物品无法放入当前箱子
            if (itemWeights[i] > remainingCapacity) {
                binCount++; // 开始使用一个新箱子
                remainingCapacity = binCapacity - itemWeights[i];
            } else {
                // 如果当前物品可以放入当前箱子，则减少当前箱子的剩余容量
                remainingCapacity -= itemWeights[i];
            }
        }
        // 返回所需的总箱子数量
        return binCount;
    }

    // 主程序入口
    public static void main(String[] args) {
        int itemWeights[] = { 2, 5, 4, 7, 1, 3, 8 }; // 每件物品的重量
        int binCapacity = 10; // 每个箱子的容量
        int itemCount = itemWeights.length; // 物品总数
        System.out.println("使用下一适配算法所需的箱子数量: "
                + calculateBinsRequired(itemWeights, itemCount, binCapacity));
    }
}

// 此代码由 29AjayKumar 提供。
