package com.leetcode2.org.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Solution236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> listP = new ArrayList<TreeNode>();
        List<TreeNode> listQ = new ArrayList<TreeNode>();
        listP = preOrderTraversal(root, p, listP);
        listQ = preOrderTraversal(root, q, listQ);
        Collections.reverse(listP);
        Collections.reverse(listQ);
        for (TreeNode treeNode : listP) {
            for (TreeNode node : listQ) {
                if (treeNode.val == node.val) {
                    return treeNode;
                }
            }
        }
        return null;
    }

    public List<TreeNode> preOrderTraversal(TreeNode root, TreeNode searchedNode, List<TreeNode> list) {
        list.add(root);
        if (root.val == searchedNode.val) return list;
        List<TreeNode> listLeft = new ArrayList<>();
        List<TreeNode> listRight = new ArrayList<>();
        if (root.left != null) {
            listLeft.addAll(list);
            listLeft = preOrderTraversal(root.left, searchedNode, listLeft);

        }
        if (root.right != null) {
            listRight.addAll(list);
            listRight = preOrderTraversal(root.right, searchedNode, listRight);
        }
        List<Object> listPureLeft = listLeft.stream().map(node -> node.val).toList();
        if (listPureLeft.contains(searchedNode.val)) {
            return listLeft;
        } else {
            return listRight;
        }
    }


    public static void main(String[] args) {
        Object[] root = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution236 solution236 = new Solution236();
        TreeNode result = solution236.lowestCommonAncestor(rootTree, p, q);
        System.out.println(result);


    }
}
