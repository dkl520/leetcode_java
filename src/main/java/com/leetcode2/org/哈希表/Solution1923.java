package com.leetcode2.org.哈希表;

import java.util.*;

public class Solution1923 {
    // 设置一个较大的基数，用于生成哈希值，减少哈希冲突的可能性
    private static final long BASE = 100001;
    // 设置一个较大的模数，用于哈希值取模，防止哈希值过大
    private static final long MOD = (long) 1e11 + 7;

    /**
     * 找出给定路径数组中最长的公共子路径长度
     *
     * @param n     路径的数量
     * @param paths 二维数组，表示各条路径，其中每个路径是一个整数数组
     * @return 最长的公共子路径长度
     */
    public int longestCommonSubpath(int n, int[][] paths) {
        // 初始化最小路径长度为最大整数，用于后续比较
        int minLen = Integer.MAX_VALUE;
        // 遍历所有路径，找到最短的路径长度
        for (int[] path : paths) {
            minLen = Math.min(minLen, path.length);
        }

        // 使用二分查找确定最长公共子路径的长度
        int left = 0, right = minLen;
        while (left < right) {
            // 计算中间长度
            int mid = left + (right - left + 1) / 2;
            // 如果存在长度为mid的公共子路径，则更新左边界
            if (hasCommonSubpath(paths, mid)) {
                left = mid;
            } else {
                // 否则，更新右边界
                right = mid - 1;
            }
        }
        // 循环结束时，left即为最长公共子路径的长度
        return left;
    }

    /**
     * 检查是否存在长度为length的公共子路径
     *
     * @param paths  路径数组
     * @param length 需要检查的子路径长度
     * @return 如果存在，则返回true；否则返回false
     */
    private boolean hasCommonSubpath(int[][] paths, int length) {
        // 用于存储所有路径中共有的哈希值
        Set<Long> commonHashes = null;
        // 遍历每一条路径
        for (int[] path : paths) {
            // 用于存储当前路径中所有可能的长度为length的子路径的哈希值
            Set<Long> currentHashes = new HashSet<>();
            // 当前子路径的哈希值
            long hash = 0;
            // 基数的幂，用于计算哈希值中的权重
            long power = 1;
            // 遍历当前路径的每个节点
            for (int i = 0; i < path.length; i++) {
                // 更新当前子路径的哈希值
                hash = (hash * BASE + path[i]) % MOD;
                // 如果当前节点是子路径的一部分，则更新基数的幂
                if (i < length) {
                    power = (power * BASE) % MOD;
                } else {
                    // 如果当前节点超出了子路径的范围，则从哈希值中移除最早进入子路径的节点的贡献
                    hash = (hash - path[i - length] * power % MOD + MOD) % MOD;
                }
                // 如果当前子路径已经完整（即长度达到了length），则将其哈希值加入集合
                if (i >= length - 1) {
                    currentHashes.add(hash);
                }
            }
            // 如果是第一条路径，则直接将当前路径的哈希值集合作为共有的哈希值集合
            if (commonHashes == null) {
                commonHashes = currentHashes;
            } else {
                // 对于后续路径，取其与之前路径的哈希值集合的交集
                commonHashes.retainAll(currentHashes);
                // 如果交集为空，则说明不存在长度为length的公共子路径
                if (commonHashes.isEmpty()) {
                    return false;
                }
            }
        }
        // 如果遍历完所有路径后，共有的哈希值集合非空，则说明存在长度为length的公共子路径
        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] paths = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
                {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45},
                {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200}
        };
        long startTime = System.currentTimeMillis();
        System.out.println(new Solution1923().longestCommonSubpath(n, paths));
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}