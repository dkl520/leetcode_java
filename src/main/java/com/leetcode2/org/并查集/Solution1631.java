package com.leetcode2.org.并查集;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 解决方案类，用于解决最小努力路径问题
public class Solution1631 {
    // 最小努力路径问题的主要方法
    public int minimumEffortPath(int[][] heights) {
        // 获取地图的行数和列数
        int m = heights.length;
        int n = heights[0].length;
        // 用于存储所有边的列表，每条边包含两个顶点的索引和它们之间的高度差（即努力值）
        List<int[]> edges = new ArrayList<int[]>();

        // 遍历地图，构建边列表
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // 计算当前位置的唯一标识符
                int id = i * n + j;
                // 如果不是第一行，则添加与上一行的连接
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                // 如果不是第一列，则添加与左一列的连接
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }

        // 按努力值（即高度差）对边进行排序
        edges.sort(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });

        System.out.println(edges);
        // 初始化并查集
        UnionFind uf = new UnionFind(m * n);
        int ans = 0; // 最小努力值
        // 遍历排序后的边，尝试连接顶点，直到起点和终点连通
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            uf.unite(x, y); // 尝试连接两个顶点
            if (uf.connected(0, m * n - 1)) { // 如果起点和终点连通
                ans = v; // 更新最小努力值
                break; // 找到最小努力值后退出循环
            }
        }

        return ans; // 返回最小努力值
    }

    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 3},
                {3, 8, 4},
                {5, 3, 5}
        };
    System.out.println(new Solution1631().minimumEffortPath(heights));

    }
    // 并查集模板
    // 并查集模板类
    // 并查集模板类
    class UnionFind {
        // 父节点数组，用于记录每个节点的根节点
        // 在并查集中，每个节点都通过其父节点指针连接到其所属的集合的根节点
        int[] parent;
        // 每个连通分量（集合）的大小
        // 这有助于在合并集合时，选择较小的集合连接到较大的集合上，从而保持树的平衡
        int[] size;
        // 连通分量的总数（初始化时，每个节点都是独立的连通分量）
        int n;
        // 当前连通分量（集合）的数目
        // 随着union操作的进行，这个值会减少
        int setCount;
        // 构造函数，初始化并查集
        // n 表示并查集中节点的总数
        public UnionFind(int n) {
            this.n = n;
            this.setCount = n; // 初始化时，每个节点都是独立的连通分量
            this.parent = new int[n];
            this.size = new int[n];
            // 初始化每个连通分量的大小为1，因为每个节点都是独立的
            Arrays.fill(size, 1);
            // 初始化每个节点的父节点为其自身，表示每个节点都是独立的连通分量
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }
        // 查找节点x的根节点
        // 使用路径压缩技术，将查找路径上的所有节点直接连接到根节点，以加快后续查找速度
        public int findset(int x) {
            // 如果节点x的父节点已经是x自身，说明x是根节点
            // 否则，递归地向上查找，直到找到根节点，并将路径上的所有节点的父节点指向根节点
            return parent[x] == x ? x : (parent[x] = findset(parent[x]));
        }

        // 合并两个节点x和y所在的集合
        // 如果x和y已经在同一个集合中，则合并操作无效，返回false
        // 否则，将较小的集合合并到较大的集合中，并更新集合的大小和连通分量的数目
        public boolean unite(int x, int y) {
            x = findset(x); // 查找x的根节点
            y = findset(y); // 查找y的根节点
            if (x == y) { // 如果x和y已经在同一个集合中
                return false;
            }
            // 为了保持树的平衡，将较小的树连接到较大的树上
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x; // 将y的根节点指向x，即将y所在的集合合并到x所在的集合中
            size[x] += size[y]; // 更新合并后集合的大小
            --setCount; // 连通分量的数目减一
            return true;
        }

        // 检查两个节点x和y是否在同一集合中
        public boolean connected(int x, int y) {
            x = findset(x); // 查找x的根节点
            y = findset(y); // 查找y的根节点
            // 如果x和y的根节点相同，则它们在同一集合中
            return x == y;
        }
    }
}