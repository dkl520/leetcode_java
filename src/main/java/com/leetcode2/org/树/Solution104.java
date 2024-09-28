package com.leetcode2.org.树;

public class Solution104 {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;

    }


    public static void main(String[] args) {
        Object[] root = {3, 9, 20, null, null, 15, 7,1,null};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution104 solution104 = new Solution104();
        int result = solution104.maxDepth(rootTree);
        System.out.println(result);


    }
}
