package com.leetcode2.org.数据结构;

public class UnionFind {
    private int[] parent; // 记录每个元素的父节点
    private int[] rank;   // 记录树的深度（秩）

    // 构造函数，初始化并查集
    public UnionFind(int size) {
        parent = new int[size]; // 初始化父节点数组
        rank = new int[size];   // 初始化秩数组
        for (int i = 0; i < size; i++) {
            parent[i] = i; // 每个节点的初始父节点为其自身
            rank[i] = 0;   // 初始秩为0
        }
    }

    // 查找元素的根节点，带路径压缩
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 递归查找并压缩路径
        }
        return parent[x];
    }

    // 合并两个元素的集合
    public void union(int x, int y) {
        int rootX = find(x); // 找到x的根节点
        int rootY = find(y); // 找到y的根节点

        if (rootX != rootY) {
            // 按秩合并
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX; // 将根Y的父节点设为根X
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY; // 将根X的父节点设为根Y
            } else {
                parent[rootY] = rootX; // 将根Y的父节点设为根X
                rank[rootX]++; // 根X的秩增加
            }
        }
    }

    // 判断两个元素是否在同一集合中
    public boolean connected(int x, int y) {
        return find(x) == find(y); // 判断根节点是否相同
    }

    // 测试用例
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10); // 创建一个大小为10的并查集

        uf.union(1, 2); // 合并1和2
        uf.union(2, 3); // 合并2和3
        uf.union(4, 5); // 合并4和5

        System.out.println(uf.connected(1, 3)); // 输出: true (1和3在同一集合)
        System.out.println(uf.connected(1, 4)); // 输出: false (1和4不在同一集合)

        uf.union(3, 4); // 合并3和4
        System.out.println(uf.connected(1, 4)); // 输出: true (1和4现在在同一集合)
    }
}
