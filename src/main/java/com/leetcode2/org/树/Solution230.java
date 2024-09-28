package com.leetcode2.org.树;


import java.util.List;
import java.util.ArrayList;

public class Solution230 {
    int result ;

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        inOrderTraversal(root, list, k);
        return result;
    }

    public void inOrderTraversal(TreeNode root, List<Integer> list, int k) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        if (root.left != null) {
            inOrderTraversal(root.left, list, k);
        }
        if (list.size() == k) {
            result = list.get(k - 1);
            return;
        }
        list.add((int) root.val);
        if (root.right != null) {
            inOrderTraversal(root.right, list, k);
        }
    }

    public static void main(String[] args) {
        Object[] root = {5,3,6,2,4,null,null,1};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution230 solution230 = new Solution230();
        System.out.println(solution230.kthSmallest(rootTree, 2));
    }
}
