package com.leetcode2.org.离线查询算法;

import java.util.*;

public class OfflineQueryAlgorithms {

    // 1. 莫氏算法 (Mo's Algorithm)
    static class MoAlgorithm {
        static class Query {
            int left, right, index;
            Query(int l, int r, int i) {
                left = l;
                right = r;
                index = i;
            }
        }

        public static int[] solveRangeQueries(int[] arr, Query[] queries) {
            int n = arr.length;
            int blockSize = (int) Math.sqrt(n);
            int[] result = new int[queries.length];

            // 按照块的大小对查询排序
            Arrays.sort(queries, (a, b) -> {
                int blockA = a.left / blockSize;
                int blockB = b.left / blockSize;
                if (blockA != blockB)
                    return blockA - blockB;
                return a.right - b.right;
            });

            // 处理查询
            int currentL = 0, currentR = -1;
            int currentAnswer = 0;

            for (Query q : queries) {
                // 移动左右指针并更新答案
                while (currentL > q.left) {
                    currentL--;
                    currentAnswer += arr[currentL];
                }
                while (currentR < q.right) {
                    currentR++;
                    currentAnswer += arr[currentR];
                }
                while (currentL < q.left) {
                    currentAnswer -= arr[currentL];
                    currentL++;
                }
                while (currentR > q.right) {
                    currentAnswer -= arr[currentR];
                    currentR--;
                }

                result[q.index] = currentAnswer;
            }

            return result;
        }
    }

    // 2. 差分数组算法
    static class DifferenceArray {
        private int[] diff;

        public DifferenceArray(int[] arr) {
            diff = new int[arr.length + 1];
            diff[0] = arr[0];
            for (int i = 1; i < arr.length; i++) {
                diff[i] = arr[i] - arr[i-1];
            }
        }

        // 区间更新
        public void update(int left, int right, int value) {
            diff[left] += value;
            if (right + 1 < diff.length) {
                diff[right + 1] -= value;
            }
        }

        // 获取结果数组
        public int[] getResult() {
            int[] result = new int[diff.length - 1];
            result[0] = diff[0];
            for (int i = 1; i < result.length; i++) {
                result[i] = result[i-1] + diff[i];
            }
            return result;
        }
    }

    // 3. 分块处理算法
    static class BlockProcessing {
        private int[] arr;
        private int[] blocks;
        private int blockSize;
        private int numBlocks;

        public BlockProcessing(int[] array) {
            arr = array;
            blockSize = (int) Math.sqrt(arr.length);
            numBlocks = (arr.length + blockSize - 1) / blockSize;
            blocks = new int[numBlocks];

            // 预处理块的和
            for (int i = 0; i < arr.length; i++) {
                blocks[i / blockSize] += arr[i];
            }
        }

        // 区间查询
        public int query(int left, int right) {
            int sum = 0;
            int startBlock = left / blockSize;
            int endBlock = right / blockSize;

            if (startBlock == endBlock) {
                // 同一块内
                for (int i = left; i <= right; i++) {
                    sum += arr[i];
                }
            } else {
                // 不完整的第一块
                for (int i = left; i < (startBlock + 1) * blockSize; i++) {
                    sum += arr[i];
                }

                // 完整的中间块
                for (int i = startBlock + 1; i < endBlock; i++) {
                    sum += blocks[i];
                }

                // 不完整的最后一块
                for (int i = endBlock * blockSize; i <= right; i++) {
                    sum += arr[i];
                }
            }

            return sum;
        }
    }

    // 4. 离线树状数组
    static class BinaryIndexedTree {
        private int[] tree;

        public BinaryIndexedTree(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int value) {
            for (; index < tree.length; index += index & (-index)) {
                tree[index] += value;
            }
        }

        public int query(int index) {
            int sum = 0;
            for (; index > 0; index -= index & (-index)) {
                sum += tree[index];
            }
            return sum;
        }

        // 离线处理区间查询
        public static int[] processQueries(int[] arr, int[][] queries) {
            int n = arr.length;
            BinaryIndexedTree bit = new BinaryIndexedTree(n);
            int[] result = new int[queries.length];

            // 构建树状数组
            for (int i = 0; i < n; i++) {
                bit.update(i + 1, arr[i]);
            }

            // 处理查询
            for (int i = 0; i < queries.length; i++) {
                int left = queries[i][0], right = queries[i][1];
                result[i] = bit.query(right + 1) - bit.query(left);
            }

            return result;
        }
    }

    // 5. 离线并查集
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    // 示例用法
    public static void main(String[] args) {
        // 1. 莫氏算法示例
        int[] arr = {1, 2, 3, 4, 5};
        MoAlgorithm.Query[] queries = {
            new MoAlgorithm.Query(0, 2, 0),
            new MoAlgorithm.Query(1, 3, 1),
            new MoAlgorithm.Query(2, 4, 2)
        };
        int[] moResult = MoAlgorithm.solveRangeQueries(arr, queries);
        System.out.println("Mo's Algorithm results: " + Arrays.toString(moResult));

        // 2. 差分数组示例
        DifferenceArray diff = new DifferenceArray(arr);
        diff.update(1, 3, 2);  // 在区间[1,3]上加2
        System.out.println("Difference Array results: " + Arrays.toString(diff.getResult()));

        // 3. 分块处理示例
        BlockProcessing block = new BlockProcessing(arr);
        System.out.println("Block Processing result: " + block.query(1, 3));

        // 4. 树状数组示例
        int[][] binaryIndexedQueries = {{0, 2}, {1, 3}, {2, 4}};
        int[] bitResult = BinaryIndexedTree.processQueries(arr, binaryIndexedQueries);
        System.out.println("Binary Indexed Tree results: " + Arrays.toString(bitResult));
    }
}