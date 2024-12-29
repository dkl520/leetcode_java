package com.leetcode2.org.大厂.tiktok;

import java.util.HashMap;
import java.util.Map;

public class SolutionTikTokUser {
    private static final int MOD = 1_000_000_007; // 模数，用于对答案取模

    /**
     * 计算可以替换缺失元素（值为0）以使数组“好”的方法数。
     * 定义“好”的数组：相邻元素之差绝对值不超过1。
     *
     * @param arr 给定数组，其中可能包含0表示缺失元素
     * @return 符合条件的替换方案数
     */
    public int goodArrays(int[] arr) {
        int n = arr.length; // 数组长度
        Map<Integer, Long> prev = new HashMap<>(); // 存储上一个状态的值及其方案数
        Map<Integer, Long> curr; // 存储当前状态的值及其方案数

        // 找到第一个非零元素的位置
        int findFirstLoc = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                findFirstLoc = i;
                break;
            }
        }

        // 如果数组全是0
        if (findFirstLoc == -1) {
            int result = 1;
            for (int i = 0; i < arr.length; i++) {
                result = (result * 3) % MOD; // 每个位置可以是1、2或3，共3种选择
            }
            return result;
        }

        long leftResult = 0; // 左侧的结果值
        prev.put(arr[findFirstLoc], 1L); // 初始化第一个非零元素

        // 从 `findFirstLoc` 向左计算
        for (int i = findFirstLoc - 1; i >= 0; i--) {
            curr = new HashMap<>();
            for (int v : prev.keySet()) { // 遍历上一个状态的所有值
                for (int nextVal = v - 1; nextVal <= v + 1; nextVal++) {
                    // 每个值可以转移到其周围的值（v-1, v, v+1）
                    curr.put(nextVal, (curr.getOrDefault(nextVal, 0L) + prev.get(v)) % MOD);
                }
            }
            prev = curr; // 更新当前状态到上一个状态
        }

        // 统计左侧的所有可能方案数
        for (long count : prev.values()) {
            leftResult = (leftResult + count) % MOD;
        }

        prev.clear(); // 清空之前的状态

        prev.put(arr[findFirstLoc], 1L); // 初始化第一个非零元素

        // 从 `findFirstLoc` 向右计算
        for (int i = findFirstLoc + 1; i < arr.length; i++) {
            curr = new HashMap<>();
            if (arr[i] == 0) { // 当前元素为0，可以转移到任意值
                for (int v : prev.keySet()) {
                    for (int nextVal = v - 1; nextVal <= v + 1; nextVal++) {
                        curr.put(nextVal, (curr.getOrDefault(nextVal, 0L) + prev.get(v)) % MOD);
                    }
                }
            } else { // 当前元素非0，仅允许合法的转移
                for (int v = arr[i] - 1; v <= arr[i] + 1; v++) {
                    if (prev.containsKey(v)) {
                        curr.put(v, (curr.getOrDefault(v, 0L) + prev.get(v)) % MOD);
                    }
                }
            }
            prev = curr; // 更新当前状态到上一个状态
        }

        long rightResult = 0; // 右侧的结果值

        // 统计右侧的所有可能方案数
        for (long count : prev.values()) {
            rightResult = (rightResult + count) % MOD;
        }

        // 如果左侧或右侧为0，直接返回另一个方向的结果
        if (leftResult == 0) {
            return (int) rightResult;
        } else if (rightResult == 0) {
            return (int) leftResult;
        }

        // 两侧结果相乘并取模
        return (int) (leftResult * rightResult % MOD);
    }

    public static void main(String[] args) {
        SolutionTikTokUser solution = new SolutionTikTokUser();
        int[] arr = {0, 0, 1}; // 示例输入
        System.out.println(solution.goodArrays(arr)); // 输出 9
    }
}
