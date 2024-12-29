package com.leetcode2.org.扫描线;

import java.util.*;

class AVLTree {
    private class Node {
        int key, count, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.count = 1; // 支持重复元素
            this.height = 1;
        }
    }

    private Node root;

    // 获取节点高度
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 计算平衡因子
    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 更新节点高度
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 右旋
    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // 左旋
    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // 平衡节点
    private Node balance(Node node) {
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        updateHeight(node);
        return node;
    }

    // 插入元素
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key == node.key) {
            node.count++;
        } else if (key < node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }
        return balance(node);
    }

    // 删除元素
    public void remove(int key) {
        root = remove(root, key);
    }

    private Node remove(Node node, int key) {
        if (node == null) return null;
        if (key == node.key) {
            if (node.count > 1) {
                node.count--; // 如果有重复元素，仅减少计数
                return node;
            }
            if (node.left == null || node.right == null) {
                return (node.left != null) ? node.left : node.right;
            }
            Node minNode = getMin(node.right);
            node.key = minNode.key;
            node.count = minNode.count;
            minNode.count = 1;
            node.right = remove(node.right, minNode.key);
        } else if (key < node.key) {
            node.left = remove(node.left, key);
        } else {
            node.right = remove(node.right, key);
        }
        return balance(node);
    }

    // 获取最小值
    public int getMin() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        return getMin(root).key;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 判断树是否为空
    public boolean isEmpty() {
        return root == null;
    }
}
public class Solution1851_4_1 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        TreeMap<Integer, List<int[]>> dic = new TreeMap<>();
        for (int[] interval : intervals) {
            dic.computeIfAbsent(interval[0], newList -> new ArrayList<>())
                    .add(new int[]{0, interval[1] - interval[0] + 1});

            dic.computeIfAbsent(interval[1],
                            newList -> new ArrayList<>())
                    .add(new int[]{2, interval[1] - interval[0] + 1});
        }

        for (int i = 0; i < queries.length; i++) {
            dic.computeIfAbsent(queries[i],
                            newList -> new ArrayList<>())
                    .add(new int[]{1, i});
        }
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);

        AVLTree tree = new AVLTree();
        dic.values().forEach(list -> {
            list.sort(Comparator.comparingInt(a -> a[0]));
            for (int[] event : list) {
                if (event[0] == 0) {
                    tree.insert(event[1]); // 插入元素
                } else if (event[0] == 1) {
                    ans[event[1]] = tree.isEmpty() ? -1 : tree.getMin(); // 查询最小值
                } else {
                    tree.remove(event[1]); // 删除元素
                }
            }
        });
        return ans;
    }
}