package com.leetcode2.org.数据结构;

import java.util.*;

//
public class Solution3161_2 {

    static class Fenwick {
        private final int[] tree;

        public Fenwick(int size) {
            tree = new int[size]; // 初始化树状数组
        }

        public void update(int i, int val) {
            for (; i < tree.length; i += i & -i) { // 更新树状数组
                tree[i] = Math.max(tree[i], val); // 取最大值
            }
        }

        public int preMax(int i) {
            int res = 0; // 初始化结果
            for (; i > 0; i &= i - 1) { // 查询前缀最大值
                res = Math.max(res, tree[i]); // 取最大值
            }
            return res; // 返回结果
        }
    }

    public static List<Boolean> getResults(int[][] queries) {
        int maxPosition = 0; // 初始化最大位置
        List<Integer> positions = new ArrayList<>(); // 初始化位置列表
        positions.add(0); // 添加初始位置
        for (int[] query : queries) { // 遍历查询
            maxPosition = Math.max(maxPosition, query[1]); // 更新最大位置
            if (query[0] == 1) { // 如果是添加障碍物
                positions.add(query[1]); // 添加位置
            }
        }
        maxPosition++; // 增加最大位置
        Collections.sort(positions); // 排序位置列表

        TreeSet<Integer> set = new TreeSet<>(positions); // 初始化有序集合
        set.add(maxPosition); // 添加哨兵
        Fenwick fenwickTree = new Fenwick(maxPosition); // 初始化树状数组
        for (int i = 1; i < positions.size(); i++) { // 遍历位置列表
            fenwickTree.update(positions.get(i), positions.get(i) - positions.get(i - 1)); // 更新树状数组
        }

        List<Boolean> results = new ArrayList<>(); // 初始化结果列表
        for (int i = queries.length - 1; i >= 0; i--) { // 逆序遍历查询
            int[] query = queries[i]; // 获取当前查询
            int x = query[1]; // 获取位置
            int pre = set.floor(x - 1); // 获取左侧最近障碍物的位置
            if (query[0] == 1) { // 如果是移除障碍物
                set.remove(x); // 移除位置
                int next = set.ceiling(x); // 获取右侧最近障碍物的位置
                fenwickTree.update(next, next - pre); // 更新树状数组
            } else { // 如果是查询
                int maxGap = Math.max(fenwickTree.preMax(pre), x - pre); // 计算最大间隔
                results.add(maxGap >= query[2]); // 添加结果
            }
        }
        Collections.reverse(results); // 反转结果列表
        return results; // 返回结果列表
    }
}