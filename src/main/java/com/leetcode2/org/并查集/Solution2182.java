package com.leetcode2.org.并查集;

import java.util.*;

public class Solution2182 {

    static class UnionFind {
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

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;
        List<int[]> thieves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    thieves.add(new int[]{i, j});
                }
            }
        }
        int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = calcDistance(thieves, i, j);
            }
        }
        List<int[]> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int id = n * i + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.min(distances[i - 1][j], distances[i][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.min(distances[i][j - 1], distances[i][j])});
                }
            }
        }
        edges.sort(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });
        int ans = 0;
        UnionFind uf = new UnionFind(n * n);
        for (int[] edge : edges) {
            int preP = edge[0];
            int postP = edge[1];
            int distance = edge[2];
            uf.unite(preP, postP);
            if (uf.connected(0, n * n - 1)) {
                ans = distance;
                break;
            }
        }
        return ans;
    }

    int calcDistance(List<int[]> thieves, int x, int y) {
        int minDistance = Integer.MAX_VALUE;
        for (int[] thief : thieves) {
            minDistance = Math.min(Math.abs(thief[0] - x) + Math.abs(thief[1] - y), minDistance);
        }
        return minDistance;
    }

    public static void main(String[] args) {
//        int[][] grid = {
//                {0, 0, 0, 1},
//                {0, 0, 0, 0},
//                {0, 0, 0, 0},
//                {1, 0, 0, 0}
//        };
//        int[][] grid = {
//                {0, 0, 1},
//                {0, 0, 0},
//                {0, 0, 0}
//        };
//        int[][] grid = {
//                {0, 0, 0, 1},
//                {0, 0, 0, 0},
//                {0, 0, 0, 0},
//                {1, 0, 0, 0}
//        };
        int[][] grid = {
                {0, 1, 1},
                {0, 0, 0},
                {0, 0, 0}
        };

        Solution2182 solution2182 = new Solution2182();
        List<List<Integer>> grildL = Arrays.stream(grid).map(v -> Arrays.stream(v).boxed().toList()).toList();
        System.out.println(solution2182.maximumSafenessFactor(grildL));
    }

}
