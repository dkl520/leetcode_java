package com.leetcode2.org.树;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

public class Solution437 {
    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        List<Long> list = new ArrayList<>();
        list.add((long) targetSum);
        getSumDFS(root, list, (long) targetSum);
        return count;
    }

    void getSumDFS(TreeNode root, List<Long> list, long originNum) {
        if (root == null) return;

        // 使用 Collections.frequency 计算 list 中 root.val 的频率，并增加 count
       Long  rootVal = Long.parseLong(root.val.toString());
        int frequency = Collections.frequency(list, rootVal);
        count += frequency;

        // 使用 Collectors.toList() 收集结果并返回一个可变的列表
        list = list.stream().map(v -> v - rootVal).collect(Collectors.toList());

        list.add((long) originNum);

        getSumDFS(root.left, new ArrayList<>(list), originNum);
        getSumDFS(root.right, new ArrayList<>(list), originNum);
    }

    public static void main(String[] args) {
        Object[] root = {1000000000, 1000000000, null, 294967296, null, 1000000000, null, 1000000000, null, 1000000000};
        TreeNode rootTree = TreeNode.buildTreeFromArray(root);
        Solution437 solution437 = new Solution437();
        int nums = solution437.pathSum(rootTree, 0);

    }


}
