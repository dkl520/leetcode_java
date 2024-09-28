package com.leetcode2.广度优先搜索;

import java.util.List;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Position {
    int x;
    int y;
    int val;

    public Position(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class Solution407 {

    public int trapRainWater(int[][] heightMap) {
        int[][] visited = new int[heightMap.length][heightMap[0].length];
        PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                if (i == 0 || i == heightMap.length - 1 || j == 0 || j == heightMap[i].length - 1) {
                    Position p = new Position(i, j, heightMap[i][j]);
                    pq.offer(p);
                }
            }
        }
        int count = 0;
        while (!pq.isEmpty()) {
            Position point = pq.poll();
            visited[point.x][point.y] = 1;
            List<Position> nextPoints = findNextPosition(point, heightMap, visited);
            for (Position nextPoint : nextPoints) {
                if (nextPoint.val < point.val) {
                    count += point.val - nextPoint.val;
                    nextPoint.val = point.val;
                    heightMap[nextPoint.x][nextPoint.y] = point.val;
                }
                pq.offer(nextPoint);
            }
        }
        return count;
    }

    List<Position> findNextPosition(Position point, int[][] heightMap, int[][] visited) {
        int[][] dirt = {
                {-1, 0}, {0, 1}, {1, 0}, {0, -1}
        };
        List<Position> result = new ArrayList<>();
        for (int[] dir : dirt) {
            int x = point.x + dir[0];
            int y = point.y + dir[1];
            if (x >= 0 && x < heightMap.length && y >= 0 && y < heightMap[0].length && visited[x][y] == 0) {
                result.add(new Position(x, y, heightMap[x][y]));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] heightMap = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };
        Solution407 solution407 = new Solution407();
        System.out.println(solution407.trapRainWater(heightMap));

    }
}
