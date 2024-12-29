package com.leetcode2.深度优先搜索;

import java.util.*;

public class Solution329 {
    Map<String, Integer> map = new HashMap<>();
    int[][] DIRS = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    public int longestIncreasingPath(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        Queue<int[]> q = new PriorityQueue<>((a, b) -> matrix[b[0]][b[1]] - matrix[a[0]][a[1]]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                q.offer(new int[]{i, j});
//                max = Math.max(max, dfs(i, j, matrix));
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            max = Math.max(max, dfs(cur[0], cur[1], matrix));
        }


        return max;
    }

    int dfs(int row, int col, int[][] matrix) {
        int[] key = new int[]{row, col};
        if (map.containsKey(Arrays.toString(key))) {
            return map.get(Arrays.toString(key));
        }
        int max = 1;
        for (int[] dir : DIRS) {
            int newX = key[0] + dir[0];
            int newY = key[1] + dir[1];
            if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length) {
                if (matrix[row][col] < matrix[newX][newY]) {
                    max = Math.max(max, dfs(newX, newY, matrix) + 1);
                }
            }
        }
        map.put(Arrays.toString(key), max);
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
                {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
                {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
                {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
                {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
                {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
                {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
                {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
                {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
                {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
                {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        Solution329 solution = new Solution329();
        System.out.println(solution.longestIncreasingPath(matrix));


    }
}
