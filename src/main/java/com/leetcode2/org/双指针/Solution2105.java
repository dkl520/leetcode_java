package com.leetcode2.org.双指针;

public class Solution2105 {
    /**
     * 计算浇灌完所有植物所需的最少重新装满水桶的次数
     * @param plants 植物需要的水量数组
     * @param capacityA 水桶A的容量
     * @param capacityB 水桶B的容量
     * @return 最少重新装满水桶的次数
     */
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int left = 0; // 左指针，指向当前要浇灌的左侧植物
        int right = plants.length - 1; // 右指针，指向当前要浇灌的右侧植物
        int curCapacityA = capacityA; // 水桶A的当前剩余容量
        int curCapacityB = capacityB; // 水桶B的当前剩余容量
        int count = 0; // 记录重新装满水桶的次数
        // 当左右指针没有相遇时，继续浇灌
        while (left <= right) {
            // 如果左右指针相遇，则只处理一个植物
            if (left == right) {
                // 使用剩余容量最大的水桶浇灌最后一个植物
                int maxCapacity = Math.max(curCapacityA, curCapacityB);
                if (maxCapacity < plants[left]) {
                    // 如果最大剩余容量不足以浇灌最后一个植物，则需要重新装满
                    count++;
                }
                break;
            }
            // 处理左侧植物
            if (plants[left] <= curCapacityA) {
                // 如果水桶A的剩余容量足够浇灌左侧植物
                curCapacityA -= plants[left];
            } else {
                // 如果水桶A的剩余容量不足以浇灌左侧植物，则重新装满水桶A，并增加计数
                curCapacityA = capacityA - plants[left];
                count++;
            }
            left++; // 移动左指针
            // 处理右侧植物
            if (plants[right] <= curCapacityB) {
                // 如果水桶B的剩余容量足够浇灌右侧植物
                curCapacityB -= plants[right];
            } else {
                // 如果水桶B的剩余容量不足以浇灌右侧植物，则重新装满水桶B，并增加计数
                curCapacityB = capacityB - plants[right];
                count++;
            }
            right--; // 移动右指针
        }
        // 返回重新装满水桶的总次数
        return count;
    }

    public static void main(String[] args) {
        int[] plants = {2, 2, 3, 3}; // 植物需要的水量数组
        int capacityA = 5, capacityB = 5; // 水桶A和B的容量
        Solution2105 solution = new Solution2105();
        System.out.println(solution.minimumRefill(plants, capacityA, capacityB)); // 输出浇灌完所有植物所需的最少重新装满水桶的次数
    }
}