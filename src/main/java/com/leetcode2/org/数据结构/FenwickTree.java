package com.leetcode2.org.数据结构;

public class FenwickTree {
    private int[] tree; // 存储树状数组
    private int size; // 数组大小

    // 构造函数
    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size + 1]; // 多一个位置，方便处理
    }

    // 更新操作
    public void update(int index, int delta) {
        // 将索引调整为 1-based
        index++;
        while (index <= size) {
            tree[index] += delta; // 更新树状数组
            index += index & -index; // 移动到下一个节点
        }
    }

    // 查询前缀和
    public int query(int index) {
        int sum = 0;
        index++;
        while (index > 0) {
            sum += tree[index]; // 累加当前节点的值
            index -= index & -index; // 移动到父节点
        }
        return sum; // 返回前缀和
    }

    // 查询区间和，范围 [left, right]
    public int query(int left, int right) {
        return query(right) - query(left - 1); // 利用前缀和公式
    }

    // 测试用例
    public static void main(String[] args) {
        FenwickTree fenwickTree = new FenwickTree(10);

        // 更新数组
        fenwickTree.update(0, 5);  // nums[0] += 5
        fenwickTree.update(1, 3);  // nums[1] += 3
        fenwickTree.update(2, 8);  // nums[2] += 8

        // 查询前缀和
        System.out.println("前缀和 (0到2): " + fenwickTree.query(2)); // 输出 16
        System.out.println("前缀和 (0到1): " + fenwickTree.query(1)); // 输出 8
        System.out.println("区间和 (1到2): " + fenwickTree.query(1, 2)); // 输出 11
    }
}
