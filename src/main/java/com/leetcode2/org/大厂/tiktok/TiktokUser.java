package com.leetcode2.org.大厂.tiktok;
import java.util.HashMap;
import java.util.Map;

public class TiktokUser {
    private static final int MOD = 1_000_000_007; // 模数，用于返回答案的余数

    /**
     * 计算可以替换缺失元素以使数组“好”的方法数
     *
     * @param arr 给定数组
     * @return 符合条件的替换方案数
     */
    public int goodArrays(int[] arr) {
        int n = arr.length;
        Map<Integer, Long> prev = new HashMap<>(); // 上一个状态的映射：dp[i-1][v]
        Map<Integer, Long> curr;

        // 初始化第 0 个元素的替代情况
        if (arr[0] == 0) {
            // 如果当前元素是 0，可以从任何值开始
            for (int v = -1; v <= 1; v++) {
                prev.put(v, 1L); // 初始化为 1，因为从任何值开始的情况都有一种
            }
        } else {
            prev.put(arr[0], 1L); // 如果当前元素不是 0，只能从其自身开始
        }

        // 动态规划遍历数组
        for (int i = 1; i < n; i++) {
            curr = new HashMap<>();

            if (arr[i] == 0) {
                // 当前元素为 0，可以从任何前一个状态转移到任意值 v
                for (int v : prev.keySet()) {
                    for (int nextVal = v - 1; nextVal <= v + 1; nextVal++) {
                        curr.put(nextVal, (curr.getOrDefault(nextVal, 0L) + prev.get(v)) % MOD);
                    }
                }
            } else {
                // 当前元素有具体的数值
                for (int v = arr[i] - 1; v <= arr[i] + 1; v++) {
                    if (prev.containsKey(v)) {
                        curr.put(v, (curr.getOrDefault(v, 0L) + prev.get(v)) % MOD);
                    }
                }
            }
            prev = curr; // 更新为当前状态
        }

        // 计算结果：所有可替换方案的数量之和
        long result = 0;
        for (long count : prev.values()) {
            result = (result + count) % MOD;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        TiktokUser solution = new TiktokUser();
        int[] arr = {0, 0, 1}; // 示例输入
        System.out.println(solution.goodArrays(arr)); // 输出 9
    }
}
