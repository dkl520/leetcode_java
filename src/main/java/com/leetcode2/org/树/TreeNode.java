package com.leetcode2.org.树;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode<T> {
    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T val) {
        this.val = val;
        this.left = this.right = null;
    }

    public static <T> TreeNode<T> buildTreeFromArray(T[] arr) {
        if (arr.length == 0) return null;

        TreeNode<T> root = new TreeNode<>(arr[0]);
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < arr.length; i += 2) {
            TreeNode<T> current = queue.poll();

            if (arr[i] != null) {
                if (current != null) {
                    current.left = new TreeNode<>(arr[i]);
                    queue.offer(current.left);
                }

            }

            if (i + 1 < arr.length && arr[i + 1] != null) {
                if (current != null) {
                    current.right = new TreeNode<>(arr[i + 1]);
                    queue.offer(current.right);
                }
            }
        }

        return root;
    }
}
