package com.leetcode2.org.树;

public class AVLNode {
    int key;
    int height;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
        this.height = 1;
    }
}
