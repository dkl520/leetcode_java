package com.leetcode2.org.树;

public class Solution543_2 {

    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        dP(root);
        return ans;
    }

    public int dP(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = dP(root.left);
        int maxRight = dP(root.right);
        ans = Math.max(maxLeft + maxRight, ans);
        return Math.max(maxLeft, maxRight) + 1;
    }

    public static void main(String[] args) {
        Object[] root = {1, 2, 3, 4, 5};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution543_2 solution543 = new Solution543_2();
        System.out.println(solution543.diameterOfBinaryTree(rootTree));
    }


}



