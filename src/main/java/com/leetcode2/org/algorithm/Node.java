package com.leetcode2.org.algorithm;

public class Node {
    int value;
    int height;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
