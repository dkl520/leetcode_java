package com.leetcode2.org.树;

import java.util.*;

public class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        List<TreeNode> list = new ArrayList<>();
        list.add(root.left);
        list.add(root.right);
        while (!list.isEmpty()) {
            int start = 0;
            int end = list.size() - 1;
            List<TreeNode> listNew = new ArrayList<>();
            while (start < end) {
                TreeNode left = list.get(start);
                TreeNode right = list.get(end);
                if (left == null && right == null) {
                    start++;
                    end--;
                    continue;
                }
                if (left == null || right == null) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }
                start++;
                end--;
            }
            for (TreeNode node : list) {
                if (node != null) {
                    listNew.add(node.left);
                    listNew.add(node.right);
                }
            }
            list = listNew;
            listNew = new ArrayList<>();
        }
        return true;
    }

    public static void main(String[] args) {
        Object[] root = {2, 3, 3, 4, 5, 5, 4, null, null, 8, 9, 9, 8};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution101 sol = new Solution101();
        System.out.println(sol.isSymmetric(rootTree));
    }
}
