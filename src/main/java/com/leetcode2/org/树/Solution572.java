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
public class Solution572 {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {


        return recursionBinaryTree(root, subRoot, 0);
    }

    boolean recursionBinaryTree(TreeNode root, TreeNode subRoot, int deep) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (deep == 1) {
            if (root.val == subRoot.val) {
                boolean leftBol = recursionBinaryTree(root.left, subRoot.left, 1);
                boolean rightBol = recursionBinaryTree(root.right, subRoot.right, 1);
                if (leftBol && rightBol) return true;
            }
            return false;
        }
        if (deep == 0) {
            if (root.val == subRoot.val) {
                boolean leftBol = recursionBinaryTree(root.left, subRoot.left, 1);
                boolean rightBol = recursionBinaryTree(root.right, subRoot.right, 1);
                if (leftBol && rightBol) return true;
            }
            return recursionBinaryTree(root.left, subRoot, 0) || recursionBinaryTree(root.right, subRoot, 0);
        }
        return false;
    }


    public static void main(String[] args) {
        Integer[] root = {3, 4, 5, 1, 2};
        Integer[] subRoot = {4, 1, 2};
        TreeNode<Integer> tree = TreeNode.buildTreeFromArray(root);
        TreeNode<Integer> subTree = TreeNode.buildTreeFromArray(subRoot);
    }
}