package com.leetcode2.org.大厂;

import java.util.*;

public class SearchMinlineforwell {
    static class Point {
        int x;
        int y;
        int dis;

        public Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    int[] search(int[][] position, List<int[]> villages) {
        int[] result = new int[2];
        int m = position.length;
        int n = position[0].length;
        boolean[][] isGETtoVillages = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> pq = new LinkedList<int[]>();
        boolean[][] isGETtoVillagesOne = bfs(m, n, position, villages.get(0));
        boolean[][] isGETtoVillagesTwo = bfs(m, n, position, villages.get(1));
        Queue<Point> pointQueue = new PriorityQueue<>(Comparator.comparing((p) -> p.dis));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isGETtoVillagesOne[i][j] && isGETtoVillagesTwo[i][j]) {
                    isGETtoVillages[i][j] = true;
                    pointQueue.add(new Point(i, j, calcManhattanDistance(new int[]{i, j}, villages.get(0), villages.get(1))));
                }
            }
        }

        Point resultP = pointQueue.poll();

        if (resultP != null) {
            return new int[]{resultP.x, resultP.y};
        }
        return new int[]{-1, -1};

    }

    boolean[][] bfs(int m, int n, int[][] position, int[] village) {
        boolean[][] isGETtoVillages = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1}
        };
        Queue<int[]> pq = new LinkedList<int[]>();
        pq.add(village);
        visited[village[0]][village[1]] = true;
        isGETtoVillages[village[0]][village[1]] = true;
        while (!pq.isEmpty()) {
            int[] point = pq.poll();
            for (int[] dir : dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && position[x][y] >= position[point[0]][point[1]]) {
                    visited[x][y] = true;
                    pq.offer(new int[]{x, y});
                    isGETtoVillages[x][y] = true;
                }
            }
        }

        return isGETtoVillages;
    }

    int calcManhattanDistance(int[] target, int[] village1, int[] village2) {
        int result = 0;
        result += Math.abs(target[0] - village1[0]) + Math.abs(target[1] - village1[1]);

        result += Math.abs(target[0] - village2[0]) + Math.abs(target[1] - village2[1]);
        return result;
    }

    public static void main(String[] args) {
        int[][] position = new int[][]{
                {4, 9, 7, 6, 5},
                {2, 6, 6, 5, 3},
                {6, 5, 1, 2, 8},
                {3, 4, 7, 2, 5},
        };
        List<int[]> villages = new ArrayList<int[]>();
        villages.add(new int[]{1, 4});
        villages.add(new int[]{3, 1});
        SearchMinlineforwell searchMinlineforwell = new SearchMinlineforwell();
        System.out.println(Arrays.toString(searchMinlineforwell.search(position, villages)));

    }
}
