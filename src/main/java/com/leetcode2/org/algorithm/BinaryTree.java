// 声明一个名为algorith的包
package com.leetcode2.org.algorithm;
// 导入java.util.ArrayList类，用于创建动态数组
import java.util.LinkedList;
import java.util.*;


// 导入java.util.List类，这是Java的集合框架的一部分，提供了一种结构来存储数据
// 声明一个名为BinaryTree的公共类
public class BinaryTree {
    // 声明一个私有的TreeNode类型的变量root，表示树的根节点
    private TreeNode root;

    // 定义一个公共的构造函数，创建一个新的BinaryTree对象时，将root初始化为null
    public BinaryTree() {
        root = null;
    }

    // 声明一个私有的List<Integer>类型的变量inorderList，用于存储中序遍历的结果
    private List<Integer> inorderList;

    //    private TreeNode preorderList;
    // 定义一个公共的插入节点的方法，接受一个int类型的参数val
    public void insert(int val) {
        // 调用私有方法insertNode，将val插入到树中，并将返回的节点赋值给root
        root = insertNode(root, val);
    }

    // 定义一个私有的插入节点的方法，接受两个参数：一个是TreeNode类型的root，另一个是int类型的val
    private TreeNode insertNode(TreeNode root, int val) {
        // 如果root为null（即树为空），则创建一个新的TreeNode对象，并将val作为节点的值，然后返回新创建的节点
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        // 如果val小于root的值，则在左子树中插入val
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {  // 如果val大于root的值，则在右子树中插入val
            root.right = insertNode(root.right, val);
        } else { // 如果val等于root的值，则不插入新的节点，直接返回root
            return root;
        }
        // 返回插入新节点后的根节点
        return root;
    }

    // 定义一个公共的中序遍历的方法，将遍历的结果存储在inorderList中
//    中序遍历
    public void inorderTraversal() {
        // 创建一个新的ArrayList对象inorderList（列表）来存储中序遍历的结果（整数）
        inorderList = new ArrayList<Integer>();
        // 调用私有方法inOrder来进行中序遍历，并将结果存储在inorderList中
        inOrder(root, inorderList);
    }
    // 定义一个私有方法inOrder，接受两个参数：一个是二叉树的根节点root，另一个是整数类型的List列表inorderList
    private void inOrder(TreeNode root, List<Integer> inorderList) {
        // 如果当前节点root为null，即当前子树为空，则直接返回，结束当前递归
        if (root == null) {
            return;
        }
        // 递归遍历root的左子树，并将遍历的结果添加到inorderList中
        inOrder(root.left, inorderList);
        // 将当前节点root的值添加到inorderList中
        inorderList.add(root.val);
        // 递归遍历root的右子树，并将遍历的结果添加到inorderList中
        inOrder(root.right, inorderList);
    }
    // 定义一个名为 preorderTaversal 的方法，返回类型为 TreeNode
    public TreeNode preorderTaversal() {
        // 创建一个新的 TreeNode 对象，其值为0，作为 preorderList 的初始节点
        TreeNode preorderList = new TreeNode(0);
        // 调用递归函数 preOrder 进行前序遍历，传入 root 节点和 preorderList 节点
        preOrder(root, preorderList);
        // 返回 preorderList 节点的 right 属性，即整个前序遍历的结果
        return preorderList.right;
    }

    // 定义一个名为 preOrder 的方法，输入参数为 node（TreeNode类型）和 preorderList（TreeNode类型），返回类型为 TreeNode
    public TreeNode preOrder(TreeNode node, TreeNode preorderList) {
        // 如果输入的 node 为 null，即当前节点为空，则返回 preorderList，即前序遍历的结果
        if (node == null) {
            return preorderList;
        }
        // 在 preorderList 的 right 属性处创建一个新的 TreeNode 对象，其值为 node.val（当前节点的值）
        preorderList.right = new TreeNode(node.val);
        // 将 preorderList 更新为其 right 属性，即新创建的节点，以便在下一次迭代中继续添加新的节点
        preorderList = preorderList.right;
        // 递归调用 preOrder 方法，传入 node.left（当前节点的左子节点）和 preorderList（前序遍历的结果）
        preorderList = preOrder(node.left, preorderList);
        // 递归调用 preOrder 方法，传入 node.right（当前节点的右子节点）和 preorderList（前序遍历的结果）
        preorderList = preOrder(node.right, preorderList);
        // 返回更新后的 preorderList，即前序遍历的结果
        return preorderList;
    }

    //反转二叉树
// 定义一个名为invertTree的函数，它接受一个参数，即二叉树的根节点root
//    这段代码是用于翻转二叉树的。它使用广度优先搜索（BFS）的方式，逐层遍历树的节点，并在每一层进行翻转。
//    具体来说，对于每个节点，它先将其右子节点加入到队列中，然后再将其左子节点加入到队列中。这样做可以保证在处理完当前层之后，
//    下一层会先处理右子节点，然后再处理左子节点。因为在二叉树中，左子节点的值小于其父节点，而右子节点的值大于其父节点，
//    所以这种方式可以保证在每一层都进行正确的翻转。
//    在处理完当前层的所有节点后，它将当前层的每个节点的左右子节点进行交换。这样，原本在左边的子节点就变到了右边，
//    原本在右边的子节点就变到了左边，从而实现了翻转。
    public TreeNode invertTree(TreeNode root) {
        // 如果根节点为空，返回null，这是递归的基本情况
        if (root == null) {
            return null;
        }
        // 创建一个队列list，用于层序遍历二叉树，这里使用的是LinkedList实现队列
        Queue<TreeNode> list = new java.util.LinkedList<>();
        // 将根节点加入队列
        list.add(root);
        // 当队列不为空时，持续进行循环，这里是层序遍历的循环
        while (!list.isEmpty()) {
            // 从队列中取出一个节点，并赋值给currentNode
            TreeNode currentNode = list.poll();
            // 如果currentNode的左子节点不为空
            if (currentNode.left != null) {
                // 将左子节点加入队列，用于后续遍历
                list.add(currentNode.left);
            }
            // 如果currentNode的右子节点不为空
            if (currentNode.right != null) {
                // 将右子节点加入队列，用于后续遍历
                list.add(currentNode.right);
            }
            // 声明一个名为temp的变量，它是用于交换左右子节点的临时变量
            // 将currentNode的左子节点赋值给temp
            TreeNode temp = currentNode.left;
            // 将currentNode的右子节点赋值给左子节点
            currentNode.left = currentNode.right;
            // 将temp（原本是左子节点）赋值给currentNode的右子节点
            currentNode.right = temp;
        }
        // 返回反转后的二叉树的根节点
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        // 如果根节点为null或者整个树只有一个节点，则是对称的
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left); // 将左子树的根节点加入队列
        queue.offer(root.right); // 将右子树的根节点加入队列

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll(); // 取出队列中左子树的节点
            TreeNode right = queue.poll(); // 取出队列中右子树的节点
            if (left == null && right == null) {
                continue; // 左右节点都为null，继续下一轮循环
            }
            // 左右节点不同时为null，或者值不相等，则不对称
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 将左子树的左孩子，右子树的右孩子加入队列
            queue.offer(left.left);
            queue.offer(right.right);
            // 将左子树的右孩子，右子树的左孩子加入队列
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true; // 遍历完成，二叉树是对称的
    }

    // 定义一个公共方法isSymmetric2，输入参数是二叉树的根节点，返回值是布尔类型，代表二叉树是否对称
    public boolean isSymmetricDFS(TreeNode root) {
        // 如果输入的根节点是null，直接返回true，代表空树是对称的
        if (root == null) {
            return true;
        }
        // 调用递归函数dfsisSymmetric来检查左右子树是否对称，如果对称返回true，否则返回false
        return dfsisSymmetric(root.left, root.right);
    }

    // 定义一个递归函数dfsisSymmetric，输入参数是左子树和右子树的根节点，返回值是布尔类型
    boolean dfsisSymmetric(TreeNode left, TreeNode right) {
        // 如果左子树和右子树都为null，那么返回true，因为两个null节点是对称的
        if (left == null && right == null) {
            return true;
        }
        // 如果左子树为null而右子树不为null，或者左子树不为null而右子树为null，或者左子树的值不等于右子树的值，那么返回false
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        // 如果左子树和右子树都不为null且值相等，那么递归地检查左子树的左子树和右子树的右子树是否对称，以及左子树的右子树和右子树的左子树是否对称
        // 如果这两个递归调用都返回true，那么整个函数就返回true，否则返回false
        return dfsisSymmetric(left.left, right.right) && dfsisSymmetric(left.right, right.left);
    }

    public int maxDepth(TreeNode root) { // 定义一个公开的函数，名为maxDepth，输入参数是一个二叉树的根节点，返回值是一个整数。
        if (root == null) { // 如果输入的根节点是空的，即这是一个空树。
            return 0; // 返回0，表示这个空树的深度为0。
        }
        int leftDepth = maxDepth(root.left); // 递归调用maxDepth函数，计算左子树的深度，将结果赋值给变量leftDepth。
        int rightDepth = maxDepth(root.right); // 递归调用maxDepth函数，计算右子树的深度，将结果赋值给变量rightDepth。
        return Math.max(leftDepth, rightDepth) + 1; // 返回左子树和右子树中较大深度的值加1，这个1表示根节点的存在。
    }



    // 定义一个返回树按层序遍历结果的函数，输入参数为树的根节点。
//    这段代码是用于实现二叉树的层序遍历（Level Order Traversal）的。层序遍历是一种遍历二叉树的方法，它会按照树的层次从上到下、从左到右进行遍历。
    //最后得到 每层的一个数组组成的大数组
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 创建一个空的列表，用于存储每一层的节点值。
        List<List<Integer>> listAll = new ArrayList<>();
        // 如果根节点为空，直接返回空列表。
        if (root == null) {
            return listAll;
        }
        // 创建一个队列，用于层序遍历中的FIFO（先进先出）操作。
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列。
        queue.offer(root);
        // 当队列不为空时，进行循环。
        while (!queue.isEmpty()) {
            // 获取当前层的节点数。
            int levelSize = queue.size();
            // 创建一个新的列表，用于存储当前层的节点值。
            List<Integer> levelList = new ArrayList<>();
            // 对当前层的每个节点进行循环。
            for (int i = 0; i < levelSize; i++) {
                // 从队列中取出一个节点。
                TreeNode currentNode = queue.poll();
                // 将当前节点的值添加到当前层的列表中。
                levelList.add(currentNode.val);
                // 如果当前节点有左子节点，则将左子节点加入队列。
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                // 如果当前节点有右子节点，则将右子节点加入队列。
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            // 将当前层的列表添加到总的列表中。
            listAll.add(levelList);
        }
        // 返回总的列表，包含了按层序遍历的结果。
        return listAll;
    }

    // 我写的。
// 定义一个返回树按层序遍历结果的函数，输入参数为树的根节点。
    public List<List<Integer>> levelOrder2(TreeNode root) {
        // 创建一个队列，用于存储待遍历的节点
        Queue<TreeNode> listRoot = new LinkedList<TreeNode>();
        // 创建一个列表，用于存储当前节点的值
        List<Integer> lRoot = new ArrayList<>();
        // 创建一个列表，用于存储每一层的节点值的列表
        List<List<Integer>> listAll = new ArrayList<>();
        // 如果根节点为空，直接返回空列表
        if (root == null) {
            return listAll;
        }
        // 将根节点加入队列，并添加到当前节点的值列表中
        listRoot.add(root);
        lRoot.add(root.val);
        // 将当前节点的值列表添加到所有节点的值列表中
        listAll.add(lRoot);
        // 调用bfsList方法，开始广度优先遍历
        bfsList(listRoot, listAll);
        // 返回所有节点的值列表的列表
        return listAll;
    }

    // 定义广度优先遍历的方法
    public void bfsList(Queue<TreeNode> listRoot, List<List<Integer>> listAll) {
        // 如果队列为空，结束遍历
        if (listRoot.isEmpty()) {
            return;
        }
        // 使用迭代器遍历队列中的节点
        Iterator<TreeNode> treeIterator = listRoot.iterator();
        // 创建一个列表，用于存储当前层的节点值
        List<Integer> lNode = new ArrayList<>();
        // 创建一个队列，用于存储下一层的待遍历节点
        Queue<TreeNode> listNext = new LinkedList<TreeNode>();
        while (treeIterator.hasNext()) {
            // 获取当前节点
            TreeNode currentNode = treeIterator.next();
            // 如果当前节点有左子节点，则将左子节点加入到待遍历队列和当前节点的值列表中
            if (currentNode.left != null) {
                listNext.offer(currentNode.left);
                lNode.add(currentNode.left.val);
            }
            // 如果当前节点有右子节点，则将右子节点加入到待遍历队列和当前节点的值列表中
            if (currentNode.right != null) {
                listNext.offer(currentNode.right);
                lNode.add(currentNode.right.val);
            }
        }
        // 如果当前层的节点值列表不为空，则将其添加到所有节点的值列表中
        if (!lNode.isEmpty()) {
            listAll.add(lNode);
        }
        // 递归调用bfsList方法，继续遍历下一层
        bfsList(listNext, listAll);
    }

    int ans; // 定义一个全局变量 ans，用来存储二叉树的直径

    //    这段代码是用来计算二叉树直径的。二叉树的直径是指所有节点之间最长路径的长度，也就是任意两个节点之间最长路径的边数。
//    这段代码的逻辑是这样的：
//    ans 初始化为1，表示当前二叉树的直径为1（只有一个节点）。
//    dP 是一个递归函数，用来计算以当前节点为根的子树的直径。
//    如果当前节点为空（即已经遍历到了叶节点），则返回0，表示这个子树的直径为0。
//    否则，递归计算左子树和右子树的直径，并取两者之和加1（当前节点）与 ans 的最大值，更新 ans。
//    最后返回左子树和右子树直径的最大值加1（当前节点）。
//    在主函数 diameterOfBinaryTree 中，返回 ans 减1，即为二叉树的直径。因为在计算过程中，我们计算的是所有节点之间的最长路径，
//    但是这个最长路径会多算一次当前节点到其子节点的边（因为在计算直径时，当前节点到其子节点的边会被计算两次），所以要减1。
//    整个代码的逻辑是通过动态规划的方式实现的，是一个递归的过程
    public int diameterOfBinaryTree(TreeNode root) { //定义一个公共函数 diameterOfBinaryTree，输入参数为二叉树的根节点 root，返回值为整型
        ans = 1; // 初始化 ans 为1，表示当前二叉树的直径为1（只有一个节点）
        dP(root); // 调用 dP 函数，计算二叉树的直径
        return ans - 1; // 返回二叉树的直径，注意要减1，因为在计算过程中会多算一次当前节点到其子节点的边
    }

    public int dP(TreeNode root) { //定义一个公共函数 dP，输入参数为二叉树的根节点 root，返回值为整型
        if (root == null) { // 如果当前节点为空（即已经遍历到了叶节点）
            return 0; // 则返回0，表示这个子树的直径为0
        }
        int maxLeft = dP(root.left); // 递归计算左子树的直径
        int maxRight = dP(root.right); // 递归计算右子树的直径
        ans = Math.max(maxLeft + 1 + maxRight, ans); // 取两者之和加1（当前节点）与 ans 的最大值，更新 ans
        return Math.max(maxLeft, maxRight) + 1; // 返回左子树和右子树直径的最大值加1（当前节点）
    }

    int maxNum = Integer.MIN_VALUE;
//最大深度  二叉树
    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        getMaxPathSum(root);
        return maxNum;
    }
//    这段代码是将一个已排序的数组转换为一个高度平衡的二叉搜索树(BST)。
    public TreeNode sortedArrayToBST(int[] nums) {
        // 调用构造二叉树的辅助函数，起始索引设为0，结束索引设为nums数组长度减1
        return constructBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructBinaryTree(int[] nums, int left, int right) {
        // 如果起始索引大于结束索引，说明当前子树为空，返回null
        if (left > right) {
            return null;
        }
        // 计算中间索引，该索引对应的元素应当作为当前子树的根节点
        int mid = (left + right) / 2;
        // 创建一个新的TreeNode节点，其值为nums数组中中间索引对应的元素
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构造左子树，左子树的起始索引和结束索引分别为当前子树的起始索引和中间索引减1
        root.left = constructBinaryTree(nums, left, mid - 1);
        // 递归构造右子树，右子树的起始索引和结束索引分别为中间索引加1和当前子树的结束索引
        root.right = constructBinaryTree(nums, mid + 1, right);
        // 返回构造好的当前子树的根节点
        return root;
    }

    public int getMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = Math.max(0, getMaxPathSum(root.left));
        int maxRight = Math.max(0, getMaxPathSum(root.right));
        maxNum = Math.max(maxNum, maxLeft + maxRight + root.val);
        return Math.max(maxLeft, maxRight) + root.val;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(0);
        tree.insert(-10);
        tree.insert(1);
        tree.insert(-11);
        tree.insert(-9);
        tree.insert(2);
        tree.insert(3);
//        tree.insert(4);
//        tree.insert(3);
//        tree.inorderTraversal();
//        Boolean isSymmetric = tree.isSymmetric(tree.root);
////         Boolean isSymmetric2=  tree.isSymmetric2(tree.root);
//        int max = tree.maxDepth(tree.root);
//        System.out.println(max + "maxDepth");
//        System.out.println(isSymmetric + " 是否对称");
//        tree.levelOrder(tree.root);
//        int[] arr = new int[]{-10, -3, 0, 5, 9};
//        int max = tree.maxPathSum(tree.root);
//        tree.sortedArrayToBST(arr);
//        System.out.println(max + "maxxxxxxxxxxxx");
//        System.out.println(isSymmetric2+" 是否对称");

        tree.preorderTaversal();
    }
}
