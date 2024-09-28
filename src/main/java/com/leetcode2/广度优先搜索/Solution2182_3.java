package com.leetcode2.广度优先搜索;

import com.leetcode2.org.并查集.Solution2182;

import java.util.*;

public class Solution2182_3 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.size() < 2 || grid.get(0).get(0) == 1 || grid.get(grid.size() - 1).get(grid.size() - 1) == 1) return 0;
        int left = 0;

        Queue<int[]> thieves = new LinkedList<>(); // 用于BFS的队列
        int[][] distances = new int[n][n]; // 存储每个格子到最近小偷的距离
        boolean[][] visited = new boolean[n][n]; // 记录格子是否被访问过

        // 初始化BFS，将所有小偷加入队列，并标记为已访问
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    thieves.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 使用BFS计算每个格子到最近小偷的距离
        int distance = 0;
        while (!thieves.isEmpty()) {
            int size = thieves.size();
            for (int i = 0; i < size; i++) {
                int[] curr = thieves.poll();
                int x = curr[0], y = curr[1];
                distances[x][y] = distance; // 当前格子到最近小偷的距离
                // 遍历当前格子的四个相邻格子
                for (int[] dir : DIRECTIONS) { // 假设DIRECTIONS是定义好的方向数组，如[[-1,0],[1,0],[0,-1],[0,1]]
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                        thieves.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            distance++;
        }

        int right = Math.min(distances[0][0], distances[n - 1][n - 1]);
        int ans = n;

        while (left <= right) {
            int mid = (left + right) / 2;
            boolean[][] seen = new boolean[n][n];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            seen[0][0] = true;


                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int[] dir : DIRECTIONS) {
                        int nx = cur[0] + dir[0];
                        int ny = cur[1] + dir[1];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && !seen[nx][ny] && distances[nx][ny] >= mid) {
                            seen[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }

                if (seen[n - 1][n - 1]) {
                    ans = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

        }
        return ans;
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

        Solution2182_3 solution2182 = new Solution2182_3();
        List<List<Integer>> grildL = Arrays.stream(grid).map(v -> Arrays.stream(v).boxed().toList()).toList();
        System.out.println(solution2182.maximumSafenessFactor(grildL));
    }
}
