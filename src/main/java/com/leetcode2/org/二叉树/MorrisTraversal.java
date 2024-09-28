package com.leetcode2.org.二叉树;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class MorrisTraversal {
    public void morrisInorderTraversal(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                // 如果当前节点没有左子树，访问该节点，并移动到右子树
                System.out.print(cur.val + " ");
                cur = cur.right;
            } else {
                // 找到当前节点在中序遍历中的前驱节点
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // 前驱节点的右指针为空，建立连接并移动到左子树
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    // 前驱节点的右指针已经指向了当前节点，删除连接，访问当前节点
                    pre.right = null;
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                }
            }
        }
    }
}
