package com.leetcode2.org.压缩算法;



import com.leetcode2.org.动态规划.Solution91_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution95 {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        List<TreeNode> res = generateSubtrees(1, n);
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null); // 如果 start 大于 end，则没有树，加入 null
            return allTrees;
        }

        // 遍历从 start 到 end 的每个值作为根节点
        for (int i = start; i <= end; i++) {
            // 递归生成所有可能的左子树
            List<TreeNode> leftTrees = generateSubtrees(start, i - 1);
            // 递归生成所有可能的右子树
            List<TreeNode> rightTrees = generateSubtrees(i + 1, end);

            // 将当前节点 i 作为根节点，将所有左子树和右子树组合
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currentTree = new TreeNode(i); // 创建新的根节点 i
                    currentTree.left = left; // 设置左子树
                    currentTree.right = right; // 设置右子树
                    allTrees.add(currentTree); // 将当前树加入结果列表

                }
            }

        }
        return allTrees;
    }
    // 辅助函数：将树转换为列表形式（可选）
    private List<Integer> treeToList(TreeNode root) {
        if (root == null) return null;
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            } else {
                result.add(null);
            }
        }

        // 去掉结果末尾的 null
        while (result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }
    // 测试示例
    public static void main(String[] args) {
        Solution95 generator = new Solution95();
        int n = 3;
        List<TreeNode> trees = generator.generateTrees(n);
        List<List<Integer>> result = new ArrayList<>();
        for (TreeNode tree : trees) {
            result.add(generator.treeToList(tree));
        }
        System.out.println(result);
    }
}

//public class Solution95_2 {
//
//
//
//}
