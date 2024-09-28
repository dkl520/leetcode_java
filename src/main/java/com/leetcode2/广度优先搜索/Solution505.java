package com.leetcode2.广度优先搜索;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution505 {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>((p1, p2) -> Double.compare(p1.distance, p2.distance));
        Point startPoint = new Point(start[0], start[1], Integer.MAX_VALUE);
        int[][] visited = new int[maze.length][maze[0].length];
        List<Point> path = new ArrayList<>();
//        path.add(startPoint);
        priorityQueue.offer(startPoint);
        while (!priorityQueue.isEmpty()) {
            Point point = priorityQueue.poll();
            visited[point.x][point.y] = 1;
            if (point.x == destination[0] && point.y == destination[1]) {
                return path.size() - 1;
            }
            List<Point> pointsList = getNextPosition(maze, point, destination, visited, path);
            if (!pointsList.isEmpty()) {
                priorityQueue.addAll(pointsList);
            }
        }

        return -1;
    }


    List<Point> getNextPosition(int[][] grid, Point point, int[] destination, int[][] visited, List<Point> path) {
        List<Point> pointsList = new ArrayList<>();
        int[][] positions = {
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1}
        };
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            int newX = x + point.x;
            int newY = y + point.y;
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 0 && visited[newX][newY] == 0) {
                double newDistance = calcDistance(newX, newY, destination);
                Point nextPoint = new Point(newX, newY, (int)newDistance);
                pointsList.add(nextPoint);
            }
        }
        if (!pointsList.isEmpty()) {
            path.add(point);
        }
        return pointsList;
    }

    double calcDistance(int x, int y, int[] destination) {
        int destX = destination[0];
        int destY = destination[1];
        // 计算欧几里得距离
        return Math.sqrt(Math.pow(destX - x, 2) + Math.pow(destY - y, 2));
    }



    public static void main(String[] args) {
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1},
        };
        int[] start = {1, 5};
        int[] destination = {5, 5};
        long startTime = System.nanoTime();
        int result = new Solution505().shortestDistance(maze, start, destination);
        System.out.println(result);
        long endTime = System.nanoTime();
        long durationInMillis = (endTime - startTime) / 1000000;
        System.out.println(durationInMillis);

    }


}
