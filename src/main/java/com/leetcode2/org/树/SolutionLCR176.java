package com.leetcode2.org.树;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class SolutionLCR176 {
    public boolean isBalanced(TreeNode root) {

        return DFS(root) != -1;
    }

    int DFS(TreeNode leaf) {
        if (leaf == null) return 0;
        int left = DFS(leaf.left);
        if (left == -1) return -1;
        int right = DFS(leaf.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Object[] arr = {1, 2, 2, 3, null, null, 3, 4, null, null, 4};
        TreeNode<Object> root = TreeNode.buildTreeFromArray(arr);
        System.out.println(new SolutionLCR176().isBalanced(root));
    }
}
