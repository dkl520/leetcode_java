package com.leetcode2.org.数据结构;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution3161 {
    // 树状数组类
    static class Fenwick {
        private final int[] tree;

        // 构造函数，初始化树状数组
        public Fenwick(int size) {
            tree = new int[size];
        }

        // 更新树状数组
        public void update(int i, int val) {
            for (; i < tree.length; i += i & -i) {
                tree[i] = Math.max(tree[i], val); // 更新为最大值
            }
        }

        // 查询前缀最大值
        public int preMax(int i) {
            int res = 0;
            for (; i > 0; i &= i - 1) {
                res = Math.max(res, tree[i]); // 获取最大值
            }
            return res;
        }
    }

    // 并查集类
    static class UnionFind {
        public final int[] fa;

        // 构造函数，初始化并查集
        public UnionFind(int size) {
            fa = new int[size];
            for (int i = 1; i < size; i++) {
                fa[i] = i; // 初始化每个节点的父节点为自己
            }
        }

        // 查找并查集的根节点
        public int find(int x) {
            if (fa[x] != x) {
                fa[x] = find(fa[x]); // 路径压缩
            }
            return fa[x];
        }
    }

    // 获取查询结果
    public static List<Boolean> getResults(int[][] queries) {
        int maxPosition = 0; // 初始化最大位置
        List<Integer> positions = new ArrayList<>(); // 初始化位置列表
        positions.add(0); // 添加初始位置

        // 遍历查询，更新最大位置并记录障碍物位置
        for (int[] query : queries) {
            maxPosition = Math.max(maxPosition, query[1]);
            if (query[0] == 1) {
                positions.add(query[1]);
            }
        }
        maxPosition++; // 增加最大位置

        // 初始化并查集和树状数组
        UnionFind left = new UnionFind(maxPosition + 1);
        UnionFind right = new UnionFind(maxPosition + 1);
        Fenwick fenwickTree = new Fenwick(maxPosition);

        // 排序位置列表
        Collections.sort(positions);

        // 更新树状数组和并查集
        for (int i = 1; i < positions.size(); i++) {
            int prev = positions.get(i - 1);
            int curr = positions.get(i);
            fenwickTree.update(curr, curr - prev);
            for (int j = prev + 1; j < curr; j++) {
                left.fa[j] = prev; // 删除 j
                right.fa[j] = curr;
            }
        }
        for (int j = positions.get(positions.size() - 1) + 1; j < maxPosition; j++) {
            left.fa[j] = positions.get(positions.size() - 1); // 删除 j
            right.fa[j] = maxPosition;
        }

        // 处理查询并生成结果
        List<Boolean> results = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
            int x = query[1];
            int pre = left.find(x - 1); // 获取左侧最近障碍物的位置
            if (query[0] == 1) {
                left.fa[x] = x - 1; // 删除 x
                right.fa[x] = x + 1;
                int next = right.find(x); // 获取右侧最近障碍物的位置
                fenwickTree.update(next, next - pre); // 更新树状数组
            } else {
                int maxGap = Math.max(fenwickTree.preMax(pre), x - pre); // 计算最大间隔
                results.add(maxGap >= query[2]); // 添加结果
            }
        }
        Collections.reverse(results); // 反转结果列表
        return results; // 返回结果列表
    }


    public static void main(String[] args) {
        int[][] queries = {{1, 2}, {2, 3, 3}, {2, 3, 1}, {2, 2, 2}};
        System.out.println(getResults(queries));

    }
}