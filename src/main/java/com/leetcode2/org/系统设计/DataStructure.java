package com.leetcode2.org.系统设计;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DataStructure {
    //字典树
    static class TrieTree {
        char alpha;
        Map<Character, TrieTree> children;
        TreeSet<String> words;

        public TrieTree(Character alpha) {
            this.alpha = alpha;
            this.children = new HashMap<>();
            this.words = new TreeSet<>();
        }
    }

    //二叉树
    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    // 树状数组
    static class FenwickTree {
        private int[] tree; // 存储树状数组
        private int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1]; // 多一个位置，方便处理
        }

        public void update(int index, int delta) {
            index++;
            while (index <= size) {
                tree[index] += delta; // 更新树状数组
                index += index & -index; // 移动到下一个节点
            }
        }

        public int query(int index) {
            int sum = 0;
            index++;
            while (index > 0) {
                sum += tree[index]; // 累加当前节点的值
                index -= index & -index; // 移动到父节点
            }
            return sum; // 返回前缀和
        }
    }

    //并查集
    static class UnionFind {
        private final int[] parent; // 记录每个元素的父节点
        public UnionFind(int size) {
            parent = new int[size]; // 初始化父节点数组
            for (int i = 0; i < size; i++) {
                parent[i] = i; // 每个节点的初始父节点为其自身
            }
        }
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 递归查找并压缩路径
            }
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }





}
