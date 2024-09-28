package com.leetcode2.org.树;

import java.util.HashMap;

public class Solution437_2 {
    public int pathSum(TreeNode root, int targetSum) {
        // 使用HashMap来存储前缀和以及出现的次数
        HashMap<Long, Integer> prefixSumCount = new HashMap<>();
        // 初始情况下，前缀和为0的路径有1条
        prefixSumCount.put(0L, 1);
        // 开始深度优先搜索
        return dfs(root, prefixSumCount, 0L, targetSum);
    }

    private int dfs(TreeNode node, HashMap<Long, Integer> prefixSumCount, long currentSum, int targetSum) {
        // 基本情况：如果节点为空，则返回0
        if (node == null) {
            return 0;
        }
        // 更新当前路径的前缀和
        currentSum += Long.parseLong(node.val.toString());
        // 查看有多少路径满足条件
        int numPathsToCurr = prefixSumCount.getOrDefault(currentSum - targetSum, 0);
        // 更新当前路径前缀和的计数
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        // 递归计算左右子树的路径数量
        int res = numPathsToCurr + dfs(node.left, prefixSumCount, currentSum, targetSum)
                + dfs(node.right, prefixSumCount, currentSum, targetSum);
        // 回溯当前节点的前缀和计数
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
        return res;
    }

    public static void main(String[] args) {

        Object[] root = {10,5,-3,3,2,null,11,3,-2,null,1};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution437_2 solution437_2 = new Solution437_2();
        int nums = solution437_2.pathSum(rootTree, 8);
        System.out.println(nums);
    }
}

