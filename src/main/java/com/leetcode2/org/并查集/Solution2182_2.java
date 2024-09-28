package com.leetcode2.org.并查集;

import java.util.*;
public class Solution2182_2 {


        private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        static class UnionFind {
            int[] parent;
            int[] size;
            int setCount;

            public UnionFind(int n) {
                this.setCount = n;
                this.parent = new int[n];
                this.size = new int[n];
                Arrays.fill(size, 1);
                for (int i = 0; i < n; ++i) {
                    parent[i] = i;
                }
            }

            public int findset(int x) {
                if (parent[x] != x) {
                    parent[x] = findset(parent[x]);
                }
                return parent[x];
            }

            public boolean unite(int x, int y) {
                x = findset(x);
                y = findset(y);
                if (x == y) {
                    return false;
                }
                if (size[x] < size[y]) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                parent[y] = x;
                size[x] += size[y];
                --setCount;
                return true;
            }

            public boolean connected(int x, int y) {
                return findset(x) == findset(y);
            }
        }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size(); // 获取网格的大小
        // 如果起点或终点是小偷，则无法安全到达，返回0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;

        Queue<int[]> queue = new LinkedList<>(); // 用于BFS的队列
        int[][] distances = new int[n][n]; // 存储每个格子到最近小偷的距离
        boolean[][] visited = new boolean[n][n]; // 记录格子是否被访问过

        // 初始化BFS，将所有小偷加入队列，并标记为已访问
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 使用BFS计算每个格子到最近小偷的距离
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1];
                distances[x][y] = distance; // 当前格子到最近小偷的距离
                // 遍历当前格子的四个相邻格子
                for (int[] dir : DIRECTIONS) { // 假设DIRECTIONS是定义好的方向数组，如[[-1,0],[1,0],[0,-1],[0,1]]
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            distance++;
        }




        // 使用并查集（Union-Find）来找到最大安全距离
        // 首先，根据距离构建边，并将边按距离从大到小排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int id = n * i + j; // 将二维坐标转换为一维ID
                if (i > 0) {
                    pq.offer(new int[]{id - n, id, Math.min(distances[i - 1][j], distances[i][j])}); // 上方相邻格子
                }
                if (j > 0) {
                    pq.offer(new int[]{id - 1, id, Math.min(distances[i][j - 1], distances[i][j])}); // 左方相邻格子
                }
            }
        }

        // 初始化并查集
        UnionFind uf = new UnionFind(n * n);
        // 遍历排序后的边，尝试合并集合，并检查是否已经连接起点和终点
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            uf.unite(edge[0], edge[1]); // 合并两个格子所在的集合
            if (uf.connected(0, n * n - 1)) { // 如果起点和终点已经连接
                return edge[2]; // 返回当前边的距离，即最大安全距离
            }
        }

        // 如果没有找到路径，返回0（理论上这种情况不会发生，因为至少可以沿着网格边缘走）
        return 0;
    }
    }