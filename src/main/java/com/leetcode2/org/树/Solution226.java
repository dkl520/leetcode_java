package com.leetcode2.org.树;

import java.util.LinkedList;
import java.util.Queue;

public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            TreeNode temp;
            node.right = left;
            node.left = right;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Object[] root = {4, 2, 7, 1, 3, 6, 9};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution226 solution226 = new Solution226();
        solution226.invertTree(rootTree);
        System.out.println(rootTree);
    }

}
