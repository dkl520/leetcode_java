package com.leetcode2.org.树;

class Solution108 {
    /**
     * 将有序数组转换为平衡二叉搜索树（BST）。
     *
     * @param nums 有序数组
     * @return 平衡BST的根节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 检查输入数组是否为空
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 开始递归转换过程
        return convert(nums, 0, nums.length - 1);
    }
    /**
     * 递归地将子数组转换为平衡BST。
     *
     * @param nums 有序数组
     * @param left 子数组的起始索引
     * @param right 子数组的结束索引
     * @return 子数组的平衡BST的根节点
     */
    private TreeNode convert(int[] nums, int left, int right) {
        // 基本情况：如果左索引大于右索引，则返回null（没有节点）
        if (left > right) {
            return null;
        }
        // 找到当前子数组的中间元素
        int mid = (left + right) / 2;
        // 创建一个新的树节点，值为中间元素的值
        TreeNode node = new TreeNode(nums[mid]);
        // 递归构建左子树，使用左子数组
        node.left = convert(nums, left, mid - 1);
        // 递归构建右子树，使用右子数组
        node.right = convert(nums, mid + 1, right);
        // 返回当前节点作为该子数组BST的根节点
        return node;
    }
}
