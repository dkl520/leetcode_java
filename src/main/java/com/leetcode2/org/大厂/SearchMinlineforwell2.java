package com.leetcode2.org.大厂;

import java.util.*;

public class SearchMinlineforwell2 {

    public int[] search(int[][] position, List<int[]> villages) {
        int m = position.length;
        int n = position[0].length;

        boolean[][] visitedOne = bfs(m, n, position, villages.get(0));
        boolean[][] visitedTwo = bfs(m, n, position, villages.get(1));

        int minDist = Integer.MAX_VALUE;
        int[] bestLocation = {-1, -1};

        // 遍历每个位置，找到两个村庄都能到达的最佳点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedOne[i][j] && visitedTwo[i][j]) {
                    int dist = calcManhattanDistance(new int[]{i, j}, villages.get(0), villages.get(1));
                    if (dist < minDist) {
                        minDist = dist;
                        bestLocation = new int[]{i, j};
                    }
                }
            }
        }

        return bestLocation;
    }

    // 广度优先搜索，找到所有可以到达的点
    private boolean[][] bfs(int m, int n, int[][] position, int[] village) {
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(village);
        visited[village[0]][village[1]] = true;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] dir : dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && position[x][y] >= position[point[0]][point[1]]) {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        return visited;
    }

    // 计算曼哈顿距离
    private int calcManhattanDistance(int[] target, int[] village1, int[] village2) {
        return Math.abs(target[0] - village1[0]) + Math.abs(target[1] - village1[1]) +
                Math.abs(target[0] - village2[0]) + Math.abs(target[1] - village2[1]);
    }

    public static void main(String[] args) {
        int[][] position = {
                {4, 9, 7, 6, 5},
                {2, 6, 6, 5, 3},
                {6, 5, 1, 2, 8},
                {3, 4, 7, 2, 5},
        };
        List<int[]> villages = new ArrayList<>();
        villages.add(new int[]{1, 4});
        villages.add(new int[]{3, 1});

        SearchMinlineforwell2 searchMinlineforwell = new SearchMinlineforwell2();
        System.out.println(Arrays.toString(searchMinlineforwell.search(position, villages)));
    }
}
