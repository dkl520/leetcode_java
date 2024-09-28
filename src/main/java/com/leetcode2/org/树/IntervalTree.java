package com.leetcode2.org.树;

// 定义区间类
class Interval {
    int start; // 区间的起始位置
    int end;   // 区间的结束位置

    // 构造函数，用于创建Interval对象
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // 重写toString方法，方便打印区间信息
    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

// 定义区间树节点类
class IntervalTreeNode {
    Interval interval; // 存储当前节点的区间
    int max;           // 存储以当前节点为根的子树中所有区间的最大结束位置
    IntervalTreeNode left, right; // 左右子节点

    // 构造函数，用于创建IntervalTreeNode对象
    public IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.max = interval.end; // 初始化max为当前区间的结束位置
        this.left = this.right = null;
    }
}

// 定义区间树类
public class IntervalTree {
    private IntervalTreeNode root; // 区间树的根节点

    // 构造函数，初始化区间树
    public IntervalTree() {
        root = null;
    }

    // 插入新的区间到区间树中
    public void insert(Interval interval) {
        root = insert(root, interval);
    }

    // 递归插入区间
    private IntervalTreeNode insert(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            // 如果当前节点为空，则创建一个新的节点并返回
            return new IntervalTreeNode(interval);
        }

        // 根据区间的起始位置决定插入到左子树还是右子树
        if (interval.start < node.interval.start) {
            node.left = insert(node.left, interval);
        } else {
            node.right = insert(node.right, interval);
        }

        // 更新当前节点的max值
        if (node.max < interval.end) {
            node.max = interval.end;
        }

        // 返回更新后的节点
        return node;
    }

    // 查找所有与给定区间重叠的区间
    public void search(Interval interval) {
        search(root, interval);
    }
    // 递归查找重叠区间
    private void search(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            // 如果当前节点为空，则返回
            return;
        }
        // 检查当前节点区间是否与给定区间重叠
        if (doOverlap(node.interval, interval)) {
            System.out.println("Overlap with interval: " + node.interval);
        }
        // 如果左子树的最大结束位置大于等于给定区间的起始位置，则继续向左子树查找
        if (node.left != null && node.left.max >= interval.start) {
            search(node.left, interval);
        }
        // 无论左子树是否重叠，都需要向右子树查找，因为可能存在与给定区间重叠但不与左子树重叠的区间
        search(node.right, interval);
    }

    // 检查两个区间是否重叠
    private boolean doOverlap(Interval i1, Interval i2) {
        // 如果i1的结束位置大于等于i2的起始位置，且i2的结束位置大于等于i1的起始位置，则两个区间重叠
        return i1.start <= i2.end && i2.start <= i1.end;
    }
}