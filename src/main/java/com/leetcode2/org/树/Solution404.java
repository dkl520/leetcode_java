package com.leetcode2.org.树;

public class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        int[] sum = new int[1];
        recursion(root, sum);
        return sum[0];
    }

    void recursion(TreeNode root, int[] sum) {
        if (root == null) return;
        if (root.left != null) {
            if (isLeafNode(root.left)) {
                sum[0] += (int) root.left.val;
            } else {
                recursion(root.left, sum);
            }

        }
        if (root.right != null) recursion(root.right, sum);
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {

        TreeNode root = TreeNode.buildTreeFromArray(new Object[]{3, 9, 20, null, null, 15, 7});
//        TreeNode root = TreeNode.buildTreeFromArray(new Object[]{1, 2, 3, 4, 5});
        Solution404 solution404 = new Solution404();

        System.out.println(solution404.sumOfLeftLeaves(root));
    }

}
