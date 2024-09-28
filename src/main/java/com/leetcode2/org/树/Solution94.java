package com.leetcode2.org.树;

import java.util.ArrayList;
import java.util.List;

public class Solution94 {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> inorderList = new ArrayList<Integer>();
        inOrder(root, inorderList);
        return inorderList;
    }

    public void inOrder(TreeNode root, List<Integer> inorderList) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left, inorderList);
        }
        inorderList.add((Integer) root.val);
        if (root.right != null) {
            inOrder(root.right, inorderList);
        }
    }

    public static void main(String[] args) {

        Object[] root = {3, 9, 20, null, null, 15, 7, 1, null};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution94 solution = new Solution94();
        List<Integer> list = solution.inorderTraversal(rootTree);
        System.out.println(list);

    }
}
