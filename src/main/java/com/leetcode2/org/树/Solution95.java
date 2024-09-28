package com.leetcode2.org.树;

import java.util.ArrayList;
import java.util.List;

class Solution95 {
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) return new ArrayList<>();


        return generateSubtrees(1, n);

    }

    List<TreeNode> generateSubtrees(int L, int R) {
        List<TreeNode> res = new ArrayList<>();
        if (L > R) {
            res.add(null);
            return res;
        }
        for (int i = L; i <= R; i++) {

            List<TreeNode> leftSubtrees = generateSubtrees(L, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, R);

            for (TreeNode leftSubtree : leftSubtrees) {
                for (TreeNode rightSubtree : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubtree;
                    root.right = rightSubtree;
                    res.add(root);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 3;
        Solution95 solution = new Solution95();
        System.out.println(solution.generateTrees(n));


    }
}