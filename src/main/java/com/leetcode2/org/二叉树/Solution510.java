package com.leetcode2.org.二叉树;

public class Solution510 {


    public TreeNode inorderSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return findNext(node.right);
        }
        if (node.parent == null) {
            return null;
        }
        if (node.parent.left == node) {
            return node.parent;
        }
        if (node.parent.right == node) {
            return findOrder(node.parent);
        }
        return null;
    }

    public TreeNode findNext(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return  findNext(node.left);
    }

    public TreeNode findOrder(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.parent == null) {
            return null;
        }
        if (node.parent.left == node) {
            return node.parent;
        }
        if (node.parent.right == node) {
            return findOrder(node.parent);
        }
        return null;
    }


}
