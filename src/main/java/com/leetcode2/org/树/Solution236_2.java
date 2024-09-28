package com.leetcode2.org.树;

public class Solution236_2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 基本情况：如果根节点为null，或根节点是p或q之一
        if (root == null || root == p || root == q) {
            return root;
        }
        // 递归遍历左子树和右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果left和right都不为空，则说明p和q分别在root的左右子树中，root即为最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 否则，返回非空的那个节点
        return left != null ? left : right;
    }

    public static void main(String[] args) {

        Object[] root = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        TreeNode p = rootTree.left;
        TreeNode q = rootTree.right;
        Solution236_2 solution236_2 = new Solution236_2();
        TreeNode result = solution236_2.lowestCommonAncestor(rootTree, p, q);
        System.out.println(result);
    }
}
//代码解释
//基本情况：
//
//如果当前根节点 root 为 null，或当前根节点 root 是节点 p 或节点 q，则返回当前根节点 root。这处理了找到其中一个节点或到达树的末端的情况。
//递归调用：
//
//递归调用 lowestCommonAncestor 函数来查找左子树和右子树中的 p 和 q。
//检查递归调用结果：
//
//如果 left 和 right 都不为空，这意味着 p 和 q 分别位于当前根节点 root 的左右子树中，因此当前根节点 root 就是它们的最近公共祖先。
//如果只有一个非空，则返回那个非空的节点。这意味着 p 和 q 都位于当前根节点的一个子树中，返回子树中的祖先节点。