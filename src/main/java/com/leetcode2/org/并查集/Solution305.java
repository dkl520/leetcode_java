package com.leetcode2.org.并查集;

import java.util.ArrayList;
import java.util.List;

public class Solution305 {

    static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    static class UnionFind {
        int size;
        int[] parent;
        int count;

        public UnionFind(int size) {
            this.size = size;
            parent = new int[size];
            count = 0;
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        void union(int x, int y) {
            parent[query(x)] = query(y);
            count--;
        }

        boolean isConnect(int x, int y) {
            return query(x) == query(y);
        }

        int query(int x) {
            if (parent[x] != x) {
                parent[x] = query(parent[x]);
            }
            return parent[x];
        }

        public int getCount() {
            return count;
        }
    }


    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        boolean[][] visited = new boolean[m][n];

        UnionFind uf = new UnionFind(m * n);

        List<Integer> res = new ArrayList<>();
        for (int[] position : positions) {

            if (visited[position[0]][position[1]]) {
                res.add(uf.count);
                continue;
            }
            visited[position[0]][position[1]] = true;
            int index = position[0] * n + position[1];
            uf.count++;
            for (int[] direction : DIRECTIONS) {
                int newx = position[0] + direction[0];
                int newy = position[1] + direction[1];
                int newIndex = newx * n + newy;
                if (newx >= 0 && newx < m && newy >= 0 && newy < n && visited[newx][newy] && !uf.isConnect(newIndex, index)) {
                    uf.union(newIndex, index);
                }
            }
            res.add(uf.getCount());
        }
        return res;
    }

    public static void main(String[] args) {
        int m = 8;
        int n = 4;
        int[][] positions = {
                {0, 0},
                {7, 1},
                {6, 1},
                {3, 3},
                {4, 1}
        };
        System.out.println(new Solution305().numIslands2(m, n, positions));

    }
}
