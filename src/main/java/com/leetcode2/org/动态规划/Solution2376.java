package com.leetcode2.org.动态规划;

import java.util.HashSet;
import java.util.Set;

public class Solution2376 {

    public int countSpecialNumbers(int n) {
        // 将整数 n 转换为字符数组（即数字的各位数字）
        char[] a = String.valueOf(n).toCharArray();
        int m = a.length; // 数字 n 的位数
        Set<Integer> s = new HashSet<>(); // 用于存储已遇到的数字，避免重复

        // 计算位数少于 m 位的所有特殊数字的总和
        int ans = 0;
        for (int i = 1; i < m; i++) {
            ans += perm(9, i - 1) * 9; // 计算 i 位数且首位非零的所有可能
        }

        // 计算第一个数字（最高位）的特殊数字数量
        ans += (a[0] - '1') * perm(9, m - 1); // 首位小于 a[0] 的特殊数字
        s.add(a[0] - '0'); // 将首位数字加入集合

        // 处理接下来的每一位数字
        for (int i = 1; i < m; i++) {
            // 计算剩余数字可以形成的有效组合
            for (int j = 0; j < a[i] - '0'; j++) {
                if (!s.contains(j)) { // 如果数字 j 未被使用
                    ans += perm(9 - i, m - i - 1); // 计算剩余的有效组合
                }
            }
            // 如果当前数字 a[i] 已经被使用，停止计算
            if (s.contains(a[i] - '0')) {
                return ans;
            }
            // 将当前数字加入集合
            s.add(a[i] - '0');
        }

        // 加 1 表示 n 自身作为一个特殊数字
        return ans + 1;
    }

    // 计算排列数 P(n, k) 即从 n 个元素中选出 k 个进行排列
    private int perm(int n, int k) {
        int result = 1;
        // 计算排列数
        for (int i = 0; i < k; i++) {
            result *= (n - i);
        }
        return result;
    }
}
