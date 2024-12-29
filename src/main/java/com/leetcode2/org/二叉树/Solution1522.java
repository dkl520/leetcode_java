package com.leetcode2.org.二叉树;

import java.util.ArrayList;
import java.util.List;

public class Solution1522 {
    // Definition for a Node.
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private int maxDiameter = 0;

    public int diameter(Node root) {
        if (root == null) return 0;
        calculateHeight(root);
        return maxDiameter;
    }

    private int calculateHeight(Node node) {
        if (node == null) return 0;

        // Find the top two longest paths from children
        int firstLongest = 0;
        int secondLongest = 0;

        for (Node child : node.children) {
            int childHeight = calculateHeight(child);

            // Update the two longest paths
            if (childHeight > firstLongest) {
                secondLongest = firstLongest;
                firstLongest = childHeight;
            } else if (childHeight > secondLongest) {
                secondLongest = childHeight;
            }
        }

        // Update max diameter
        maxDiameter = Math.max(maxDiameter, firstLongest + secondLongest);

        // Return the longest path from this node
        return firstLongest + 1;
    }
}