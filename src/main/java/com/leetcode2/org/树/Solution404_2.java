package com.leetcode2.org.树;

public class Solution404_2 {
    public int sumOfLeftLeaves(TreeNode root) {
        return recursion(root);
    }

    int recursion(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null) {
            if (isLeafNode(root.left)) {
                sum += (int) root.left.val;
            } else {
                sum += recursion(root.left);
            }
        }
        if (root.right != null) {
            sum += recursion(root.right);
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTreeFromArray(new Object[]{3, 9, 20, null, null, 15, 7});
        Solution404_2 solution404 = new Solution404_2();
        System.out.println(solution404.sumOfLeftLeaves(root));
    }
}
