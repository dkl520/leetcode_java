package com.leetcode2.org.二叉树;


import java.util.ArrayDeque;
import java.util.Queue;

public class Solution1367 {
    //  Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    //      Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public boolean isSubPath(ListNode head, TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.val == head.val) {
                if (dfs(head, curNode)) {
                    return true;
                }
            }
            if (curNode.left != null) {
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }

        }
        return false;


    }

    boolean dfs(ListNode head, TreeNode root) {

        if (head.val != root.val) return false;
        if (head.next == null) return true;
        if (root.left != null && root.right != null) {
            return dfs(head.next, root.left) || dfs(head.next, root.right);
        }
        if (root.left != null) {
            return dfs(head.next, root.left);
        }
        if (root.right != null) {
            return dfs(head.next, root.right);
        }
        return false;

    }

}
