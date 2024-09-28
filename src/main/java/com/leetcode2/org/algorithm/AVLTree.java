package com.leetcode2.org.algorithm; // 定义了这个类所在的包，即它属于org.algorithm。
// 二叉平衡树
public class AVLTree { // 定义了一个公开的类，名为AVLTree。
    private Node root; // 定义了一个私有的Node类型的变量root，表示树的根节点。

    // 以下三个方法用于获取节点的高度，计算节点的平衡因子以及更新节点的高度。
    private int getHeight(Node node) {
        if (node == null) { // 如果节点为空（即树为空），则返回0。
            return 0;
        }
        return node.height; // 否则，返回节点的高度。
    }

    private int getBalanceFactor(Node node) { // 获取节点的平衡因子。
        if (node == null) { // 如果节点为空（即树为空），则返回0。
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right); // 否则，返回左子树高度减去右子树高度。
    }

    private void updateHeight(Node node) { // 更新节点的高度。
        if (node == null) { // 如果节点为空（即树为空），则不进行任何操作。
            return;
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1; // 否则，节点的高度设为左子树和右子树中较高的那个的高度加1。
    }

    // 以下两个方法用于进行左旋和右旋操作，以恢复树的平衡。
    private Node rotateLeft(Node node) { // 左旋操作。
        Node newRoot = node.right; // 新的根节点是原节点的右子节点。
        node.right = newRoot.left; // 原节点的右子节点变为新根的左子节点。
        newRoot.left = node; // 原节点成为新根的左子节点。
        updateHeight(node); // 更新原节点的高度。
        updateHeight(newRoot); // 更新新根的高度。
        return newRoot; // 返回新的根节点。
    }

    private Node rotateRight(Node node) { // 右旋操作。
        Node newRoot = node.left; // 新的根节点是原节点的左子节点。
        node.left = newRoot.right; // 原节点的左子节点变为新根的右子节点。
        newRoot.right = node; // 原节点成为新根的右子节点。
        updateHeight(node); // 更新原节点的高度。
        updateHeight(newRoot); // 更新新根的高度。
        return newRoot; // 返回新的根节点。
    }

    public void insert(int value) { // 插入一个整数值到树中。
        root = insertNode(root, value); // 使用私有方法insertNode插入值，并将返回的新的根节点赋值给root。
    }

    // 这个方法用于在树中插入一个新的值，并返回新的根节点。它首先找到插入位置，然后更新高度并平衡树。
    private Node insertNode(Node node, int value) {
        if (node == null) { // 如果节点为空（即树为空），则创建一个新的节点作为根节点。
            return new Node(value);
        }
        if (value < node.value) { // 如果插入的值小于当前节点的值，则在左子树中插入。
            node.left = insertNode(node.left, value);
        } else { // 否则，在右子树中插入。
            node.right = insertNode(node.right, value);
        }
        updateHeight(node); // 更新当前节点的高度。
        return balance(node); // 返回平衡后的节点（可能是当前节点或其他节点）。这个调用可能会触发左旋或右旋操作以恢复树的平衡性。
    }

    // 这个方法用于平衡树，它根据节点的平衡因子来决定是否需要进行左旋或右旋操作。这个方法可能返回一个新的根节点。
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node); // 获取当前节点的平衡因子。
        if (balanceFactor > 1) { // 如果平衡因子大于1，说明左子树太重，需要进行右旋。
            if (getBalanceFactor(node.left) < 0) { // 如果左子树平衡因子小于0，说明左子树右旋过，需要先进行左旋。
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node); // 对当前节点进行右旋，并返回新的根节点。
        }
        if (balanceFactor < -1) { // 如果平衡因子小于-1，说明右子树太重，需要进行左旋。
            if (getBalanceFactor(node.right) > 0) { // 如果右子树平衡因子大于0，说明右子树左旋过，需要先进行右旋。
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node); // 对当前节点进行左旋，并返回新的根节点。
        }
        return node; // 如果平衡因子在-1到1之间，说明树是平衡的，直接返回当前节点。
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(60);
        System.out.println(avlTree);
    }
}
