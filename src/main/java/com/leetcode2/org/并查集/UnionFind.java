package com.leetcode2.org.并查集;

// 并查集实现，主要用于处理不相交集合的合并和查找操作
public class UnionFind {
    // parent数组用于存储每个元素的父节点
    private int[] parent;
    // rank数组用于存储每个集合的深度
    private int[] rank;

    // 初始化并查集，n表示元素的数量
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        // 初始化时，每个元素的父节点都是它自己，集合的深度为1
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找元素x的根节点，并进行路径压缩
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 路径压缩
        }
        return parent[x];
    }



    // 合并元素x和y所在的集合
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // 根据rank合并，rank小的集合挂到rank大的集合下
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // 判断元素x和y是否属于同一个集合
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // 测试用例
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);

        System.out.println(uf.isConnected(1, 3)); // 输出: true
        System.out.println(uf.isConnected(1, 4)); // 输出: false

        uf.union(3, 4);
        System.out.println(uf.isConnected(1, 5)); // 输出: true
    }
}
