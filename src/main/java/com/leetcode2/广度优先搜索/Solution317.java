package com.leetcode2.广度优先搜索;

import java.util.*;
import java.util.stream.Collectors;

public class Solution317 {
    static class Point {
        int[] pos;
        int dis;

        public Point(int[] pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.deepEquals(pos, point.pos);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(pos);
        }
    }

    public int shortestDistance(int[][] grid) {
        List<int[]> listBuilding = new ArrayList<int[]>();
        List<int[]> listPanel = new ArrayList<>();
        List<List<Point>> listAllPoint = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    listBuilding.add(new int[]{i, j});
                } else if (grid[i][j] == 0) {
                    listPanel.add(new int[]{i, j});
                }
            }
        }
        List<Point> setS = new ArrayList<>();
        for (int i = 0; i < listBuilding.size(); i++) {
            List<Point> listCur = calcDis(grid, listBuilding.get(i));
            listCur = listCur.stream().filter(point -> point.dis == 0).collect(Collectors.toList());
            if (i == 0) {
                setS = listCur;
            } else {
                if (listCur.isEmpty()) {
                    return -1;
                }
                setS.retainAll(listCur);
            }
        }
        if (setS.isEmpty()) {
            return -1;
        }
        int result = Integer.MAX_VALUE;
        for (Point panel : setS) {
            int dist = 0;
            for (int[] building : listBuilding) {
                dist += calcD(panel.pos, building);
            }
            result = Math.min(result, dist);
        }
        return result;
    }

    int calcD(int[] cur, int[] next) {
        return Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]);
    }

    List<Point> calcDis(int[][] grid, int[] posCur) {
        int[][] DIRS = new int[][]{
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        List<Point> list = new ArrayList<>();
        Point start = new Point(posCur, 0);
        Deque<Point> queue = new ArrayDeque<>();
        int[][] visited = new int[grid.length][grid[0].length];
        queue.offer(start);
        visited[start.pos[0]][start.pos[1]] = 1;
        while (!queue.isEmpty()) {
            Point node = (Point) queue.poll();
            for (int[] dir : DIRS) {
                int x = node.pos[0] + dir[0];
                int y = node.pos[1] + dir[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && visited[x][y] == 0) {
                    if (grid[x][y] != 2) {
                        Point point = new Point(new int[]{x, y}, grid[x][y]);
                        queue.offer(point);
                        list.add(point);
                        visited[x][y] = 1;
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        Solution317 solution317 = new Solution317();
        System.out.println(solution317.shortestDistance(grid));
    }
}
