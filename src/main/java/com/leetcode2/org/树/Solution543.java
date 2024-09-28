package com.leetcode2.org.树;

import java.util.concurrent.atomic.AtomicInteger;

public class Solution543 {
    Integer ans;

    public int diameterOfBinaryTree(TreeNode root) {
        AtomicInteger ans = new AtomicInteger(0);
        getMaxDFS(root, ans);
        return ans.get();
    }

    public int getMaxDFS(TreeNode root,  AtomicInteger ans) {
        if (root == null) return 0;
        int leftMax = getMaxDFS(root.left, ans);
        int rightMax = getMaxDFS(root.right, ans);
        ans.set(Math.max(leftMax + rightMax, ans.get()));
        return Math.max(leftMax, rightMax) + 1;
    }

    public static void main(String[] args) {
        Object[] root = {1, 2, 3,4,5};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution543 solution543 = new Solution543();
        System.out.println(solution543.diameterOfBinaryTree(rootTree));
    }


}
