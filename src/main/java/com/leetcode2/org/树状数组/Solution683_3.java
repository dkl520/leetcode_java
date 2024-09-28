package com.leetcode2.org.树状数组;

public class Solution683_3 {
    static class BinaryIndexedTree {
        private int size; // 树状数组的大小
        private int[] tree; // 存储树状数组的值

        public BinaryIndexedTree(int size) {
            this.size = size;
            this.tree = new int[size + 1]; // 初始化树状数组
        }

        // 在位置 x 更新树状数组，增加 delta
        public void update(int index, int delta) {
            for (; index <= size; index += index & -index) { // 遍历树状数组结构
                tree[index] += delta; // 更新值
            }
        }

        // 查询从 1 到 index 的前缀和
        public int query(int index) {
            int sum = 0; // 用于累加和
            for (; index > 0; index -= index & -index) { // 遍历树状数组结构
                sum += tree[index]; // 将当前值加到和中
            }
            return sum; // 返回累加的和
        }
    }

    public int kEmptySlots(int[] bulbs, int k) {
        int length = bulbs.length; // 灯泡的数量
        // 初始化一个树状数组，用于跟踪已点亮的灯泡
        BinaryIndexedTree bit = new BinaryIndexedTree(length);
        // 数组，用于标记每个位置是否已点亮
        boolean[] isLit = new boolean[length + 1];

        // 遍历每个灯泡
        for (int i = 1; i <= length; ++i) {
            int currentPosition = bulbs[i - 1]; // 当前点亮的灯泡位置
            bit.update(currentPosition, 1); // 在树状数组中标记灯泡已点亮
            isLit[currentPosition] = true; // 更新标记，表示该位置已点亮

            // 检查左侧位置 (currentPosition - k - 1)
            int leftPosition = currentPosition - k - 1;
            if (leftPosition > 0 && isLit[leftPosition] && bit.query(currentPosition - 1) - bit.query(leftPosition) == 0) {
                return i; // 如果找到有效配置，返回当前时刻
            }

            // 检查右侧位置 (currentPosition + k + 1)
            int rightPosition = currentPosition + k + 1;
            if (rightPosition <= length && isLit[rightPosition] && bit.query(rightPosition - 1) - bit.query(currentPosition) == 0) {
                return i; // 如果找到有效配置，返回当前时刻
            }
        }
        return -1; // 如果没有找到有效配置，返回 -1
    }
}