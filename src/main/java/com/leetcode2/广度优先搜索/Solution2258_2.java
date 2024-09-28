package com.leetcode2.广度优先搜索;

import java.util.*;

public class Solution2258_2 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] timeFired = fireController(grid);
        int status = personCanReach(grid, timeFired, 0);
        if (status == -1) return -1;
        status = personCanReach(grid, timeFired, 1000_000_000);
        if (status != -1) return (int) Math.pow(10, 9);


        int ans = 0;
        int left = 0;
        int right = m + n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            status = personCanReach(grid, timeFired, mid);
            if (status == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
                ans = mid;
            }
        }
        return ans;
    }

    int personCanReach(int[][] grid, int[][] timeFired, int neededTime) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] personVisited = new boolean[m][n];
        Queue<int[]> personFindLines = new ArrayDeque<>();
        personFindLines.add(new int[]{0, 0});
        personVisited[0][0] = true;
        int[][] personTime = new int[m][n];
//        int neededTime = 0;
        while (!personFindLines.isEmpty()) {
            int size = personFindLines.size();
            neededTime++;
            while (size > 0) {
                int[] person = personFindLines.poll();
                for (int[] dir : DIRECTIONS) {
                    int nextX = person[0] + dir[0];
                    int nextY = person[1] + dir[1];
                    if (nextX == m - 1 && nextY == n - 1 && timeFired[nextX][nextY] >= neededTime) {
                        return neededTime;
                    }

                    if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && !personVisited[nextX][nextY]
                            && grid[nextX][nextY] == 0 && timeFired[nextX][nextY] > neededTime) {
                        personVisited[nextX][nextY] = true;
                        personFindLines.add(new int[]{nextX, nextY});
                        if (personVisited[m - 1][n - 1]) {
                            return neededTime;
                        }
                    }
                }
                size--;
            }
        }
        return -1;
    }

    public int[][] fireController(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] copiedGrid = Arrays.stream(grid)
                .map(row -> Arrays.stream(row).toArray())
                .toArray(int[][]::new);
        boolean[][] fireVisited = new boolean[m][n];

        int[][] timeFired = new int[m][n];
        Arrays.stream(timeFired).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        Queue<int[]> ignitionSites = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fireVisited[i][j] = true;
                    timeFired[i][j] = 0;
                    ignitionSites.offer(new int[]{i, j});
                }
            }
        }
        // 使用保存的数据执行下一步操作
        return nextIgnitionSites(ignitionSites, copiedGrid, fireVisited, timeFired);
    }

    int[][] nextIgnitionSites(Queue<int[]> ignitionSites, int[][] grid, boolean[][] fireVisited, int[][] timeFired) {
        int fireTime = 1;
        while (!ignitionSites.isEmpty()) {
            int size = ignitionSites.size();
            while (size > 0) {
                int[] curIgnitionSite = ignitionSites.poll();
                for (int[] dir : DIRECTIONS) {
                    int nextX = curIgnitionSite[0] + dir[0];
                    int nextY = curIgnitionSite[1] + dir[1];
                    if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && !fireVisited[nextX][nextY] && grid[nextX][nextY] == 0) {
                        fireVisited[nextX][nextY] = true;
                        ignitionSites.add(new int[]{nextX, nextY});
                        timeFired[nextX][nextY] = fireTime;
                        grid[nextX][nextY] = 1;
                    }
                }
                size--;
            }
            fireTime++;
        }

        return timeFired;
    }


    public static void main(String[] args) {
//        int[][] grid = {
//                {0, 2, 0, 0, 0, 0, 0},
//                {0, 0, 0, 2, 2, 1, 0},
//                {0, 2, 0, 0, 1, 2, 0},
//                {0, 0, 2, 2, 2, 0, 2},
//                {0, 0, 0, 0, 0, 0, 0}
//        };
        int[][] grid = {
                {0, 2, 0, 0, 1},
                {0, 2, 0, 2, 2},
                {0, 2, 0, 0, 0},
                {0, 0, 2, 2, 0},
                {0, 0, 0, 0, 0}
        };


        Solution2258_2 solution2258 = new Solution2258_2();
        System.out.println(solution2258.maximumMinutes(grid));

    }
}
