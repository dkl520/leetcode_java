package com.leetcode2.org.树;

public class Solution124 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        getMaxSum(root);
        return maxSum;

    }

    public int getMaxSum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            maxSum = Math.max(maxSum, (int) root.val);
            return (int) root.val;
        }

        int leftMax = getMaxSum(root.left);
        int rightMax = getMaxSum(root.right);
        leftMax = Math.max(leftMax, 0);
        rightMax = Math.max(rightMax, 0);

        maxSum = Math.max(maxSum, (int) root.val + leftMax + rightMax);
        return Math.max(leftMax, rightMax) + (int) root.val;
    }

    public static void main(String[] args) {
        Object[] root = {-10, 9, 20, null, null, 15, 7};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution124 solution = new Solution124();
        System.out.println(solution.maxPathSum(rootTree));
    }
}
