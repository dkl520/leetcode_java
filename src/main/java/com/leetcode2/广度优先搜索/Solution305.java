package com.leetcode2.广度优先搜索;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution305 {
    int calcPos(int row, int col, int m, int n) {
        return row * n + col;
    }

    static int[][] DIRS = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    static class UnionFind {
        int n;
        int[] parent;
        int count;

        UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = 0;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                count--;
            }
        }


    }


    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n);
        int[][] graph = new int[m][n];
        int[] answer = new int[positions.length];
        Set<Integer> visited = new HashSet<>();
        int index = -1;
        for (int[] pos : positions) {
            graph[pos[0]][pos[1]] = 1;

            index++;
            int curPIndex = calcPos(pos[0], pos[1], m, n);
            if (visited.contains(curPIndex)) {
                answer[index] = uf.count;
                continue;
            }
            visited.add(curPIndex);
            uf.count++;
            for (int[] dir : DIRS) {
                int x = pos[0] + dir[0];
                int y = pos[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (graph[x][y] == 1) {
                        int nextPIndex = calcPos(x, y, m, n);
                        uf.union(curPIndex, nextPIndex);
                    }
                }
            }
            answer[index] = uf.count;
        }
        return Arrays.stream(answer).boxed().toList();
    }

}
