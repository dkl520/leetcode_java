package com.leetcode2.周赛.zs130;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SolutionQ3 {
    static final int INF = 0x3f3f3f3f; // 定义一个大常数值，表示无穷大，用于初始化动态规划数组

    public int minimumSubstringsInPartition(String s) {
        int n = s.length(); // 字符串长度
        int[] d = new int[n + 1]; // 动态规划数组，d[i]表示前i个字符分割的最小子串数量
        Arrays.fill(d, INF); // 将数组初始化为无穷大，表示还未计算的状态
        d[0] = 0; // 初始状态，空字符串分割为0段

        for (int i = 1; i <= n; i++) { // 遍历字符串的每一个位置i
            Map<Character, Integer> occCnt = new HashMap<>(); // 记录子串中每个字符的出现次数
            int maxCnt = 0; // 记录子串中某个字符的最大出现次数
            for (int j = i; j >= 1; j--) { // 从位置i往前扫描，构建子串[j, i]
                char c = s.charAt(j - 1); // 获取当前扫描到的字符
                // 更新字符出现次数
                occCnt.put(c, occCnt.getOrDefault(c, 0) + 1);
                // 更新子串中字符的最大出现次数
                maxCnt = Math.max(maxCnt, occCnt.get(c));

                // 判断当前子串[j, i]是否符合条件
                // 条件：子串长度等于字符种类数 * 最大出现次数
                if (maxCnt * occCnt.size() == (i - j + 1) && d[j - 1] != INF) {
                    // 更新d[i]为从j-1状态转移过来的最小分割数
                    d[i] = Math.min(d[i], d[j - 1] + 1);
                }
            }
        }
        return d[n]; // 返回整个字符串的最小分割数量
    }
}
