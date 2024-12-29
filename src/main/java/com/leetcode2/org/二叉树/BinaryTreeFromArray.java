package com.leetcode2.org.二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeFromArray {
    public static TreeNode buildTree(Integer[] tree) {
        if (tree == null || tree.length == 0) return null;
        TreeNode root = new TreeNode(tree[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < tree.length) {
            TreeNode current = queue.poll();
            if (tree[index] != null) {
                current.left = new TreeNode(tree[index]);
                queue.add(current.left);
            }
            index++;
            if (index < tree.length && tree[index] != null) {
                current.right = new TreeNode(tree[index]);
                queue.add(current.right);
            }
            index++;
        }
        return root;
    }





}
