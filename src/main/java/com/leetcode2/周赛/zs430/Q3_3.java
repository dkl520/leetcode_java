package com.leetcode2.周赛.zs430;

import java.util.*;

public class Q3_3 {
    /**
     * 计算子序列的数量
     * @param nums 输入的整数数组
     * @return 子序列的数量
     */
    public long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        // map1和map2用于存储特定键值对的索引列表
        Map<Integer, List<Integer>> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i - 1; ++j) {
                // 计算nums[j]和nums[i]的最大公约数
                int g = gcd(nums[j], nums[i]);
                // 将计算出的键值对存入map1和map2
                map1.computeIfAbsent(nums[j] / g * 2000 + nums[i] / g, key -> new ArrayList<>()).add(i);
                map2.computeIfAbsent(nums[i] / g * 2000 + nums[j] / g, key -> new ArrayList<>()).add(j);
            }
        }
        long ans = 0;
        // 遍历map1中的每个键值对
        for (Map.Entry<Integer, List<Integer>> e : map1.entrySet()) {
            int k = e.getKey();
            List<Integer> v = e.getValue();
            // 获取map2中对应键的值
            List<Integer> v2 = map2.getOrDefault(k, List.of());
            if (v2.size() > 0)
                Collections.sort(v2); // 对v2进行排序
            int j = -1;
            int tmp = 0;
            // 遍历v2中的每个元素
            for (int x : v2) {
                // 更新j的值，使得v.get(j + 1) + 1 < x
                while (j + 1 < v.size() && v.get(j + 1) + 1 < x) ++j;
                ans += j + 1; // 更新答案
                tmp += j + 1; // 更新临时变量
            }
        }
        return ans; // 返回最终答案
    }

    /**
     * 计算两个数的最大公约数
     * @param a 整数a
     * @param b 整数b
     * @return a和b的最大公约数
     */
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}