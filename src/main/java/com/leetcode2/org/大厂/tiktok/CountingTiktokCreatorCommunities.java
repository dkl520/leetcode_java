package com.leetcode2.org.大厂.tiktok;

public class CountingTiktokCreatorCommunities {
    static class UnionFind {
        int n;
        int[] parent;
        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        int query(int num) {
            if (parent[num] != num) {
                parent[num] = query(parent[num]);
            }
            return parent[num];
        }
        void union(int x, int y) {
            parent[query(x)] = query(y);
        }

    }

    int get(int[][] communities) {
        int n = communities.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (communities[i][j] == 1) {
                    if (uf.query(i) != uf.query(j)) {
                        uf.union(i, j);
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (uf.parent[i] == i) {
                result++;
            }
        }

        return result;

    }


    public static void main(String[] args) {
        int[][] communities = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        CountingTiktokCreatorCommunities c = new CountingTiktokCreatorCommunities();
        c.get(communities);

    }


}
