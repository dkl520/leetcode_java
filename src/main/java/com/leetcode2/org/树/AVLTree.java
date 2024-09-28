package com.leetcode2.org.树;

public class AVLTree {
    private AVLNode root;

    // 获取节点的高度
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // 获取节点的平衡因子
    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 右旋转操作
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋转操作
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入操作
    public void insert(int key) {
        root = insert(root, key);
    }

    private AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // 左左情况
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // 右右情况
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // 左右情况
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左情况
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 查找操作
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(AVLNode node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.key) {
            return true;
        }

        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // 删除操作
    public void delete(int key) {
        root = delete(root, key);
    }

    private AVLNode delete(AVLNode root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                AVLNode temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        // 左左情况
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // 左右情况
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // 右右情况
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // 右左情况
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 中序遍历
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(AVLNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

//    public static void main(String[] args) {
//        AVLTree tree = new AVLTree();
//        tree.insert(10);
//        tree.insert(20);
//        tree.insert(30);
//        tree.insert(40);
//        tree.insert(50);
//        tree.insert(25);
//
//        System.out.println("Inorder traversal of the AVL tree is:");
//        tree.inorderTraversal();
//
//        System.out.println("\n\nAfter deletion of 20:");
//        tree.delete(20);
//        tree.inorderTraversal();
//    }
}
