package com.leetcode2.org.树;
import java.util.Random;

class TreapNode {
    int key;
    int priority;
    int size;
    TreapNode left;
    TreapNode right;

    TreapNode(int key) {
        this.key = key;
        this.priority = new Random().nextInt();
        this.size = 1;
    }
}

public class Treap {
    TreapNode root;

    private int getSize(TreapNode node) {
        return node == null ? 0 : node.size;
    }

    private void updateSize(TreapNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    private TreapNode rotateRight(TreapNode node) {
        TreapNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateSize(node);
        updateSize(newRoot);
        return newRoot;
    }

    private TreapNode rotateLeft(TreapNode node) {
        TreapNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateSize(node);
        updateSize(newRoot);
        return newRoot;
    }

    public TreapNode insert(TreapNode node, int key) {
        if (node == null) {
            return new TreapNode(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else {
            node.right = insert(node.right, key);
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }
        updateSize(node);
        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public int findKthSmallest(TreapNode node, int k) {
        if (node == null) {
            throw new IllegalArgumentException("K is out of bounds");
        }

        int leftSize = getSize(node.left);
        if (k <= leftSize) {
            return findKthSmallest(node.left, k);
        } else if (k > leftSize + 1) {
            return findKthSmallest(node.right, k - leftSize - 1);
        } else {
            return node.key;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 34, 5, 6, 7, 8, 32, 3, 15};
        Treap treap = new Treap();
        for (int value : arr) {
            treap.insert(value);
        }
        int k = 2; // 查找第二小的元素

        int result = treap.findKthSmallest(treap.root, k);
        System.out.println("The " + k + "-th smallest element is: " + result);
    }
}
